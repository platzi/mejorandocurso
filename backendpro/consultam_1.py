import urllib2

try:
    f = urllib2.urlopen("http://congresorest.appspot.com/diputado/4")
    print f.read()
    f.close()
except HTTPError, e:
   print "Ocurrio un error"
   print e.code
except URLError, e:
   print "Ocurrio un error"
   print e.reason