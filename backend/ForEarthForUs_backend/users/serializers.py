from rest_framework import serializers
from django.contrib.auth import get_user_model
from api.models.prefer_models import UserPrefer

userModel = get_user_model()

class CustomUserDetailsSerializer(serializers.ModelSerializer):
    prefer = serializers.PrimaryKeyRelatedField(
        many=True, read_only=False, queryset=UserPrefer.objects.all())

    class Meta:
        model = userModel
        fields = ('id', 'email', 'name', 'earthLevel', 'prefer')
