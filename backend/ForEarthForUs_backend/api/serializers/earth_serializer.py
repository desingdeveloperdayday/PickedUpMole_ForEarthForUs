from rest_framework import serializers
from api.models.earth_models import Earth
from api.utils.validate_svg import validate_svg

class EarthSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True, validators=[validate_svg])

    class Meta:
        model = Earth
        fields = ('earthLevel', 'image', 'content')
