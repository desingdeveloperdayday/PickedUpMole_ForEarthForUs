from django.urls import path, include
from api import views
from rest_framework.routers import DefaultRouter

# Create a router and register our viewsets with it.
router = DefaultRouter()
router.register(r'v1/prefer', views.PreferViewSet)

urlpatterns = [
    path('v1/rest-auth/', include('rest_auth.urls')),
    path('v1/rest-auth/registration/', include('rest_auth.registration.urls')),
    path('', include(router.urls)),
]
