from rest_framework import serializers
from api.models.earth_models import Earth

class EarthSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Earth
        fields = ('earthLevel', 'image', 'content')
