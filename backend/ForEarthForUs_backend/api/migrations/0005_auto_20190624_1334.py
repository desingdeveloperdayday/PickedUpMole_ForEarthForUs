# Generated by Django 2.2.2 on 2019-06-24 13:34

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0004_article'),
    ]

    operations = [
        migrations.AlterField(
            model_name='article',
            name='link',
            field=models.URLField(max_length=256, unique=True),
        ),
        migrations.AlterField(
            model_name='campaign',
            name='link',
            field=models.URLField(max_length=256, unique=True),
        ),
        migrations.AlterField(
            model_name='donation',
            name='link',
            field=models.URLField(max_length=256, unique=True),
        ),
    ]