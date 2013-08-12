from django import forms
from demo.models import *

class AutorForm(forms.ModelForm): 
    class Meta:
        model = Autor
        
class LibroForm(forms.ModelForm): 
    class Meta:
        model = Libro