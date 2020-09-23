# redis_implementation
Proyecto desarrollado con Spring Boot, se implementa una instancia de redis dockerizado y una bd H2, el objetivo es compartir el conocimiento basico para implementar una aplicacion poliglota capaz de conectarse a una instancia de base de datos H2 y una base de datos no relacional en cache como Redis. La implementacion podria ser similar al integrar un motor de base de datos persistente en disco.

Para efectos de la demostracion se manejan datos basicos de usuario en una sola tabla, el acceso a datos H2 se realiza mediante JPA. El tratamiento de las operaciones en redis se realizan por Hash.


## Comenzando 🚀
* Ejecutar aplicacion en IDE, la aplicación va a correr en el puerto 8080 configurado en application.properties
* Dockerizar instancia de Redis en el puerto 4025:

```
docker run --rm -p 4025:6379 -d --name redis-test redis redis-server
```


## Construido con 🛠️

* Spring boot
* Maven


## Autores ✒️

* **Matias Pinto Ramirez** 


