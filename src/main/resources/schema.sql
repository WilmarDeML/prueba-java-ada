-- Creación de la tabla version_company
create table version_company (
     version_company_id int8 primary key,
     company_id int8 not null,
     version_id int8 not null,
     version_company_description varchar(255) NULL,
     unique (company_id),
     foreign key (company_id) references company(id_company) on delete cascade,
     foreign key (version_id) references version(version_id) on delete cascade
);

-- Creación de tabla tmp_llenar_campos
create table TMP_LLENAR_CAMPOS (
   id_company int8 ,
   codigo_company varchar(255),
   name_company varchar(255),
   description_company varchar(255),
   version_id int8,
   app_id int8,
   version varchar(255),
   version_description varchar(255),
   version_company_id int8,
   company_id int8,
   version_company_description varchar(255),
   app_code varchar(255),
   app_name varchar(255),
   app_description varchar(255)
);

-- Procedimeinto de inserción de datos
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
