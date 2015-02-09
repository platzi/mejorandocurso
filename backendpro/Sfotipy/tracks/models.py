from django.db import models
# Create your models here.
from artists.models import Artist
from albums.models import Album


class Track(models.Model):
	name=models.CharField(max_length=255)
	order=models.PositiveIntegerField()
	track_file=models.FileField(upload_to='tracks')
	artist=models.ForeignKey(Artist)
	album=models.ForeignKey(Album)


	def __unicode__(self):
		return self.name