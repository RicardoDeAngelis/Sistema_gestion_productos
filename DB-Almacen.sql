--DB Almacen--
--name ricardo-
--pass ricardo

--drop table categoria;
create table categoria  (
   	 
   	codigo_categoria VARCHAR(50) NOT NULL, 
	descripcion_categoria VARCHAR(50) NOT NULL,
	PRIMARY KEY (codigo_categoria)
);

--truncate table categoria; 

--drop table bebidas;
create table bebidas  (
 
       codigo_bebida varchar(50)not null,       
       descripcion_bebida varchar(50) not null,
        
        
        stock Integer not null,
        vencimiento Date not null,
     
        precio_final real not null,
        codigo_categoria varchar(50) not null,
         PRIMARY KEY (codigo_bebida)
);
--truncate table bebidas; 
alter table bebidas
      add foreign key (codigo_categoria)
       references categoria (codigo_categoria)
;

--SELECT precio_final FROM bebidas where codigo_categoria ='1';




--drop table cierre;
create table cierre  (
   	
        numero_venta Integer  generated always as identity(start with 1,increment by 1) ,
        fecha_cierre Date not null,
        ingresos  real not null,
        egresos real not null,
        total real not null,
         PRIMARY KEY (numero_venta)
);

create view vista_categoria_produc as
select b.descripcion_bebida, c.descripcion_categoria
 from bebidas AS b inner join categoria AS c
 on c.codigo_categoria = b.codigo_categoria ;
--and c.codigo_categoria = '1';

--create view vista_categoria_prod as
--select b.descripcion_bebida, c.descripcion_categoria
 --from bebidas AS b inner join categoria AS c
 --on c.codigo_categoria = b.codigo_categoria ;

 


select*from vista_categoria_produc;
select*from categoria;
select*from bebidas;



select*from cierre;

SELECT sum (ingresos)total_ingresos ,sum (egresos)total_egresos FROM cierre ; 
select sum(total)ventaDiaria from cierre;
truncate table cierre; 


insert into categoria values('1','Bebidas');

insert into categoria values('2','Comestibles');

insert into categoria values('3','Lacteos');
   
insert into bebidas values('123456','coca 2 lr',100,'12/12/2020',55,'1');

insert into bebidas values('123457','pepsi 2,250 ld ',100,'12/12/2020',70,'1');

insert into bebidas values('123458','Stella 1 lr',100,'12/12/2020',80,'1');

insert into bebidas values('123459','prestopronta 500gr',40,'12/12/2020',20,'2');

insert into bebidas values('123450','arroz gallo 500gr',40,'12/12/2020',25,'2');

insert into bebidas values('123451','pan dulce m&a 500gr',50,'12/12/2020',60,'2');

insert into bebidas values('123452','leche milkaut 1l caja',100,'12/12/2020',30,'3');

insert into bebidas values('123453','manteca comun',100,'12/12/2020',20,'3');

insert into bebidas values('123454','yogurt 1l milkaut',100,'12/12/2020',40,'3');
