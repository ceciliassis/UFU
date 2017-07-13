
-- DROP FUNCTION checalogin(character varying);

CREATE OR REPLACE FUNCTION checalogin(character varying)
  RETURNS int AS
$BODY$
declare 
	nomee varchar;
	id int;
begin
	
	select nome, id_hospede into nomee, id  from hospede where login=$1;
	if not found then
		return 0;
		else return id;
	end if;
	
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION checalogin(character varying)
  OWNER TO postgres;

-- drop view viewHotel

create or replace view viewHotel as 
select id_hotel, hotel.nome, endereco, tel, pais.nome as pais from hotel join pais on hotel.id_pais=pais.id_pais

alter table hotel alter tel type int

alter table hospede alter tel type int
alter table hospede add ddd smallint
