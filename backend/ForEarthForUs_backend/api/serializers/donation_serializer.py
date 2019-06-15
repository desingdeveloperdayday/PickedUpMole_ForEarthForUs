from rest_framework import serializers
from api.models.donation_models import Donation
from api.utils.validate_svg import validate_image_extension

class DonationSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True,
                                validators=[validate_image_extension])

    class Meta:
        model = Donation
        fields = ('name', 'image', 'link')
