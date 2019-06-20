from django.urls import path, include
from api.views.donation_views import *
from rest_framework.routers import DefaultRouter

# Create a router and register our viewsets with it.
router = DefaultRouter()
router.register(r'v1/donation', DonationViewSet)

class DonationUrls():
    urlpatterns = [
        path('', include(router.urls)),
    ]
