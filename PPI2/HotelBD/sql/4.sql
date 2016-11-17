-- Function: checarlogin(character varying)

-- DROP FUNCTION checarlogin(character varying);

CREATE OR REPLACE FUNCTION checarlogin(login_user character varying)
  RETURNS boolean AS
$BODY$
declare
id int;
begin
select id_hospede into id  from hospede where login=login_user;
	if not found then
		return 0;
		else return id;
	end if;
end;
	$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION checarlogin(character varying)
  OWNER TO postgres;

alter table faz add data timestamp;

--drop view

create or replace view viewHotel as 
select id_hotel as codigo , hotel.nome, endereco, ddd,tel, pais.nome as pais, cidade from hotel, pais where hotel.id_pais=pais.id_pais

-- drop view viewReserva
create or replace view viewReserva as 
select hospede.id_hospede as hospede, reserva.id_reserva as codigo, reserva_de as de, reserva_ate as ate, checkin, checkout, sitpg as pagamento from reserva, hospede,faz where faz.id_hospede=hospede.id_hospede

--drop view viewProdutosConsumidos
create or replace view viewProdutosConsumidos as
select hospede.id_hospede as hospede, produto.nome, qtd, consome.data, qtd*valor as total 
from consome,hospede, reserva,produto,faz where faz.id_hospede=hospede.id_hospede 
and reserva.id_reserva=consome.id_reserva and consome.id_prod = produto.id_prod

--drop view viewTipoQuarto
create or replace view viewTipoQuarto as
select tipoquarto.id_tipo as codigo, lotacao, descri, valor from tipoquarto

alter table tipoquarto add nome varchar

--drop view viewQuartos

create or replace view viewQuartos as
select hotel.id_hotel as hotel, quarto.num_quarto as num, quarto.andar, quarto.id_tipo, tipoquarto.id_tipo as tipo, tipoquarto.nome from quarto, hotel,compoe, tipoquarto
where tipoquarto.id_tipo=quarto.id_tipo

-- drop function
create or replace function sp_logDelecao () returns trigger as
$$
begin
	insert into logDelecao values (now(),old.nome,old.email);
	return old;
end;
$$language 'plpgsql';

--drop trigger tr_logDelecao on hospede
create trigger tr_logDelecao after delete on hospede for each row execute procedure sp_logDelecao() 


--drop table logDelecao

create table logDelecao (

data timestamp,
nome varchar,
email varchar,
id serial primary key

)


set datestyle to 'sql,dmy'

show datestyle

alter table reserva alter reserva_de type timestamp

alter table reserva alter reserva_ate type timestamp

ALTER DATABASE hotel2015 SET datestyle TO SQL, DMY;

-- Function: formatdata(integer)

-- DROP FUNCTION formatdata(integer);

CREATE OR REPLACE FUNCTION formatdata( data2 integer )
  RETURNS varchar AS
$BODY$
declare 
 resp text;
 dia double precision;
 mes double precision;
 ano double precision;
 data1 timestamp;
begin
 select data into data1 from msg where id=data1;
 select extract(day from data1) into dia;
 select extract(month from data1) into mes;
 select extract(year from data1) into ano;
 resp:= dia || '/' || mes || '/' || ano;

 return resp;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION formatdata(integer)
  OWNER TO postgres;

