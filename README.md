# API Gestión de Pólizas
Servicio para la gestion de polizas y riesgos asociados.
permitiendo operaciones de creacion, renovacion, cancelacion y consulta. 
Incluye una simulacion de integracion con un sistema CORE externo.

## Tecnologías utilizadas
Java 21, Spring Boot, Maven, Spring Data JPA, Lombok y H2 Database.

## Arquitectura
El proyecto sigue una arquitectura por capas organizada en los siguientes paquetes: 
| config | controller | dto | entity | enums | exceptions | integration | mapper | repository | security | service |

## Seguridad
Todos los endpoints requieren el header `x-api-key: 123456`.

## Endpoints disponibles

### Pólizas
Crear póliza, para probar los demas endpoints, empezar por acá
**POST** `/polizas`

Listar pólizas  
**GET** `/polizas`

Renovar la póliza aplicando incremento del IPC  
**POST** `/polizas/{id}/renovar`

Cancelar póliza y todos los riesgos asociados  
**POST** `/polizas/{id}/cancelar`

Consultar riesgos de una póliza  
**GET** `/polizas/{id}/riesgos`

### Riesgos
Agregar riesgo  
**POST** `/polizas/{id}/riesgos`

Cancelar riesgo  
**POST** `/riesgos/{id}/cancelar`

## Pruebas de endpoints
Para probar los endpoints, en la carpeta `test` se encuentra una colección de Postman. 
Para usarla: 
 - Abre Postman y ve a tu Workspace, haz clic en el botón **Import**, 
 - Selecciona **Upload Files** y carga el archivo JSON de la colección desde tu explorador de archivos. 
 - Postman detectará automáticamente que es una colección válida. 
 - Finalmente haz clic en **Import** y la colección aparecerá en la pestaña **Collections** en la barra lateral izquierda.

## Integración con CORE
El sistema simula integración con un CORE externo mediante el endpoint **POST** `/core-mock/evento`. 
Este endpoint simula el registro en logs del intento de sincronización.

## Manejo de errores
Se implementa un **Global Exception Handler** para centralizar las respuestas de error de la API.

Ejemplo de respuesta:
```json
{
  "mensaje": "No se puede renovar una póliza cancelada",
  "fecha": "2026-03-15T18:10:00",
  "status": 400
}
```

## Ejecutar el proyecto

Clonar repositorio:
```bash
git clone https://github.com/samuel4X/polizas-api
```
Entrar al proyecto:
```bash
cd polizas-api
```
Compilar:
```bash
./mvnw clean install
```
Ejecutar:
```bash
./mvnw spring-boot:run
```
La API quedará disponible en:
```
http://localhost:8080
```