from django.shortcuts import render,get_object_or_404
from django.http import HttpResponse, Http404
from .models import Track
import json
# Create your views here.

def tracks_view(request):
	return HttpResponse('ok')

def track_view(request,name):
	# Bloque de codigo comentado para hacer con el get_object_or_404
	''' 
	try:
		track=Track.objects.get(name=name)
	except Track.DoesNotExist:
		raise Http404
	'''
	print 'yes'
	print name
	track=get_object_or_404(Track,name=name)
	return render (request,'track.html',{'track':track})

def json_view(request):
	name='cancion'
	track=get_object_or_404(Track,name=name)
	data={'title':track.name,
	'order':track.order,
	'album':track.album.title,
	'artist':{'name':track.artist.first_name,'bio':track.artist.biography}}
	json_data=json.dumps(data) #Transforma de PYTHON a JSON
	# json.loads(data) TRANSFORMA de JSON A PYTHON
	return HttpResponse(json_data,content_type='application/json')
