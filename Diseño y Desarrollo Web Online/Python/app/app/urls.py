from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    url(r'^$', 'demo.views.index', name='index'),
    # url(r'^app/', include('app.foo.urls')),
    url(r'^hola/$', 'demo.views.hola', name='hola'),
    url(r'^post/(\d{1,2})/$', 'demo.views.post', name='post'),
    url(r'^hora_actual/$', 'demo.views.hora_actual', name='hora_actual'),
    url(r'^agregar/$', 'demo.views.agregar', name='agregar'),
    
    
    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    url(r'^admin/', include(admin.site.urls)),
)
