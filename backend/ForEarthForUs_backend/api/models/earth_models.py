from django.db import models
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup

LEVEL_1 = 1
LEVEL_2 = 2
LEVEL_3 = 3
LEVEL_4 = 4
LEVEL_5 = 5
LEVEL_6 = 6
LEVEL_7 = 7
LEVEL_8 = 8
EARTH_LEVEL_CHOICES = (
    (LEVEL_1, 'Earth level 1'),
    (LEVEL_2, 'Earth level 2'),
    (LEVEL_3, 'Earth level 3'),
    (LEVEL_4, 'Earth level 4'),
    (LEVEL_5, 'Earth level 5'),
    (LEVEL_6, 'Earth level 6'),
    (LEVEL_7, 'Earth level 7'),
    (LEVEL_8, 'Earth level 8'),
)

class Earth(models.Model):
    earthLevel = models.IntegerField(
        primary_key=True, unique=True, choices=EARTH_LEVEL_CHOICES)
    image = models.FileField(null=False, upload_to='images/earth/')
    content = models.CharField(max_length=100)

    class Meta:
        db_table = 'earth'
        verbose_name = 'earth'
        verbose_name_plural = 'earth'

post_delete.connect(file_cleanup, sender=Earth, dispatch_uid="earth.file_cleanup")
