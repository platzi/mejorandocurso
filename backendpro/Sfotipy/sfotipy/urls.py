from django.conf.urls import patterns, include, url

from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'sfotipy.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^admin/', include(admin.site.urls)),
    url(r'^tracks/','tracks.views.tracks_view',name='tracks_view'),
    url(r'^json/','tracks.views.json_view',name='json_view'),
    url(r'^track/(?P<name>[-\w]+)/$','tracks.views.track_view',name='track_view'),
	url(r'^signup/','UserProfile.views.signup_view',name='signup_view'),
	url(r'^sigin/','UserProfile.views.sigin_view',name='sigin_view'),
)
