from rest_framework import serializers
from api.models.article_models import Article
from api.utils.validate_svg import validate_image_extension
from api.models.scrap_models import Scrap

class ArticleSerializer(serializers.HyperlinkedModelSerializer):
    image = serializers.FileField(max_length=None, use_url=True,
                                  validators=[validate_image_extension])
    # TODO: crawling data

    class Meta:
        model = Article
        fields = ('id', 'title', 'subTitle', 'image', 'link')
    
    def to_representation(self, instance):
        data = super(ArticleSerializer, self).to_representation(instance)
        data['scrap'] = update_status(self.context['request'].user.id, data['id'])
        return data

def update_status(userId, articleId):
    scrap = Scrap.objects.filter(
                user=userId,
                kind=2,
                article=articleId
            )

    val = False
    if scrap:
        val = True
    else:
        val = False

    return val