
class Estudiante(object):
    
    def __init__(self,nombre_r,edad_r):
        self.nombre = nombre_r
        self.edad = edad_r
    
    def __str__(self):
        return "%s" % self.nombre
    
    def hola(self):
        return "Mi nombre es %s mi edad es %i" % (self.nombre,self.edad)

e = Estudiante("Arturo", 23)
e2 = Estudiante("Freddier", 27)

lista_alumnos = list()
for num_es in range(10):
    nombre = "alumno %i" % num_es
    e = Estudiante(nombre, 23)
    lista_alumnos.append(e)

# print lista_alumnos

for al in lista_alumnos:
    print al.nombre
