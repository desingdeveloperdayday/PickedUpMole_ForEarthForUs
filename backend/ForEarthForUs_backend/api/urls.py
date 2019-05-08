from django.urls import path, include
from api import views

urlpatterns = [
    path('v1/rest-auth/', include('rest_auth.urls')),
    path('v1/rest-auth/registration/', include('rest_auth.registration.urls')),
]
