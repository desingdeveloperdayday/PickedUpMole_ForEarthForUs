from rest_framework import viewsets
from rest_framework import generics
from .models import Prefer
from .serializers import PreferSerializer
from rest_framework import permissions
from rest_framework.response import Response
from rest_framework import status

class PreferViewSet(viewsets.ModelViewSet):
    queryset = Prefer.objects.all()
    serializer_class = PreferSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    # permission_classes = (permissions.AllowAny,)
