create table mesa(
	cod_mesa int auto_increment primary key,
    num_sesion int,
    expires date
);
create table menu(
	cod_producto int primary key,
    nombre varchar(30),
    imagen varchar(100),
    precio float
);
create table pedidos(
	cod_producto int,
    cod_mesa int,
    cantidad int,
    constraint primare_key primary key (cod_producto, cod_mesa),
    constraint producto_foreng foreign key (cod_producto) references menu (cod_producto),
    constraint mesa_freng foreign key (cod_mesa) references mesa (cod_mesa)
);

insert into mesa(num_sesion)values(1);
insert into mesa(num_sesion)values(1);
insert into mesa(num_sesion)values(1);
insert into mesa(num_sesion)values(1);
insert into mesa(num_sesion)values(1);
insert into mesa(num_sesion)values(1);
select * from mesa;