create table mesa(
	cod_mesa int auto_increment primary key,
    num_sesion int,
    expires date
);
create table menu(
	cod_producto int primary key,
    nombre varchar(100),
    imagen varchar(100),
    precio float,
    descripcion varchar(200)
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
insert into menu values(1,"Pizza Bissmark","/Imagenes/1.png",9.95,"Tomate, mozzarella,jamon york, huevo y oregano");
insert into menu values(2,"Risotti","/Imagenes/2.png", 9.25,"Arroz con pollo braseado con champignon bacon y curry suave");
insert into menu values(3,"MeatBalls","/Imagenes/3.png",14.90, "Spaghetti y albondigas trufadas");
insert into menu values(4,"Menu piccolino","/Imagenes/4.png",9.50,"Macarron fresco con salsa bolognesa");
insert into menu values(5,"Tarta de queso","/Imagenes/5.png",5,"Tarta de requeson con mermelada de fresa");
insert into menu values(6,"Ternera esencial(300gr)","/Imagenes/6.png",19.90,"De la finca de Jimenez Barbero con pimientos asados y patata natural frita");
insert into menu values(7,"Insalate Cesar Romana","/Imagenes/7.png",10.50,"Lechuga Romana, pollo braseado, bacon, elemmental rallado, parmesano, tomate reposado y nuestra salsa cesar");
insert into menu values(8,"Antipasti, Bruchetta di jamon de pato","/Imagenes/8.png",6.50,"brotes tiernos de lechuga, cebolla caramelizada, pasas, pignones, parmesano y vinagreta de nueces");
insert into menu values(9,"Lasagna di carne","/Imagenes/9.png",13.90,"Seleccion de ternera y magro de cerdo, zanahoria, puerros gratinados con bechamel y parmesano");