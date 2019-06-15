# Generated by Django 2.2.2 on 2019-06-15 06:53

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0002_donation'),
    ]

    operations = [
        migrations.CreateModel(
            name='Campaign',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=50)),
                ('subTitle', models.CharField(max_length=100)),
                ('image', models.FileField(upload_to='images/campaign/')),
                ('link', models.URLField(max_length=256)),
            ],
            options={
                'verbose_name': 'campaign',
                'verbose_name_plural': 'campaigns',
                'db_table': 'campaigns',
            },
        ),
    ]