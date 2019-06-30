from rest_framework import serializers
from api.models.campaign_models import Campaign
from api.utils.validate_svg import validate_image_extension

class CampaignSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True,
                                  validators=[validate_image_extension])

    class Meta:
        model = Campaign
        fields = ('id', 'title', 'subTitle', 'image', 'link')
