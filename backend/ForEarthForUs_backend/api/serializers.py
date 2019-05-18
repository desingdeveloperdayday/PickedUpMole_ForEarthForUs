from rest_framework import serializers
from .models import Prefer
from django.contrib.auth import get_user_model

userModel = get_user_model()

class PreferSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Prefer
        fields = ('id', 'name', 'image')
