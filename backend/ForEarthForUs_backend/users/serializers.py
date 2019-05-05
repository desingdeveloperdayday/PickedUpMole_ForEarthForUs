from rest_framework import serializers
from django.contrib.auth import get_user_model

UserModel = get_user_model()

class CustomUserDetailsSerializer(serializers.ModelSerializer):

    class Meta:
        model = UserModel
        fields = ('email', 'name', 'password')