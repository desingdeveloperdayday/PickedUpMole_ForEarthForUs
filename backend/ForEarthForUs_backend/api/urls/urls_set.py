from django.urls import path, include
from api.urls.prefer_urls import PreferUrls
from api.urls.earth_urls import EarthUrls
from api.urls.category_urls import CategoryUrls
from api.urls.mission_urls import MissionUrls
from api.urls.feed_urls import FeedUrls
from api.urls.donation_urls import DonationUrls
from api.urls.campaign_urls import CampaignUrls
from api.urls.article_urls import ArticleUrls
from api.urls.scrap_urls import ScrapUrls

urlpatterns = [
    path('v1/account/', include('rest_auth.urls')),
    path('v1/account/registration/', include('rest_auth.registration.urls')),
]

urlpatterns += PreferUrls.urlpatterns
urlpatterns += EarthUrls.urlpatterns
urlpatterns += CategoryUrls.urlpatterns
urlpatterns += MissionUrls.urlpatterns
urlpatterns += FeedUrls.urlpatterns
urlpatterns += DonationUrls.urlpatterns
urlpatterns += CampaignUrls.urlpatterns
urlpatterns += ArticleUrls.urlpatterns
urlpatterns += ScrapUrls.urlpatterns
