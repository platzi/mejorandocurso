from django.http import HttpResponse,HttpResponseRedirect
from django.template.loader import get_template 
from django.template import Context
from datetime import datetime
from django.shortcuts import render_to_response
from demo.forms import *
from django.template.context import RequestContext
from demo.models import *

def hola(request):
    return HttpResponse("Hola al curso")

def post(request,id):
    return HttpResponse("Este es el post %i" % int(id))
    
def hora_actual(request):
    ahora = datetime.now()
    return render_to_response('hora.html', 
                                {'tiempo': ahora, "lista" : range(4)})
            

def index(request):
    libros = Libro.objects.all()
    return render_to_response('galeria.html', context_instance=RequestContext(request,{'libros': libros}))
                        
def agregar(request):
    if request.method == 'POST':
        form = LibroForm(request.POST)
        if form.is_valid():
            form.save()
            return HttpResponseRedirect('/agregar')
    else:
        form = LibroForm()
    return render_to_response('agregar.html', context_instance=RequestContext(request,{'form': form}))
