from django.db import models
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup

class Donation(models.Model):
    name = models.CharField(max_length=50)
    image = models.FileField(null=False, upload_to='images/donation/')
    link = models.URLField(max_length=256, unique=True)

    class Meta:
        db_table = 'donations'
        verbose_name = 'donation'
        verbose_name_plural = 'donations'

post_delete.connect(file_cleanup, sender=Donation, dispatch_uid="donation.file_cleanup")
