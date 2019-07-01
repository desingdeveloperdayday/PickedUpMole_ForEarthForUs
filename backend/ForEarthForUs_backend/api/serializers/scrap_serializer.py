from rest_framework import serializers
from api.models.scrap_models import Scrap
from api.serializers.campaign_serializer import CampaignSerializer
from api.serializers.article_serializer import ArticleSerializer

class ScrapSerializer(serializers.ModelSerializer):
    campaign = CampaignSerializer()
    article = ArticleSerializer()

    class Meta:
        model = Scrap
        fields = ('id', 'kind', 'campaign', 'article')

class ScrapCampaignSerializer(serializers.ModelSerializer):

    class Meta:
        model = Scrap
        fields = ('id', 'kind', 'campaign')

class ScrapArticleSerializer(serializers.ModelSerializer):

    class Meta:
        model = Scrap
        fields = ('id', 'kind', 'article')
