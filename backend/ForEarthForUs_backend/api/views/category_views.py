from rest_framework import viewsets
from api.models.category_models import Category
from api.serializers.category_serializer import CategorySerializer
from api.permission import *

class CategoryViewSet(viewsets.ModelViewSet):
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    permission_classes = (permissions.IsAuthenticated,
                          IsAdminOrReadOnly,)
