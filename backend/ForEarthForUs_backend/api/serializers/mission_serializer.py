from rest_framework import serializers
from api.models.mission_models import Mission
from api.models.category_models import Category
from api.serializers.category_serializer import CategorySerializer
from api.utils.validate_svg import validate_image_extension

class MissionSerializer(serializers.HyperlinkedModelSerializer):
    category = serializers.PrimaryKeyRelatedField(read_only=True)
    image = serializers.FileField(max_length=None, use_url=True,
                                validators=[validate_image_extension])

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'content')

class MissionDetailSerializer(serializers.HyperlinkedModelSerializer):
    category = serializers.PrimaryKeyRelatedField(
        read_only=False, queryset=Category.objects.all())
    image = serializers.FileField(max_length=None, use_url=True,
                                validators=[validate_image_extension])

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'content',
                  'missionTipTitle', 'missionTipContent',
                  'missionMethodContent', 'missionEffectContent')

class MissionFeedSerializer(serializers.HyperlinkedModelSerializer):
    category = CategorySerializer(required=True)
    image = serializers.FileField(max_length=None, use_url=True,
                                validators=[validate_image_extension])

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'comment')
