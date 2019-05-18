from django.contrib import admin

from .models import Prefer, UserPrefer

admin.site.register(Prefer)
admin.site.register(UserPrefer)