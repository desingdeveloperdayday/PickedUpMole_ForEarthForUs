from rest_framework import viewsets
from api.models.article_models import Article
from api.serializers.article_serializer import ArticleSerializer
from api.permission import *

class ArticleViewSet(viewsets.ModelViewSet):
    queryset = Article.objects.all()
    serializer_class = ArticleSerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)

# TODO: Article detail view