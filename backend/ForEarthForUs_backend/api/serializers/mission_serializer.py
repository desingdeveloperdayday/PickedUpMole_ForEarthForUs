from rest_framework import serializers
from api.models.mission_models import Mission
from api.models.category_models import Category
from api.models.feed_models import Feed
from api.serializers.category_serializer import CategorySerializer
from api.utils.validate_svg import validate_image_extension
from api.utils.time_func import calc_today

class MissionSerializer(serializers.HyperlinkedModelSerializer):
    category = serializers.PrimaryKeyRelatedField(read_only=True)
    image = serializers.FileField(max_length=None, use_url=True,
                                  validators=[validate_image_extension])

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'content')

    def to_representation(self, instance):
        data = super(MissionSerializer, self).to_representation(instance)
        data['status'] = update_status(self.context['request'].user.id, data['id'])
        return data

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

    def to_representation(self, instance):
        data = super(MissionDetailSerializer, self).to_representation(instance)
        data['status'] = update_status(self.context['request'].user.id, data['id'])
        return data

class MissionFeedSerializer(serializers.HyperlinkedModelSerializer):
    category = CategorySerializer(required=True)
    image = serializers.FileField(max_length=None, use_url=True,
                                  validators=[validate_image_extension])

    class Meta:
        model = Mission
        fields = ('id', 'category', 'image', 'title', 'comment')

def update_status(userId, missionId):
    start, end = calc_today()
    feed = Feed.objects.filter(
                user=userId,
                mission=missionId,
                created__gte=start,
                created__lte=end
            )

    val = ''
    if feed:
        if feed.values()[0]['complete'] is True:
            val = 'complete'
        else:
            val = 'progress'
    else:
        val = 'new'

    return val
