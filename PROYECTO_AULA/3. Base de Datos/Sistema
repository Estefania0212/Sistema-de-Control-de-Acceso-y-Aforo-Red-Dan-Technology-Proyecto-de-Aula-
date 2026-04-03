

create table usuario(
idusuario integer primary key,
usua_nombre varchar (50) not null,
usua_apellidos varchar (100)not null,
usua_clave varchar (20) not null
); 

INSERT INTO usuario(idusuario,usua_nombre,usua_apellidos,usua_clave)
VALUES('1005105673','Estefania','Moreno','admin');


INSERT INTO usuario(idusuario,usua_nombre,usua_apellidos,usua_clave)
VALUES('1005161030','Brandon','Quintero','45678');

INSERT INTO usuario(idusuario,usua_nombre,usua_apellidos,usua_clave)
VALUES('1095822479','Oscar','Jimenez','941230');

INSERT INTO usuario(idusuario,usua_nombre,usua_apellidos,usua_clave)
VALUES('1098697248','Victor','Molina','0000');

INSERT INTO usuario(idusuario,usua_nombre,usua_apellidos,usua_clave)
VALUES('37747784','Edy','Rodriguez','1234');

CREATE TABLE empleados(
empl_cedula integer  PRIMARY KEY,
empl_nombres varchar(50)NOT NULL,
empl_apellidos varchar(100)NOT NULL,
empl_telefono varchar(10)null,
empl_correo varchar(50) null,
empl_area_tra varchar(90) not null,
empl_temperatura varchar(4) not null,
empl_fecha_ingreso varchar(20) not null,
empl_fecha_salida varchar(20) null,
fk_idusuario integer not null, 
constraint fk_empleados foreign key(fk_idusuario) references usuario(idusuario)
);


CREATE TABLE visitantes(
visi_cedula integer primary key,
visi_nombres varchar(50)not null,
visi_apellidos varchar(100)null,
visi_telefono varchar(15),
visi_correo varchar(50),
visi_area_dirige varchar(90)not null,
visi_motivo_desc varchar(200),
visi_temperatura varchar(4) not null,
visi_fecha_ingreso varchar(20) not null,
visi_fecha_salida varchar(20) null,
fk_idusuario integer not null, 
constraint fk_visitantes foreign key(fk_idusuario) references usuario(idusuario)
);