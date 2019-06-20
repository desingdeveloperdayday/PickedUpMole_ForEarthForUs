from django.db import models
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup

class Article(models.Model):
    title = models.CharField(max_length=50)
    subTitle = models.CharField(max_length=100)
    image = models.FileField(null=False, upload_to='images/article/')
    link = models.URLField(max_length=256)

    class Meta:
        db_table = 'articles'
        verbose_name = 'article'
        verbose_name_plural = 'articles'

post_delete.connect(file_cleanup, sender=Article, dispatch_uid="article.file_cleanup")
