from django.db import models
from django.contrib.auth import get_user_model

userModel = get_user_model()

class Prefer(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=20, unique=True)
    image = models.ImageField(default='dafault/default_image.png', upload_to='images/')

    class Meta:
        db_table = 'prefers'
        verbose_name = 'prefer'
        verbose_name_plural = 'prefers'
