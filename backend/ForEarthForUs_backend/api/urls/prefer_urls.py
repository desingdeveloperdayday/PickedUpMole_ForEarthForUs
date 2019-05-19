from django.urls import path, include
from api.views.prefer_views import *
from rest_framework.routers import DefaultRouter

# Create a router and register our viewsets with it.
router = DefaultRouter()
router.register(r'v1/prefer', PreferViewSet)

class PreferUrls():
    prefer_urlpatterns = [
        path('', include(router.urls)),
        path('v1/account/prefer/', UserPreferCreate.as_view()),
    ]
