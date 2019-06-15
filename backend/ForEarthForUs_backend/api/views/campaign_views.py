from rest_framework import viewsets
from api.models.campaign_models import Campaign
from api.serializers.campaign_serializer import CampaignSerializer
from api.permission import *

class CampaignViewSet(viewsets.ModelViewSet):
    queryset = Campaign.objects.all()
    serializer_class = CampaignSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)
