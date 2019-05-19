from django.urls import path, include
from api.urls.prefer_urls import PreferUrls

urlpatterns = [
    path('v1/account/', include('rest_auth.urls')),
    path('v1/account/registration/', include('rest_auth.registration.urls')),
]

urlpatterns += PreferUrls.prefer_urlpatterns
