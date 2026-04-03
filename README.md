# Sistema de Control de Acceso y Aforo – Red Dan Technology (Proyecto de Aula)

## 📌 Descripción del Problema
Debido a la situación sanitaria COVID-19, Red Dan Technology necesita implementar protocolos de bioseguridad.  
Se requiere un sistema para el control de entrada de visitantes y empleados, con el fin de obtener un registro exacto de cuántas personas ingresan y cuántas se encuentran en las instalaciones, respetando el aforo máximo permitido.

## 🎯 Propósito del Sistema
El sistema permite:
- Registrar la entrada y salida de empleados y visitantes.
- Monitorear el estado de salud de las personas al ingresar.
- Controlar el número de personas dentro de la empresa según aforo.
- Permitir acciones CRUD (Crear, Leer, Actualizar, Eliminar) en los registros.

### Alcance
- Validación de usuario y clave de acceso.
- Registro de datos de empleados y visitantes.
- Consultas y filtros de información (por nombre, identificación o área).

### Lo que el sistema **no** contempla
- Consultas avanzadas por múltiples filtros, aunque sí permite filtrar por nombre.

---

## 🧑‍💻 Actores
- **Proveedor:** Creador del software para el cliente.  
- **Administrador:** Persona que maneja el sistema.  
- **Cliente:** Empresa que solicita el sistema.  
- **Usuario Final:** Personas involucradas en el uso del sistema.  
- **Sistema:** Procesos automáticos que realiza el software.

---

## 📋 Requerimientos Funcionales

| Código | Nombre | Descripción | Usuario | Prioridad |
|--------|--------|------------|---------|-----------|
| RF-1 | Información General | Registro de entradas y salidas de empleados y visitantes con todos sus datos. | Administrador, Sistema | Alta |
| RF-2 | Usuario y Clave de Acceso | Validación de usuario y contraseña para ingresar al sistema. | Administrador, Sistema | Alta |
| RF-3 | Validación de Datos | Validar tipo de dato ingresado en cada campo. | Sistema | Alta |
| RF-4 | Registro de Empleados | Guardar datos de empleados incluyendo estado de salud. | Administrador, Sistema | Alta |
| RF-5 | Registro de Visitantes | Guardar datos de visitantes y terceros incluyendo estado de salud. | Usuario, Sistema | Alta |
| RF-6 | Filtro de Datos | Permite filtrar por nombre, identificación y área. | Usuario, Sistema | Alta |
| RF-7 | Acciones en el Sistema | CRUD completo sobre los registros. | Usuario, Sistema | Alta |
| RF-8 | Mostrar Registros | Mostrar empleados y visitantes ingresados y salidos. | Sistema | Alta |
| RF-9 | Sistema de Interfaces | Contar con interfaces: ingreso, empleados, visitantes, reportes. | Administrador | Media |
| RF-10 | Verificación de Información | Mensajes de confirmación al ingresar datos. | Administrador, Sistema | Media |
| RF-11 | Almacenamiento de Datos | Guardar todos los registros en la base de datos. | Administrador, Sistema | Alta |
| RF-12 | Registro de Salida | Registrar la hora de salida automáticamente. | Administrador, Sistema | Alta |
| RF-13 | Encuesta de Ingreso | Realizar encuesta de síntomas COVID-19 antes de ingresar. | Administrador | Alta |

---

## 🛠️ Tecnologías Utilizadas
- Lenguaje principal: **Java ** 
- Base de datos: **PostgreSQL**  
- Frontend: **HTML, CSS**  

---

## 📂 Estructura del Proyecto
proyecto-aula/
│
├── REQUERIMIENTOS/ ← Documentos del proyecto
├── DISENO/ ← Diagramas, bocetos
├── BD/ ← Base de datos
├── LIBRERIAS/ ← Librerías usadas
├── EL PROYECTO/ ← Código fuente
└── README.md ← Este archivo
---

## 🚀 Funcionalidades Principales
- Registro de ingreso y salida de empleados y visitantes.  
- Validación de usuario y contraseña.  
- Filtrado y consulta de registros.  
- CRUD completo de los datos.  
- Registro de síntomas COVID-19.  
- Mensajes de verificación y control de aforo.  

---

<img width="1263" height="691" alt="image" src="https://github.com/user-attachments/assets/8cdc3582-c8da-4a7d-9b62-ec14092cc89d" />
<img width="1183" height="647" alt="image" src="https://github.com/user-attachments/assets/6dd6644e-e762-4743-8346-d209536d0278" />

---

## 💡 Nota
Este proyecto fue desarrollado como **proyecto de aula** para Red Dan Technology, con fines educativos y de práctica profesional. 

---


