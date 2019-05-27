from django.db import models
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup

class Category(models.Model):
    categoryId = models.IntegerField(primary_key=True, unique=True)
    image = models.ImageField(null=False, upload_to='images/category/')
    content = models.CharField(max_length=100)
    completeMessage = models.CharField(max_length=100)

    class Meta:
        db_table = 'categories'
        verbose_name = 'category'
        verbose_name_plural = 'categories'

post_delete.connect(file_cleanup, sender=Category, dispatch_uid="category.file_cleanup")
