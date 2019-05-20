from rest_framework import viewsets
from api.models.earth_models import Earth
from api.serializers.earth_serializer import EarthSerializer
from api.permission import *

class EarthViewSet(viewsets.ModelViewSet):
    queryset = Earth.objects.all()
    serializer_class = EarthSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)
