from rest_framework import serializers
from api.models.article_models import Article
from api.utils.validate_svg import validate_image_extension

class ArticleSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True,
                                  validators=[validate_image_extension])
    # TODO: crawling data

    class Meta:
        model = Article
        fields = ('id', 'title', 'subTitle', 'image', 'link')
