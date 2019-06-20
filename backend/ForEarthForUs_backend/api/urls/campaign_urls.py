from django.urls import path, include
from api.views.campaign_views import *
from rest_framework.routers import DefaultRouter

# Create a router and register our viewsets with it.
router = DefaultRouter()
router.register(r'v1/campaign', CampaignViewSet)

class CampaignUrls():
    urlpatterns = [
        path('', include(router.urls)),
    ]
