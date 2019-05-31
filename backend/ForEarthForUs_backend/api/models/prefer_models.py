from django.db import models
from django.contrib.auth import get_user_model
from django.db.models.signals import post_delete
from api.utils.media_clean import file_cleanup

userModel = get_user_model()

class Prefer(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=20, unique=True)
    image = models.FileField(null=False, upload_to='images/prefer/')

    class Meta:
        db_table = 'prefers'
        verbose_name = 'prefer'
        verbose_name_plural = 'prefers'

class UserPrefer(models.Model):
    user = models.ForeignKey(userModel, related_name='prefer', on_delete=models.CASCADE)
    prefer = models.OneToOneField(Prefer, on_delete=models.CASCADE)

post_delete.connect(file_cleanup, sender=Prefer, dispatch_uid="prefer.file_cleanup")
