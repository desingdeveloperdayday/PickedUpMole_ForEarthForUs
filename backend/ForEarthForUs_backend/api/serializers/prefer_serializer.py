from rest_framework import serializers
from api.models.prefer_models import Prefer, UserPrefer
from django.contrib.auth import get_user_model
from api.utils.validate_svg import validate_image_extension

userModel = get_user_model()

class PreferSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True,
                                validators=[validate_image_extension])

    class Meta:
        model = Prefer
        fields = ('id', 'name', 'image')

class UserPreferSerializer(serializers.ModelSerializer):

    class Meta:
        model = UserPrefer
        fields = ('prefer', 'user')
