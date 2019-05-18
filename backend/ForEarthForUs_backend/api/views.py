from rest_framework import viewsets
from rest_framework import generics
from .models import Prefer, UserPrefer
from .serializers import PreferSerializer, UserPreferSerializer
from rest_framework import permissions
from .permission import *
from rest_framework.response import Response
from rest_framework import status

class PreferViewSet(viewsets.ModelViewSet):
    queryset = Prefer.objects.all()
    serializer_class = PreferSerializer
    permission_classes = (IsAdminOrReadOnly,)

class UserPreferCreate(generics.CreateAPIView):
    queryset = UserPrefer.objects.all()
    serializer_class = UserPreferSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

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
        for prefer_dic in data:
            user_prefer_set = UserPrefer.objects.filter(
                user=prefer_dic['user'], prefer_id=prefer_dic['prefer'])

            # 보낸 user id를 알아서 맞는지 확인해야함
            if request.user.id is not prefer_dic['user']:
                return Response(
                    {"message": "Forbidden request (user id invalid)"},
                    status=status.HTTP_403_FORBIDDEN)
            # 보낸 prefer id중에 중복이 있는지 체크
            if prefer_dic['prefer'] in prefer_id_list:
                return Response(
                    {"message": "Forbidden request (duplicated prefer id)"},
                    status=status.HTTP_403_FORBIDDEN)
            prefer_id_list.append(prefer_dic['prefer'])

        if not is_many:
            return super(UserPreferCreate, self).create(request, *args, **kwargs)
        else:
            serializer = self.get_serializer(data=request.data, many=True)
            serializer.is_valid(raise_exception=True)
            self.perform_create(serializer)
            headers = self.get_success_headers(serializer.data)
            return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)
