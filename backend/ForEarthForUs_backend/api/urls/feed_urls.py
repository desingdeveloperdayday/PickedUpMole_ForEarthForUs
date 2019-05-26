from django.urls import path, include
from api.views.feed_views import *
from rest_framework.routers import DefaultRouter

# Create a router and register our viewsets with it.
router = DefaultRouter()
router.register(r'v1/feed', FeedViewSet)

class FeedUrls():
    urlpatterns = [
        path('', include(router.urls)),
    ]
