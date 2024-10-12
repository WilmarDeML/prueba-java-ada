# prueba-java-ada

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.0-green)

Propuesta de solución a la prueba hecha por ada para desarrollador java.

## Tabla de Contenidos
- [Descripción](#Descripción)
- [Características](#Características)
- [Requisitos previos](#Requisitos-previos)
- [Instalación](#Instalación)
- [Ejecución](#Ejecución)
- [Uso](#Uso)
- [Pruebas](#Pruebas)

## Descripción
Este proyecto es una aplicación basada en **Spring Boot** que da solución a la propuesta de prueba por ada para desarrollador java.

## Características
- Relación de tablas en el modelo ER [`Diagrama`](https://lucid.app/lucidchart/34251402-a1c6-4352-84f1-591d292902a0/edit?viewport_loc=-3316%2C-632%2C1664%2C750%2C0_0&invitationId=inv_3cebc963-867b-48dd-821d-f110d565cda3)
  ![image](https://github.com/user-attachments/assets/b863ccff-f393-4e26-b52c-f8ba70b2c201)
- Script para crear la tabla version_company (postgresql)
  ```sql
    create table version_company (
       version_company_id int8 primary key,
       company_id int8 not null,
       version_id int8 not null,
       version_company_description varchar(255) NULL,
       unique (company_id),
       foreign key (company_id) references company(id_company) on delete cascade,
       foreign key (version_id) references version(version_id) on delete cascade
    );
  ```
- Procedimiento para inserción de datos en las tablas del modelo
  ```sql
    CREATE OR REPLACE FUNCTION insertar_datos_desde_tmp()
    RETURNS VOID language plpgsql AS
    $$
    DECLARE
        -- Definimos el cursor para recorrer la tabla TMP_LLENAR_CAMPOS
        CTemporal CURSOR FOR
        SELECT * FROM TMP_LLENAR_CAMPOS;
    
        -- Variables para almacenar temporalmente los datos de la tabla TMP_LLENAR_CAMPOS
        tmp_record TMP_LLENAR_CAMPOS%ROWTYPE;
    BEGIN
        -- Abrimos el cursor
        OPEN CTemporal;
    
        -- Recorremos cada fila de la tabla TMP_LLENAR_CAMPOS
        LOOP
            -- Obtener una fila del cursor
            FETCH CTemporal INTO tmp_record;
            EXIT WHEN NOT FOUND;
    
            -- Insertar datos en la tabla Company si no existe
            IF NOT EXISTS (SELECT 1 FROM Company WHERE id_company = tmp_record.id_company) THEN
                INSERT INTO Company (id_company, codigo_company, name_company, description_company)
                VALUES (tmp_record.id_company, tmp_record.codigo_company, tmp_record.name_company, tmp_record.description_company);
            END IF;
    
            -- Insertar datos en la tabla Application si no existe
            IF NOT EXISTS (SELECT 1 FROM application WHERE app_id = tmp_record.app_id) THEN
                INSERT INTO application (app_id, app_code, app_name, app_description)
                VALUES (tmp_record.app_id, tmp_record.app_code, tmp_record.app_name, tmp_record.app_description);
            END IF;
    
            -- Insertar datos en la tabla Version si no existe
            IF NOT EXISTS (SELECT 1 FROM version WHERE version_id = tmp_record.version_id) THEN
                INSERT INTO version (version_id, version, app_id, version_description)
                VALUES (tmp_record.version_id, tmp_record.version, tmp_record.app_id, tmp_record.version_description);
            END IF;
    
            -- Insertar datos en la tabla Version_Company si no existe
            IF NOT EXISTS (SELECT 1 FROM version_company WHERE version_company_id = tmp_record.version_company_id) THEN
                INSERT INTO version_company (version_company_id, company_id, version_id, version_company_description)
                VALUES (tmp_record.version_company_id, tmp_record.id_company, tmp_record.version_id, tmp_record.version_company_description);
            END IF;
        END LOOP;
    
        -- Cerramos el cursor
        CLOSE CTemporal;
    END;
    $$;
    
    select insertar_datos_desde_tmp();
  ```
  ***Los scripts también se encuentran en la estructura del proyecto, `src/main/resources/schema.sql`***
- API REST con crud de la tabla company
- Endpoint adicional con respuesta específica
- Aplicación Calculadora
- Aplicación Generador de palabras
- Conexión a base de datos con JPA

## Requisitos previos
- **Java 17** o superior
- **Maven** 3.8 o superior
- **Spring Boot** 2.7.0
- Tener instalado una base de datos como MySQL, PostgreSQL o cualquier otra compatible con JPA.

## Instalación

### Abrir consola o terminal y ejecutar los siguientes comandos
### Clonar el repositorio
```bash
git clone https://github.com/WilmarDeML/prueba-java-ada.git
cd prueba-java-ada
```

### Compilar y construir el proyecto
```bash
mvn clean install
```

## Ejecución
### Ejecutar localmente
Ejecutar proyecto con maven
```bash
mvn spring-boot:run
```
O compila el archivo **.jar**
```bash
java -jar target/examen-java-0.0.1-SNAPSHOT.jar
```

## Uso
### Consumir api rest, crud tabla company
#### path `http:localhost:8080/v1/api/company`
#### Estructura de respuestas: `{ msg, status, content }`
#### Endpoints
`GET / Retorna en su content todos los registros de la tabla`  
`GET /{companyId} Retorna en su content el registro que coincida con el companyId`  
`POST / Requiere un body con { codigoCompany, nameCompany, descriptionCompany } y retorna en content el registro insertado en la tabla`  
`PUT /{companyId} si recibe un body como el del método POST actualiza el registro que coincida con companyId y retorna el registro actualizado`
`DELETE /{companyId} elimina el registro que coincida con companyId y retorna el registro eliminado`

### Consumir endpoint adicional
#### path `http:localhost:8080/v1/api/versionCompany`
#### Estructura de respuestas: `{ msg, status, content }`
#### Endpoint
`GET /{companyCode} Retorna en su content { codigoCompany, nameCompany, appName, version } del registro que coincida con el código de la compañía -> companyCode`

### Ejecutar calculadora
### Terminar la ejecución del proyecto desde la consola
```bash
ctrl + c
```

### Compilar calculadora
```bash
javac Calculadora.java
```

### Ejecutar calculadora
```bash
java Calculadora
```
*`Seguir las instrucciones en consola para usarla`*

### Ejecutar generador de palabras

### Compilar generador
```bash
javac WordsGenerator.java
```

### Ejecutar generador
```bash
java WordsGenerator
```
*`Ejecutar el mismo comando para usarlo de nuevo`*