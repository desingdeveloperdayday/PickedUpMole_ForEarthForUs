from django.db import models
from django.contrib.auth import get_user_model
from api.models.campaign_models import Campaign
from api.models.article_models import Article

CAMPAIGN = 1
ARTICLE = 2
SCRAP_TYPE_CHOICE = (
    (CAMPAIGN, 'Campaign type scrap'),
    (ARTICLE, 'Article type scrap'),
)

userModel = get_user_model()

class Scrap(models.Model):
    id = models.CharField(max_length=100, primary_key=True, unique=True)
    user = models.ForeignKey(userModel, on_delete=models.CASCADE)
    kind = models.IntegerField(choices=SCRAP_TYPE_CHOICE)
    campaign = models.ForeignKey(Campaign, on_delete=models.CASCADE, null=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE, null=True)
    created = models.DateTimeField(auto_now_add=True)

    class Meta:
        db_table = 'scraps'
        verbose_name = 'scrap'
        verbose_name_plural = 'scraps'
