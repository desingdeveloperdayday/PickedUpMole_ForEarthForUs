from django.db import models
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup

class Campaign(models.Model):
    title = models.CharField(max_length=50)
    subTitle = models.CharField(max_length=100)
    image = models.FileField(null=False, upload_to='images/campaign/')
    link = models.URLField(max_length=256, unique=True)

    class Meta:
        db_table = 'campaigns'
        verbose_name = 'campaign'
        verbose_name_plural = 'campaigns'

post_delete.connect(file_cleanup, sender=Campaign, dispatch_uid="campaign.file_cleanup")
