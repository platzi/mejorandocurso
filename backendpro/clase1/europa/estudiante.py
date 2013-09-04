
class Estudiante(object):
    
    def __init__(self,nombre_r,edad_r):
        self.nombre = nombre_r
        self.edad = edad_r
        
    def hola(self):
        return "Mi nombre es %s mi edad es %i" % (self.nombre,self.edad)

e = Estudiante("Arturo", 23)
e2 = Estudiante("Freddier", 27)
resultado = e.hola()
resultado2 = e2.hola()
print "%s \n%s" % (resultado,resultado2)
if(e == e2):
    print "son identicos"
else:
    print "no son identicos"