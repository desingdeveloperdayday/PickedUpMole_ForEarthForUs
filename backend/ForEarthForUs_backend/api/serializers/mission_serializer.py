from rest_framework import serializers
from api.models.mission_models import Mission
from api.models.category_models import Category
from api.serializers.category_serializer import CategorySerializer

class MissionSerializer(serializers.HyperlinkedModelSerializer):
    category = serializers.PrimaryKeyRelatedField(read_only=True)
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'content')

class MissionDetailSerializer(serializers.HyperlinkedModelSerializer):
    category = serializers.PrimaryKeyRelatedField(
        read_only=False, queryset=Category.objects.all())
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'content',
                  'missionTipTitle', 'missionTipContent',
                  'missionMethodTitle', 'missionMethodContent',
                  'missionEffectTitle', 'missionEffectContent')

class MissionFeedSerializer(serializers.HyperlinkedModelSerializer):
    category = CategorySerializer(required=True)
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'comment')
