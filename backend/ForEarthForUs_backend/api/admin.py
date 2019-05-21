from django.contrib import admin

from api.models.prefer_models import Prefer, UserPrefer
from api.models.earth_models import Earth
from api.models.category_models import Category

admin.site.register(Prefer)
admin.site.register(UserPrefer)
admin.site.register(Earth)
admin.site.register(Category)
