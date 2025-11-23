**GestorConvenioUcc**

Proyecto backend en Java (Spring Boot) para la gestión de convenios de la Universidad. Este README explica la arquitectura, la comunicación entre paquetes, las tecnologías usadas, cómo construir y ejecutar el proyecto, y referencias útiles.

**Resumen**:
- **Propósito**: API REST para gestionar convenios, empresas, documentos, peticiones y alertas de vencimiento.
- **Lenguaje**: Java 21
- **Framework**: Spring Boot

**Arquitectura / Flujo de comunicación**
- **Cliente -> Controller**: Las peticiones HTTP llegan a las clases en `src/main/java/com/gucc/GestorConvenioUcc/controllers`.
- **Controller -> Service**: Los controladores delegan la lógica de negocio a los servicios (`service`).
- **Service -> Repository**: Los servicios usan `repository` (Spring Data JPA) para persistencia con las entidades en `entity`.
- **DTOs y Mappers**: Para entrada/salida se usan `dto` y mapeos con MapStruct (`mapper`) que convierten entre `DTO` y `Entity`.
- **Configuraciones transversales**: Componentes en `config` manejan configuración global (Jackson, S3, seguridad, scheduler).
- **Almacenamiento de archivos**: `S3Config.java` integra AWS S3 para subir/descargar documentos.
- **Scheduler**: Tareas programadas (e.g., alertas de vencimiento) en `scheduler` y configuradas por `SchedulerConfig`.

Comunicación típica (simplificada):

`HTTP Client -> Controller -> Service -> Repository -> Base de datos`

Y para objetos expuestos:

`Controller <-> DTO <-> MapStruct Mapper <-> Entity`

**Estructura de paquetes (resumen)**
- **`controllers`**: Endpoints REST.
- **`service`**: Reglas de negocio y transacciones.
- **`repository`**: Interfaces Spring Data JPA.
- **`entity`**: Modelos persistidos (JPA entities).
- **`dto`**: Objetos de transferencia entre capas y para la API.
- **`mapper`**: MapStruct mappers que convierten DTO <-> Entity.
- **`config`**: Configuraciones (Jackson, S3, Seguridad, Scheduler).
- **`scheduler`**: Tareas programadas.

**Objetos y Relaciones**
- **Convenio**: Representa un acuerdo. Pertenece a un `Campus` (ManyToOne), puede pertenecer a una `Empresa` (ManyToOne, opcional), es creado por un `Usuario` y puede estar supervisado por otro `Usuario`. Está ligado a una `Peticion` de creación (OneToOne) y tiene colecciones de `Documento`, `ReporteEmpresa`, `AlertaVencimiento` y `RenovacionConvenio` (OneToMany). Los documentos y alertas se eliminan en cascada cuando se borra el convenio (`cascade = ALL`, `orphanRemoval = true`).

- **Empresa**: Contiene datos de la empresa (nombre, NIT, dirección, contacto). Mantiene una relación inversa OneToMany con `Convenio` (una empresa puede tener múltiples convenios) y con `ReporteEmpresa`.

- **Documento**: Contiene metadatos y `url` al archivo (almacenado en S3). Está enlazado a un `Convenio` o a una `Peticion` (ManyToOne, opcional) y registra qué `Usuario` lo subió. Se usa para guardar contratos, anexos y evidencias.

- **Campus**: Entidad que representa la sede; relacionada con `Usuario`, `Peticion` y `Convenio`. Sirve como contexto/ámbito para convenios y peticiones.

- **Usuario**: Representa usuarios del sistema con un `rol` y asociado a un `Campus`. Un usuario puede crear `Peticion` y `Convenio`, supervisar convenios, subir `Documento` y ser destinatario de `AlertaVencimiento`.

- **Peticion**: Solicitud para crear o modificar un `Convenio`. Está asociada a un `Usuario` (creador) y a un `Campus`, puede estar vinculada a una `Empresa`, y tiene relaciones OneToOne con procesos administrativos como `RevisionJuridica`, `FirmaConvenioCampus` y `FirmaConvenioNacional`. También puede contener `Documento` y, tras el proceso, puede dar origen a un `Convenio` (relación inversa OneToOne `convenio`).

- **AlertaVencimiento**: Generada para un `Convenio` y dirigida a un `Usuario` (destinatario). Se usa por el `scheduler` para notificar vencimientos y se almacena con un estado.

- **RenovacionConvenio**: Modelo que representa renovaciones; mantiene referencia a `convenioOriginal` para enlazar renovaciones con el convenio base.

- **ReporteEmpresa**: Informes relacionados con un `Convenio` y una `Empresa` (OneToMany desde `Convenio`, ManyToOne hacia `Empresa`).

Notas técnicas:
- Cardinalidades principales: `Empresa 1 - * Convenio`; `Convenio 1 - * Documento`; `Peticion 1 - 1 Convenio` (tras aprobación); `Convenio 1 - * AlertaVencimiento`.
- Comportamiento de cascada: varias relaciones `OneToMany` en `Convenio` usan `cascade = ALL` y `orphanRemoval = true` para mantener integridad y limpieza de entidades dependientes.
- Almacenamiento de archivos: los `Documento.url` suelen apuntar a objetos en S3; el upload/download se maneja mediante la configuración en `S3Config.java` y el SDK de AWS.


**Tecnologías y dependencias clave**
- **Java 21**: Versión del JDK.
- **Spring Boot 3.x**: Núcleo del framework (web, data, security).
- **Spring Web**: Para construir la API REST.
- **Spring Data JPA**: Acceso a datos y repositorios.
- **PostgreSQL**: Driver incluido en `pom.xml` (configurable en `application.properties`).
- **MapStruct**: Mapeo compile-time DTO <-> Entity (generador en `target/generated-sources`).
- **Lombok**: Reduce boilerplate (`@Getter`, `@Setter`, etc.).
- **AWS SDK v2 (S3)**: Subida/descarga de archivos a S3.
- **Spring Security + JJWT**: Seguridad y manejo de JWT.
- **Maven (wrapper incluido)**: Construcción y gestión de dependencias (`mvnw`, `mvnw.cmd`).
- **JUnit / Spring Test**: Tests (dependencia `spring-boot-starter-test`).

**Archivos importantes**
- `pom.xml`: Dependencias y plugins (MapStruct y procesadores, Lombok, plugin de Spring Boot).
- `src/main/resources/application.properties`: Variables de configuración (BD, S3, seguridad, etc.).
- `API_ENDPOINTS.md`: Documento con los endpoints disponibles (referencia de rutas y payloads).
- 
**Referencias**
- Endpoints y ejemplos de request/response: `API_ENDPOINTS.md`.
- Configuración de S3: `src/main/java/com/gucc/GestorConvenioUcc/config/S3Config.java`.
- Configuración global JSON: `src/main/java/com/gucc/GestorConvenioUcc/config/JacksonConfig.java`.

Si quieres, puedo:
- Añadir ejemplos concretos de `application.properties` con variables de entorno para desarrollo.
- Generar un `README` en inglés además del español.
- Extraer y documentar endpoints automáticamente desde los `Controller`.
