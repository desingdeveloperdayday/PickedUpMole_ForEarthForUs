from django.contrib import admin

from api.models.prefer_models import Prefer, UserPrefer

admin.site.register(Prefer)
admin.site.register(UserPrefer)
