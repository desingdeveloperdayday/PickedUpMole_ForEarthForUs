from django.db import models
from django.contrib.auth import get_user_model
from api.models.mission_models import Mission

userModel = get_user_model()

RESULT_0 = 0
RESULT_1 = 1
RESULT_2 = 2
RESULT_3 = 3
RESULT_4 = 4
RESULT_5 = 5
FEED_RESULT_CHOICES = (
    (RESULT_1, 'Mission RESULT 0'),
    (RESULT_1, 'Mission RESULT 1'),
    (RESULT_2, 'Mission RESULT 2'),
    (RESULT_3, 'Mission RESULT 3'),
    (RESULT_4, 'Mission RESULT 4'),
    (RESULT_5, 'Mission RESULT 5'),
)

CONTENT_LENGTH = 500

class Feed(models.Model):
    user = models.ForeignKey(userModel, on_delete=models.CASCADE)
    mission = models.ForeignKey(Mission, on_delete=models.CASCADE)
    result = models.IntegerField(choices=FEED_RESULT_CHOICES, default=RESULT_0)
    message = models.CharField(max_length=CONTENT_LENGTH, default='')
    created = models.DateTimeField(auto_now_add=True)
    complete = models.BooleanField(default=False)

    class Meta:
        db_table = 'feeds'
        verbose_name = 'feed'
        verbose_name_plural = 'feeds'
        ordering = ('created',)
