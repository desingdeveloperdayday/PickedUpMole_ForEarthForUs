from rest_framework import viewsets
from api.models.donation_models import Donation
from api.serializers.donation_serializer import DonationSerializer
from api.permission import *

class DonationViewSet(viewsets.ModelViewSet):
    queryset = Donation.objects.all()
    serializer_class = DonationSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)
