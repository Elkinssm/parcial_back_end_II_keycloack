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
###Configuración de Eureka
El microservicio se registra con un servidor Eureka en http://localhost:8761/eureka.
---
