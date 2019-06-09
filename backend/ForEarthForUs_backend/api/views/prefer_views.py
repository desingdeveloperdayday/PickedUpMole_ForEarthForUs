from rest_framework import viewsets
from rest_framework import generics
from api.models.prefer_models import Prefer, UserPrefer
from api.serializers.prefer_serializer import PreferSerializer, UserPreferSerializer
from rest_framework import permissions
from api.permission import *
from rest_framework.response import Response
from rest_framework import status

class PreferViewSet(viewsets.ModelViewSet):
    queryset = Prefer.objects.all()
    serializer_class = PreferSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)

class UserPreferCreate(generics.CreateAPIView):
    queryset = UserPrefer.objects.all()
    serializer_class = UserPreferSerializer
    permission_classes = (permissions.IsAuthenticated,)

    def create(self, request, *args, **kwargs):
        """
        checks if post request data is an array initializes serializer with many=True
        else executes default CreateModelMixin.create function
        """
        is_many = isinstance(request.data, list)
        if not is_many:
            data = [request.data]
        else:
            data = request.data

        prefer_id_list = []
        json_arr = []
        for prefer_item in data:
            # 보낸 prefer id중에 중복이 있는지 체크
            if prefer_item in prefer_id_list:
                return Response(
                    {"message": "Forbidden request (duplicated prefer id)"},
                    status=status.HTTP_403_FORBIDDEN)
            prefer_id_list.append(prefer_item)
            json_arr.append({'prefer': prefer_item, 'user':request.user.id})

        if not is_many:
            return super(UserPreferCreate, self).create(request, *args, **kwargs)
        else:
            serializer = self.get_serializer(data=json_arr, many=True)
            serializer.is_valid(raise_exception=True)
            self.perform_create(serializer)
            headers = self.get_success_headers(serializer.data)
            return Response(status=status.HTTP_201_CREATED, headers=headers)
