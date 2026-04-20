# Herramienta de Gestion Medica

<img width="345" float="left" alt="image" src="https://github.com/user-attachments/assets/140a7b07-f7b5-41be-ab09-91e814bc72b3" />

<img width="345" float="left" alt="image" src="https://github.com/user-attachments/assets/4d453f87-1c46-4f02-b9d7-92130edd6a99" />

<img width="345" float="left" alt="image" src="https://github.com/user-attachments/assets/76e4b408-38ad-45b9-9098-2cc76654c8da" />

<img width="345"  float="left" height="530" alt="image" src="https://github.com/user-attachments/assets/bb239619-354b-4fc9-b820-a942cd4d0972" />



Aplicacion de escritorio en Java Swing para gestionar usuarios, especialidades y consultas de datos locales, con una vista adicional para consultar una red medica global desde API.

Este repositorio esta armado como proyecto NetBeans (Ant), con formularios Swing generados y logica de negocio escrita a mano sobre JDBC.

## Alcance actual del proyecto

- Registro de usuarios con validaciones de cedula, nombre, correo, sexo, edad y foto.
- Clasificacion por tipo de usuario: Paciente, Doctor o Enfermero.
- Relacion usuario-especialidad mediante tabla puente.
- Consulta local con filtros visuales por nombre, orden y especialidad.
- Aplicacion de ejercicio academico con Streams sobre nombres (filtro por longitud mayor a 5 y orden alfabetico).
- Vista de ficha por doble clic y exportacion de ficha en HTML.
- Consulta de directorio global (RandomUser API) para exploracion de datos externos.

## Scaffolding del proyecto

Estructura principal (carpetas clave):

- src/directorio_medico
  - Menu.java
  - cargarUsuario.java
  - cargarEspecialidad.java
  - consulta.java
  - imprimir.java
  - pdf.java
  - DirectorioMedico.java
  - conexion.java
  - Subida.java
- src/Fotos
  - destino de fotos copiadas al registrar o actualizar usuario
- src/reportes
  - destino de fichas HTML exportadas
- nbproject
  - configuracion de NetBeans y clase principal del proyecto
- build.xml
  - script de Ant generado por NetBeans

Punto de entrada actual:

- main.class = directorio_medico.Menu
- archivo: nbproject/project.properties

## Flujo funcional

1. Arranque en Menu.
2. Desde Menu se navega a:
   - Registro/actualizacion de usuarios.
   - Registro de especialidades.
   - Consulta de datos locales.
   - Consulta de red medica global.
3. En Consulta local:
   - Se cargan usuarios y especialidades.
   - Se pueden aplicar filtros visuales.
   - Doble clic abre ficha del usuario.

## Consideraciones tecnicas

### 1) Persistencia y conexion

- La conexion esta centralizada en conexion.java mediante un Connection estatico.
- La app lee credenciales desde src/env.json.
- Si falla la configuracion o la conexion, se notifica por UI y se detiene la ejecucion.

-- Los datos de conexión los toma de env.json

### 2) Evolucion de esquema de BD

Se normalizan nulos de TIPO_USUARIO a Paciente.

### 3) Modelo de datos

- USUARIOS
  - CEDULA (clave de negocio)
  - NOMBRES, SEXO, FECHA_NAC, CORREO, FOTO, RUTA_FOTO, TIPO_USUARIO
- ESPECIALIDAD
  - ID_ESPECIALIDAD (PK), NOM_ESPECIALIDAD
- USUARIO_ESPECIALIDAD
  - ID (PK), CEDULA, ID_ESPECIALIDAD

La consulta usa LEFT JOIN para no perder usuarios sin especialidad asignada.

### 4) Validacion y UX

- Validacion en tiempo real con bordes de error en cargarUsuario.java a medida que se teclea en los campos.
= Limpieza de tablas una vez enviados los datos
- Las reglas de negocio y validaciones de datos estan en validaciones de estado de controles y en regex, tambien el consumo de json se usa.
- En consulta.java se uso TableRowSorter + RowFilter para evitar recargar todo desde BD por cada filtro.

### 5) Streams aplicados al caso real

La actividad de lambdas con stream se integró en la clase de consulta:

- Se toma la coleccion de nombres desde la tabla cargada.
- Se normaliza a minuscula.
- Se filtra por longitud mayor a 5.
- Se ordena alfabeticamente.
- Se imprime resultado en consola.

Esto permitio aterrizar el ejercicio de clase en un flujo funcional de producto.

### 6) Exportacion de ficha

pdf.java genera un archivo HTML por cedula en src/reportes.

Nota practica:
- Aunque la clase se llama pdf, el artefacto generado es HTML por motivo de práctica.

### 7) Integracion con API externa

DirectorioMedico.java consume el api RandomUser con HttpURLConnection y se parsea manualmente el JSON a través de regex.

## Documentacion de clases

### Menu.java

- Pantalla de navegacion principal.

### conexion.java

- Apertura de conexion JDBC y carga de configuracion.

- Lee src/env.json.
- Mantiene Connection estatico usado por formularios.

### cargarUsuario.java

- Manejo de foto (copia fisica en src/fotos y blob en BD).
- Validaciones en tiempo real.
- Sincroniza USUARIO_ESPECIALIDAD segun tipo de usuario.
- Incluye adaptaciones de esquema para columnas dependiendo el tipo de paciente.

### cargarEspecialidad.java

- Inserta especialidades

### consulta.java

- Listado local de usuarios con joins, filtros y navegacion a ficha.
- Carga usuarios con tipo y especialidad.
- Filtro visual por nombre/especialidad y orden por nombre.
- Check para actividad de Streams integrada.
- Doble clic abre imprimir.java.

### imprimir.java

-- Archivo de pruebas para un ejercicio de clase externo.
- Vista de ficha de usuario para lectura.
- Recibe datos desde consulta y los muestra bloqueados para edicion.

### pdf.java

- Exportar ficha por cedula.
- Consulta datos en BD y genera archivo HTML en src/reportes.

### DirectorioMedico.java

- Consulta externa de medicos de ejemplo via API RandomUser.
- Uso de SwingWorker para no congelar UI.
- Carga tabla con nombre, genero, pais, email y telefono.

### Subida.java

- Soporte visual para seleccion de archivo en flujo de foto.

- Contiene un JFileChooser embebido.

## Requisitos de ejecucion

- Java 8 o superior (proyecto configurado con source/target 1.8).
- MySQL disponible y accesible.
- Dependencias JDBC presentes en src:
  - mysql-connector-j-8.4.0.jar
  - derbyclient.jar


## Como ejecutar

En NetBeans (recomendado):

1. Abrir carpeta del proyecto.
2. Confirmar que la clase principal sea directorio_medico.Menu.
3. Ejecutar Run Project.

Desde linea de comandos:

- Este repo usa Ant, pero en algunos equipos Ant no viene en PATH por defecto.
- Si el comando ant falla en terminal, ejecutar desde NetBeans evita ese bloqueo.
