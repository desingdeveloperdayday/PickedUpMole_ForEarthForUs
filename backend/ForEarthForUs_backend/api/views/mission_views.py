from rest_framework import viewsets
from api.models.mission_models import Mission
from api.serializers.mission_serializer import MissionSerializer, MissionDetailSerializer
from api.permission import *
from rest_framework.response import Response
from rest_framework import status

class MissionDetailViewSet(viewsets.ModelViewSet):
    queryset = Mission.objects.all()
    serializer_class = MissionDetailSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)

    def list(self, request):
        queryset = Mission.objects.all()
        category = request.query_params.get('category', None)
        if category is None:
            return Response({"message": "category query should be needed"},
                status=status.HTTP_400_BAD_REQUEST)
        queryset = queryset.filter(category=category)
        serializer = MissionSerializer(queryset, many=True, context={"request": request})
        return Response(serializer.data)

    def create(self, request, *args, **kwargs):
        queryset = Mission.objects.filter(
                        category=request.data['category'],
                        title=request.data['title']
                    )
        
        if queryset:
            return Response({"message": "Mission is already exist"},
                status=status.HTTP_400_BAD_REQUEST)

        serializer = MissionDetailSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        instance = self.perform_create(serializer)

        read_serializer = MissionDetailSerializer(instance, context={"request": request})
        return Response(read_serializer.data, status=status.HTTP_201_CREATED)
