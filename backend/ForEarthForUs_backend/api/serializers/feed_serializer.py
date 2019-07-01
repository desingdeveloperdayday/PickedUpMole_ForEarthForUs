from rest_framework import serializers
from api.models.feed_models import Feed
from api.models.mission_models import Mission
from api.serializers.mission_serializer import MissionFeedSerializer, MissionDetailSerializer

class FeedReadSerializer(serializers.HyperlinkedModelSerializer):
    mission = MissionFeedSerializer(required=True)

    class Meta:
        model = Feed
        fields = ('id', 'mission', 'result', 'message', 'created')

class FeedRetreiveSerializer(serializers.HyperlinkedModelSerializer):
    mission = MissionDetailSerializer(required=True)

    class Meta:
        model = Feed
        fields = ('id', 'mission', 'result', 'message', 'created')

class FeedWriteSerializer(serializers.ModelSerializer):

    class Meta:
        model = Feed
        fields = ('id', 'mission',)

class FeedUpdateSerializer(serializers.HyperlinkedModelSerializer):

    class Meta:
        model = Feed
        fields = ('result', 'message', 'complete')
