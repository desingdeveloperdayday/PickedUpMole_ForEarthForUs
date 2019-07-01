from django.contrib import admin

from api.models.prefer_models import Prefer, UserPrefer
from api.models.earth_models import Earth
from api.models.category_models import Category
from api.models.mission_models import Mission
from api.models.feed_models import Feed
from api.models.donation_models import Donation
from api.models.campaign_models import Campaign
from api.models.article_models import Article
from api.models.scrap_models import Scrap

admin.site.register(Prefer)
admin.site.register(UserPrefer)
admin.site.register(Earth)
admin.site.register(Category)
admin.site.register(Mission)
admin.site.register(Feed)
admin.site.register(Donation)
admin.site.register(Campaign)
admin.site.register(Article)
admin.site.register(Scrap)
