from rest_framework import serializers
from api.models.category_models import Category

class CategorySerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.ImageField(max_length=None, use_url=True)

    class Meta:
        model = Category
        fields = ('categoryId', 'image', 'content', 'completeMessage')
