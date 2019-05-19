from rest_framework import serializers
from api.models.prefer_models import Prefer, UserPrefer
from django.contrib.auth import get_user_model

userModel = get_user_model()

class PreferSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Prefer
        fields = ('id', 'name', 'image')

class UserPreferSerializer(serializers.ModelSerializer):

    class Meta:
        model = UserPrefer
        fields = ('prefer', 'user')
