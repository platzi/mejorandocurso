class Estudiante(object):
    
    def __init__(self,nombre_r,edad_r):
        self.nombre = nombre_r
        self.edad = edad_r
    
    def hola(self):
        return "Mi nombre es %s y tengo %i" % (self.nombre, self.edad)

lista_de_alumnos = list()
for es in range(10):
    nombre = "Estudiante %i" % es
    e = Estudiante(nombre,23)
    lista_de_alumnos.append(e)

for es in lista_de_alumnos:
    if es.nombre == "Estudiante 5":
        print "Soy el estudiante 5"
    else:
        print "No soy el estudiante 5"
    
        