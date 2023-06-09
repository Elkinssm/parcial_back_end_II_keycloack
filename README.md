# Documentación del Proyecto - Microservicios con Keycloak, Gateway y Discovery
---
# Elkin Stiven Silva Manrique
---
## Resumen
Este proyecto consta de varios componentes principales: un microservicio (ms-bills), un Gateway(ms-gateway) y un Discovery Service(ms-discovery). Cada uno de estos componentes tiene una función específica y se comunica con los demás para proporcionar una aplicación completa y segura.
El proyecto utiliza Keycloak como su sistema de gestión de identidad y acceso. Keycloak es un producto de código abierto que permite la autenticación y autorización de usuarios, proporcionando características como el inicio de sesión único (Single Sign-On, SSO), la gestión de identidades y la gestión de permisos.
---
## Componentes
---
### Microservicio "Bills"
---
El microservicio "Bills" es responsable de la gestión de las facturas de los clientes en el sistema de comercio electrónico. Este microservicio proporciona una API REST que expone las funcionalidades para interactuar con las facturas.
---
### Endpoints
El servicio expone los siguientes endpoints:
---
GET /bills/all - Este endpoint devuelve una lista de todas las facturas en el sistema. Este endpoint requiere que el usuario esté autenticado y tenga el rol "USER".
---
### Configuración
La configuración para este microservicio se realiza a través de un archivo de propiedades. Las propiedades incluyen la configuración del servidor, la seguridad de Spring, la base de datos H2, JPA, y la configuración del cliente de Eureka y Keycloak.
---
### Configuración del servidor
El microservicio se ejecuta en el puerto 8081 y tiene un contexto de servlet en /api/v1/.
---
### Configuración de Seguridad Keycloak
Este proyecto utiliza Keycloak para la autenticación y autorización de usuarios. Los tokens emitidos por Keycloak son tokens JWT, y el proyecto utiliza una configuración personalizada para decodificar estos tokens y convertirlos en objetos de autenticación utilizables en el sistema.
---
### Clase `KeyCloakJwtAuthenticationConverter`
Esta clase implementa la interfaz `Converter` y se utiliza para convertir un `Jwt` en un `AbstractAuthenticationToken`. Esto permite al sistema de seguridad de Spring interpretar correctamente los tokens JWT emitidos por Keycloak. 
Esta clase realiza varias funciones:
- Extrae los roles del token JWT que se encuentran en varios lugares dentro del token, como el acceso a recursos (`resource_access`), el acceso al reino (`realm_access`) y el auditor (`aud`).
- Crea instancias de `GrantedAuthority` a partir de los roles extraídos. Los roles se convierten en objetos `GrantedAuthority` que se utilizan en el sistema de seguridad de Spring para representar las autoridades concedidas a un usuario autenticado.
- Combina las autoridades concedidas por defecto (si las hay) con las autoridades extraídas del token JWT.
---
### Clase `OAuth2ResourceServerSecurityConfiguration`
Esta clase es una clase de configuración de Spring que configura la seguridad de la aplicación. 
Establece la política de creación de sesiones en `SessionCreationPolicy.STATELESS`, lo que significa que el servidor no mantendrá ninguna información de sesión entre las solicitudes. 
Además, configura el servidor## Configuración de seguridad de Keycloak
---
### Configuración de la base de datos
El microservicio utiliza una base de datos H2 en memoria para el almacenamiento de datos. La base de datos se configura para permanecer abierta hasta que la JVM se cierre.
---
### Configuración de JPA
El microservicio utiliza JPA para la persistencia de datos con Hibernate como proveedor. Se configura para mostrar las consultas SQL y actualizar automáticamente el esquema de la base de datos para reflejar las entidades JPA.
---
### Configuración de Eureka
El microservicio se registra con un servidor Eureka en http://localhost:8761/eureka.
---
# Configuración del Servicio Discovery (Eureka Server)

El servicio `ms-discovery` actúa como un servidor de registro y descubrimiento de servicios, utilizando Netflix Eureka. 

Esta es la configuración relevante para `ms-discovery`:

- `spring.application.name`: Nombre de la aplicación, en este caso, es `ms-discovery`.
- `server.port`: Puerto en el que se ejecuta el servidor, en este caso, es el puerto `8761`.

La configuración de Eureka se desglosa de la siguiente manera:

- `eureka.instance.hostname`: Define el nombre del host en el que se ejecuta el servidor Eureka, que es `localhost` en este caso.
- `eureka.instance.prefer-ip-address`: Esta propiedad, cuando se establece en `true`, le dice al servidor Eureka que utilice la dirección IP del servicio en lugar del nombre del host cuando registra el servicio.

En cuanto a la configuración del cliente de Eureka:

- `eureka.client.fetch-registry`: Cuando se establece en `false`, indica que este servidor no necesita obtener el registro de Eureka de otros servidores Eureka.
- `eureka.client.register-with-eureka`: Cuando se establece en `false`, indica que este servidor no necesita registrarse con otros servidores Eureka. Esto tiene sentido aquí ya que este servidor es el servidor de registro.

En resumen, `ms-discovery` es un servidor Eureka que permite a los servicios registrarse con él y descubrir otros servicios a través de él. Sin embargo, como es el único servidor de Eureka en este sistema, no necesita registrarse ni obtener registros de otros servidores de Eureka.

