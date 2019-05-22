from django.db import models
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup
from api.models.category_models import Category

TITLE_LENGTH=50
CONTENT_LENGTH=256

class Mission(models.Model):
    category = models.ForeignKey(Category, on_delete=models.CASCADE)
    image = models.ImageField(null=False, upload_to='images/mission/')
    title = models.CharField(max_length=TITLE_LENGTH)
    content = models.CharField(max_length=CONTENT_LENGTH)
    missionTipTitle = models.CharField(max_length=TITLE_LENGTH)
    missionTipContent = models.CharField(max_length=CONTENT_LENGTH)
    missionMethodTitle = models.CharField(max_length=TITLE_LENGTH)
    missionMethodContent = models.CharField(max_length=CONTENT_LENGTH)
    missionEffectTitle = models.CharField(max_length=TITLE_LENGTH)
    missionEffectContent = models.CharField(max_length=CONTENT_LENGTH)

    class Meta:
        db_table = 'missions'
        verbose_name = 'mission'
        verbose_name_plural = 'missions'

post_delete.connect(file_cleanup, sender=Mission, dispatch_uid="mission.file_cleanup")
