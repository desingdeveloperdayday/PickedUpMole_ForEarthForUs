from rest_framework import serializers
from api.models.campaign_models import Campaign
from api.utils.validate_svg import validate_image_extension
from api.models.scrap_models import Scrap

class CampaignSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True,
                                  validators=[validate_image_extension])

    class Meta:
        model = Campaign
        fields = ('id', 'title', 'subTitle', 'image', 'link')

    def to_representation(self, instance):
        data = super(CampaignSerializer, self).to_representation(instance)
        data['scrap'] = update_status(self.context['request'].user.id, data['id'])
        return data

def update_status(userId, campaignId):
    scrap = Scrap.objects.filter(
                user=userId,
                kind=1,
                campaign=campaignId
            )

    val = False
    if scrap:
        val = True
    else:
        val = False

    return val