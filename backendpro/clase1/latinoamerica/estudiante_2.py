class Estudiante(object):
    
    def __init__(self,nombre_r,edad_r):
        self.nombre = nombre_r
        self.edad = edad_r
    
    def hola(self):
        return "Mi nombre es %s y tengo %i" % (self.nombre, self.edad)

e = Estudiante("Arturo",23)
s = e.hola()
print s
        