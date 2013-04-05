from django.db import models

class Autor(models.Model):
    nombre = models.CharField(blank=True, max_length=100)
    
    def __unicode__(self):
        return self.nombre

class Libro(models.Model):
    nombre = models.CharField(blank=True, max_length=100) 
    creado = models.DateTimeField(auto_now=True)
    disponible = models.BooleanField(default=True)
    autor = models.ForeignKey(Autor)
    
    def __unicode__(self):
        return self.nombre