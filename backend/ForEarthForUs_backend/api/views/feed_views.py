from rest_framework import viewsets
from api.models.feed_models import Feed
from api.serializers.feed_serializer import (
    FeedReadSerializer, FeedRetreiveSerializer,
    FeedWriteSerializer, FeedUpdateSerializer)
from api.permission import *
from rest_framework.response import Response
from rest_framework import status
from api.utils.time_func import calc_today, get_today
from django.contrib.auth import get_user_model
from api.utils.earth_check import increase_earthLevel, decrease_earthLevel

STATUS_PROGRESS = 'progress'
STATUS_COMPLETE = 'complete'
STATUS_ALL = 'all'

class FeedViewSet(viewsets.ModelViewSet):
    queryset = Feed.objects.all()
    serializer_class = FeedReadSerializer
    permission_classes = (permissions.IsAuthenticated,)

    def list(self, request):
        queryset = Feed.objects.all()

        user = request.user.id
        query = request.query_params.get('status', None)
        complete = False
        if query is None:
            return Response({"message": "status query should be needed"},
                status=status.HTTP_400_BAD_REQUEST)
        else:
            if query == STATUS_PROGRESS:
                complete = False
                start, end = calc_today()
                queryset = queryset.filter(
                    user=user, complete=complete,
                    created__gte=start, created__lte=end)
            elif query == STATUS_COMPLETE:
                complete = True
                queryset = queryset.filter(user=user, complete=complete)
            elif query == STATUS_ALL:
                queryset = queryset.filter(user=user)
            else:
                return Response({"message": "Invalid query"},
                    status=status.HTTP_400_BAD_REQUEST)

        serializer = FeedReadSerializer(queryset, many=True, context={"request": request})
        return Response(serializer.data)

    def retrieve(self, request, pk=None):
        try:
            feed = Feed.objects.get(pk=pk)
        except:
            return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = FeedRetreiveSerializer(feed, context={"request": request})
        return Response(serializer.data)

    def create(self, request, *args, **kwargs):
        today = get_today()
        mission = request.data['mission']
        id = "%s_%d" % (today, mission)

        newData = request.data
        newData['id'] = id

        write_serializer = FeedWriteSerializer(data=newData)
        write_serializer.is_valid(raise_exception=True)
        instance = self.perform_create(write_serializer)

        read_serializer = FeedReadSerializer(instance, context={"request": request})
        return Response(read_serializer.data, status=status.HTTP_201_CREATED)

    def partial_update(self, request, pk=None):
        if not 'result' in request.data.keys():
            return Response({"message": "result can not be empty"},
                    status=status.HTTP_400_BAD_REQUEST)

        instance = self.queryset.get(pk=pk)
        if instance is None:
            return Response(status=status.HTTP_404_NOT_FOUND)

        firstComplete = False
        if not instance.complete:
            firstComplete = True
        
        request.data["complete"] = True
        serializer = FeedUpdateSerializer(
            instance, data=request.data,
            partial=True, context={"request": request})
        serializer.is_valid(raise_exception=True)
        serializer.save()

        # Earth level uypdate logic
        if firstComplete:
            userModel = get_user_model()
            userInstance = userModel.objects.get(email=request.user.email)
            newEarthLevel = increase_earthLevel(userInstance.earthLevel)
            if newEarthLevel is not userInstance.earthLevel:
                userInstance.earthLevel = newEarthLevel
                userInstance.save()

        read_serializer = FeedReadSerializer(instance, context={"request": request})
        return Response(read_serializer.data, status=status.HTTP_202_ACCEPTED)

    def perform_create(self, serializer):
        return serializer.save(user=self.request.user)

    def get_permissions(self):
       if self.request.method == 'PATCH':
           self.permission_classes = (permissions.IsAuthenticated, IsOwnerFeed)
       return super(FeedViewSet, self).get_permissions()
