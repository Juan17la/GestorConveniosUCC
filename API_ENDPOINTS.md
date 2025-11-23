# API Endpoints - Sistema Gestor de Convenios UCC

## Índice
- [Convenios](#convenios)
- [Peticiones](#peticiones)
- [Usuarios](#usuarios)
- [Campus](#campus)
- [Empresas](#empresas)
- [Documentos](#documentos)
- [Alertas de Vencimiento](#alertas-de-vencimiento)
- [Reportes de Empresa](#reportes-de-empresa)

---

## Convenios

### Obtener todos los convenios
```http
GET /api/convenio
```

**Response:**
```json
[
  {
    "id": 1,
    "nombreConvenio": "Convenio Interinstitucional ABC",
    "fechaInicio": "2024-01-15T08:00:00",
    "fechaFinalizacion": "2025-01-15T08:00:00",
    "tipo": "INTERINSTITUCIONAL",
    "estado": "ACTIVO",
    "creadoPorId": 1,
    "creadoPorNombre": "Juan Pérez",
    "campusId": 1,
    "campusNombre": "Campus Pasto",
    "empresaId": 1,
    "empresaNombre": "Empresa XYZ"
  }
]
```

### Obtener convenio por ID
```http
GET /api/convenio/{id}
```

**Response:**
```json
{
  "id": 1,
  "nombreConvenio": "Convenio Interinstitucional ABC",
  "fechaInicio": "2024-01-15T08:00:00",
  "fechaFinalizacion": "2025-01-15T08:00:00",
  "tipo": "INTERINSTITUCIONAL",
  "estado": "ACTIVO",
  "creadoPorId": 1,
  "campusId": 1,
  "empresaId": 1
}
```

### Actualizar convenio
```http
PUT /api/convenio/{id}
```

**Request Body:**
```json
{
  "nombreConvenio": "Convenio Interinstitucional ABC - Actualizado",
  "fechaInicio": "2024-01-15T08:00:00",
  "fechaFinalizacion": "2026-01-15T08:00:00",
  "tipo": "INTERINSTITUCIONAL",
  "estado": "ACTIVO"
}
```

**Response:** Same as GET by ID

### Eliminar convenio
```http
DELETE /api/convenio/{id}
```

**Response:** 204 No Content

### Renovar convenio
```http
POST /api/convenio/renovar/{id}
```

**Request Body:**
```json
{
  "nuevaFechaFinalizacion": "2026-01-15T08:00:00",
  "observaciones": "Renovación aprobada por rectoría"
}
```

**Response:**
```json
{
  "id": 5,
  "convenioId": 1,
  "fechaRenovacion": "2024-11-20T10:30:00",
  "nuevaFechaFinalizacion": "2026-01-15T08:00:00",
  "observaciones": "Renovación aprobada por rectoría"
}
```

---

## Peticiones

### Crear petición de convenio
```http
POST /api/peticion/create
Content-Type: multipart/form-data
```

**Request (Form Data):**
- `empresaRequest` (JSON string):
```json
{
  "nombre": "Empresa XYZ S.A.",
  "nit": "900123456-7",
  "direccion": "Calle 123 #45-67",
  "telefono": "3001234567",
  "representante": "María García"
}
```

- `peticionRequest` (JSON string):
```json
{
  "nombrePeticion": "Convenio de Prácticas Profesionales",
  "fechaInicio": "2024-02-01T08:00:00",
  "fechaFinalizacion": "2025-02-01T08:00:00",
  "tipo": "PRACTICAS_PROFESIONALES",
  "creadoPorId": 1,
  "campusId": 1
}
```

- `minuta` (File): Archivo PDF con la minuta del convenio

**Response:**
```json
{
  "id": 1,
  "nombrePeticion": "Convenio de Prácticas Profesionales",
  "fechaInicio": "2024-02-01T08:00:00",
  "fechaFinalizacion": "2025-02-01T08:00:00",
  "estado": "EN_REVISION_JURIDICA",
  "tipo": "PRACTICAS_PROFESIONALES",
  "creadoPorId": 1,
  "campusId": 1,
  "empresaId": 1
}
```

### Obtener todas las peticiones
```http
GET /api/peticion
```

**Response:**
```json
[
  {
    "id": 1,
    "nombrePeticion": "Convenio de Prácticas",
    "fechaInicio": "2024-02-01T08:00:00",
    "fechaFinalizacion": "2025-02-01T08:00:00",
    "estado": "EN_REVISION_JURIDICA",
    "tipo": "PRACTICAS_PROFESIONALES",
    "creadoPorId": 1,
    "campusId": 1,
    "empresaId": 1
  }
]
```

### Obtener petición por ID
```http
GET /api/peticion/{id}
```

**Response:** Same structure as single item in GET all

### Actualizar petición
```http
PUT /api/peticion/{id}
```

**Request Body:**
```json
{
  "nombrePeticion": "Convenio de Prácticas - Actualizado",
  "fechaInicio": "2024-02-01T08:00:00",
  "fechaFinalizacion": "2025-02-01T08:00:00",
  "estado": "APROBADA_JURIDICA",
  "tipo": "PRACTICAS_PROFESIONALES"
}
```

### Eliminar petición
```http
DELETE /api/peticion/{id}
```

**Response:** 204 No Content

### Aprobar revisión jurídica
```http
POST /api/peticion/revision/juridica/aprobada/{id}
```

**Request Body:**
```json
{
  "aprobada": true,
  "observaciones": "La minuta cumple con los requisitos legales",
  "revisadoPorId": 3
}
```

**Response:**
```json
{
  "id": 1,
  "peticionId": 1,
  "aprobada": true,
  "observaciones": "La minuta cumple con los requisitos legales",
  "revisadoPorId": 3,
  "fechaRevision": "2024-11-20T14:30:00"
}
```

### Firma de convenio - Campus
```http
POST /api/peticion/firma/campus/{id}
Content-Type: multipart/form-data
```

**Request (Form Data):**
- `firmaRequest` (JSON string):
```json
{
  "firmadoPorId": 4,
  "observaciones": "Aprobado por Rectoría Campus Pasto"
}
```

- `firmaCampusDocumento` (File): Documento firmado

**Response:**
```json
{
  "id": 1,
  "peticionId": 1,
  "firmadoPorId": 4,
  "fechaFirma": "2024-11-20T15:00:00",
  "observaciones": "Aprobado por Rectoría Campus Pasto"
}
```

### Firma de convenio - Nacional
```http
POST /api/peticion/firma/nacional/{id}
Content-Type: multipart/form-data
```

**Request (Form Data):**
- `firmaRequest` (JSON string):
```json
{
  "firmadoPorId": 5,
  "observaciones": "Aprobado por Rectoría Nacional"
}
```

- `firmaNacionalDocumento` (File): Documento firmado

**Response:**
```json
{
  "id": 1,
  "peticionId": 1,
  "firmadoPorId": 5,
  "fechaFirma": "2024-11-20T16:00:00",
  "observaciones": "Aprobado por Rectoría Nacional"
}
```

---

## Usuarios

### Crear usuario
```http
POST /api/usuario
```

**Request Body:**
```json
{
  "nombre": "Carlos",
  "apellidos": "Rodríguez",
  "email": "carlos.rodriguez@ucc.edu.co",
  "contrasena": "password123",
  "campusId": 1,
  "rol": "PROFESOR"
}
```

**Response:**
```json
{
  "id": 1,
  "nombre": "Carlos",
  "apellidos": "Rodríguez",
  "email": "carlos.rodriguez@ucc.edu.co",
  "campusId": 1,
  "campusNombre": "Campus Pasto",
  "rol": "PROFESOR",
  "fechaCreacion": "2024-11-20T10:00:00"
}
```

### Obtener todos los usuarios
```http
GET /api/usuario
```

**Response:**
```json
[
  {
    "id": 1,
    "nombre": "Carlos",
    "apellidos": "Rodríguez",
    "email": "carlos.rodriguez@ucc.edu.co",
    "campusId": 1,
    "campusNombre": "Campus Pasto",
    "rol": "PROFESOR",
    "fechaCreacion": "2024-11-20T10:00:00"
  }
]
```

### Obtener usuario por ID
```http
GET /api/usuario/{id}
```

**Response:** Same structure as single item in GET all

### Actualizar usuario
```http
PUT /api/usuario/{id}
```

**Request Body:**
```json
{
  "nombre": "Carlos Alberto",
  "apellidos": "Rodríguez Pérez",
  "email": "carlos.rodriguez@ucc.edu.co",
  "campusId": 1,
  "rol": "COORDINADOR"
}
```

**Response:** Same structure as GET by ID

### Eliminar usuario
```http
DELETE /api/usuario/{id}
```

**Response:** 204 No Content

---

## Campus

### Crear campus
```http
POST /api/campus
```

**Request Body:**
```json
{
  "nombre": "Campus Pasto",
  "direccion": "Calle 18 #47-150"
}
```

**Response:**
```json
{
  "id": 1,
  "nombre": "Campus Pasto",
  "direccion": "Calle 18 #47-150",
  "fechaCreacion": "2024-11-20T09:00:00"
}
```

### Obtener todos los campus
```http
GET /api/campus
```

**Response:**
```json
[
  {
    "id": 1,
    "nombre": "Campus Pasto",
    "direccion": "Calle 18 #47-150",
    "fechaCreacion": "2024-11-20T09:00:00"
  }
]
```

### Obtener campus por ID
```http
GET /api/campus/{id}
```

**Response:** Same structure as single item in GET all

### Actualizar campus
```http
PUT /api/campus/{id}
```

**Request Body:**
```json
{
  "nombre": "Campus Pasto - Actualizado",
  "direccion": "Nueva dirección 123"
}
```

**Response:** Same structure as GET by ID

### Eliminar campus
```http
DELETE /api/campus/{id}
```

**Response:** 204 No Content

---

## Empresas

### Crear empresa
```http
POST /api/empresa
```

**Request Body:**
```json
{
  "nombre": "Tech Solutions S.A.S.",
  "nit": "900456789-1",
  "direccion": "Carrera 10 #20-30",
  "telefono": "3201234567",
  "representante": "Ana María López"
}
```

**Response:**
```json
{
  "id": 1,
  "nombre": "Tech Solutions S.A.S.",
  "nit": "900456789-1",
  "direccion": "Carrera 10 #20-30",
  "telefono": "3201234567",
  "representante": "Ana María López"
}
```

### Obtener todas las empresas
```http
GET /api/empresa
```

**Response:**
```json
[
  {
    "id": 1,
    "nombre": "Tech Solutions S.A.S.",
    "nit": "900456789-1",
    "direccion": "Carrera 10 #20-30",
    "telefono": "3201234567",
    "representante": "Ana María López"
  }
]
```

### Obtener empresa por ID
```http
GET /api/empresa/{id}
```

**Response:** Same structure as single item in GET all

### Actualizar empresa
```http
PUT /api/empresa/{id}
```

**Request Body:**
```json
{
  "nombre": "Tech Solutions S.A.S. - Actualizado",
  "nit": "900456789-1",
  "direccion": "Nueva dirección empresarial",
  "telefono": "3209876543",
  "representante": "Roberto Martínez"
}
```

**Response:** Same structure as GET by ID

### Eliminar empresa
```http
DELETE /api/empresa/{id}
```

**Response:** 204 No Content

---

## Documentos

### Subir documento
```http
POST /api/documento
Content-Type: multipart/form-data
```

**Request (Form Data):**
- `file` (File): Archivo a subir
- `usuario_id` (Long): ID del usuario que sube el documento

**Response:**
```json
{
  "id": 1,
  "nombreOriginal": "minuta_convenio.pdf",
  "url": "https://s3.bucket.com/documentos/abc123.pdf",
  "tipoArchivo": "application/pdf",
  "subidoPorId": 1,
  "fechaSubida": "2024-11-20T11:00:00"
}
```

### Obtener todos los documentos
```http
GET /api/documento
```

**Response:**
```json
[
  {
    "id": 1,
    "nombreOriginal": "minuta_convenio.pdf",
    "url": "https://s3.bucket.com/documentos/abc123.pdf",
    "tipoArchivo": "application/pdf",
    "subidoPorId": 1,
    "fechaSubida": "2024-11-20T11:00:00"
  }
]
```

### Obtener documento por ID
```http
GET /api/documento/{id}
```

**Response:** Same structure as single item in GET all

### Eliminar documento
```http
DELETE /api/documento/{id}
```

**Response:** 204 No Content

---

## Alertas de Vencimiento

### Obtener todas las alertas
```http
GET /api/alerta-vencimiento
```

**Response:**
```json
[
  {
    "id": 1,
    "fechaGenerada": "2024-11-20T08:00:00",
    "convenioId": 1,
    "convenioNombre": "Convenio ABC",
    "enviadaAId": 2,
    "enviadaANombre": "Admin Usuario",
    "estado": "PENDIENTE"
  }
]
```

### Obtener alerta por ID
```http
GET /api/alerta-vencimiento/{id}
```

**Response:** Same structure as single item in GET all

### Obtener alertas por estado
```http
GET /api/alerta-vencimiento/estado/{estado}
```

**Valores posibles para estado:** PENDIENTE, ATENDIDA, RENOVADO, ELIMINADO

**Response:** Array of alerts with the specified status

### Actualizar estado de alerta
```http
PUT /api/alerta-vencimiento/{id}/estado
```

**Request Body:**
```json
{
  "estado": "ATENDIDA"
}
```

**Response:**
```json
{
  "id": 1,
  "fechaGenerada": "2024-11-20T08:00:00",
  "convenioId": 1,
  "convenioNombre": "Convenio ABC",
  "enviadaAId": 2,
  "enviadaANombre": "Admin Usuario",
  "estado": "ATENDIDA"
}
```

---

## Reportes de Empresa

### Crear reporte
```http
POST /api/reportes-empresa
```

**Request Body:**
```json
{
  "convenioId": 1,
  "empresaId": 1,
  "desempeño": "Excelente desempeño del estudiante",
  "actividades": "Desarrollo de aplicación web, pruebas de software",
  "asistencia": "100% asistencia",
  "observaciones": "Estudiante destacado"
}
```

**Response:**
```json
{
  "id": 1,
  "convenioId": 1,
  "empresaId": 1,
  "desempeño": "Excelente desempeño del estudiante",
  "actividades": "Desarrollo de aplicación web, pruebas de software",
  "asistencia": "100% asistencia",
  "observaciones": "Estudiante destacado",
  "fechaCreacion": "2024-11-20T12:00:00"
}
```

### Obtener reportes por convenio
```http
GET /api/reportes-empresa/convenio/{convenioId}
```

**Response:** Array of reports for the specified agreement

### Obtener reportes por empresa
```http
GET /api/reportes-empresa/empresa/{empresaId}
```

**Response:** Array of reports from the specified company

### Obtener reportes por empresa y convenio
```http
GET /api/reportes-empresa/empresa/{empresaId}/convenio/{convenioId}
```

**Response:** Array of reports matching both criteria

### Actualizar reporte
```http
PUT /api/reportes-empresa/{id}
```

**Request Body:**
```json
{
  "desempeño": "Desempeño actualizado",
  "actividades": "Actividades actualizadas",
  "asistencia": "95% asistencia",
  "observaciones": "Observaciones actualizadas"
}
```

**Response:** Same structure as GET by ID

### Eliminar reporte
```http
DELETE /api/reportes-empresa/{id}
```

**Response:** 204 No Content

---

## Enumeraciones

### TipoConvenio
- `INTERINSTITUCIONAL`
- `PRACTICAS_PROFESIONALES`

### EstadoConvenio
- `ACTIVO`
- `VENCIDO`
- `ELIMINADO`

### EstadoPeticion
- `EN_REVISION_JURIDICA`
- `APROBADA_JURIDICA`
- `RECHAZADA_JURIDICA`
- `FIRMADA_CAMPUS`
- `FIRMADA_NACIONAL`
- `APROBADA`
- `RECHAZADA`

### EstadoAlerta
- `PENDIENTE`
- `ATENDIDA`
- `RENOVADO`
- `ELIMINADO`

### Roles
- `PROFESOR`
- `COORDINADOR`
- `JURIDICA`
- `RECTOR_CAMPUS`
- `RECTOR_NACIONAL`
- `ADMIN`

---

## Códigos de Respuesta HTTP

- `200 OK` - Operación exitosa
- `201 Created` - Recurso creado exitosamente
- `204 No Content` - Operación exitosa sin contenido de respuesta
- `400 Bad Request` - Error en la petición
- `404 Not Found` - Recurso no encontrado
- `500 Internal Server Error` - Error del servidor

---

## Ejemplo de uso con JavaScript Fetch API

### GET Request
```javascript
fetch('http://localhost:8080/api/convenio', {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json',
  }
})
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));
```

### POST Request (JSON)
```javascript
fetch('http://localhost:8080/api/usuario', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    nombre: 'Carlos',
    apellidos: 'Rodríguez',
    email: 'carlos@ucc.edu.co',
    contrasena: 'password123',
    campusId: 1,
    rol: 'PROFESOR'
  })
})
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));
```

### POST Request (Multipart Form Data)
```javascript
const formData = new FormData();
formData.append('file', fileInput.files[0]);
formData.append('usuario_id', '1');

fetch('http://localhost:8080/api/documento', {
  method: 'POST',
  body: formData
})
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));
```

### PUT Request
```javascript
fetch('http://localhost:8080/api/convenio/1', {
  method: 'PUT',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    nombreConvenio: 'Convenio Actualizado',
    fechaInicio: '2024-01-15T08:00:00',
    fechaFinalizacion: '2026-01-15T08:00:00',
    tipo: 'INTERINSTITUCIONAL',
    estado: 'ACTIVO'
  })
})
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Error:', error));
```

### DELETE Request
```javascript
fetch('http://localhost:8080/api/convenio/1', {
  method: 'DELETE'
})
  .then(response => {
    if (response.status === 204) {
      console.log('Eliminado exitosamente');
    }
  })
  .catch(error => console.error('Error:', error));
```

---

## Notas Adicionales

1. **Autenticación:** Todos los endpoints requieren autenticación JWT. Incluya el token en el header:
   ```
   Authorization: Bearer {token}
   ```

2. **Formato de Fechas:** Todas las fechas siguen el formato ISO 8601: `yyyy-MM-ddTHH:mm:ss`

3. **Archivos:** Los endpoints que aceptan archivos utilizan `multipart/form-data`. El tamaño máximo de archivo está configurado en el servidor.

4. **Validaciones:** El backend valida todos los campos obligatorios y formatos. Errores de validación retornan código 400 con detalles del error.

5. **CORS:** Asegúrese de que el servidor tenga configurado CORS para permitir peticiones desde el frontend.
