create database clase;
use clase;
select * from clase;
create table Alumno(
    idAlumno into not null primary key auto_increment,
    email varchar(30) not null,
    fechaCreacion datetime,
    nombre varchar(20),
    apellidoP varchar(20),
    apellidoM varchar(20)
)

INSERT INTO Alumno (email,fechaCreacion,nombre,apellidoM,apellidoP)
values ('email@gmail.com','2023-05-02','Alan','Malagon','Baeza')
select * from alumno;
select * from alumno where idAlumno=1;
select * from alumno where email='email@gmail.com';
delete from alumno where idAlumno=1;
update alumno set email='emailnuevo@gmail.com',nombre='Adrian',apellidoM='Martinez',apellidoP='Villegas' where idAlumno=1;