from rest_framework import serializers
from api.models.category_models import Category
from api.utils.validate_svg import validate_svg

class CategorySerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True, validators=[validate_svg])

    class Meta:
        model = Category
        fields = ('categoryId', 'image', 'completeMessage')
