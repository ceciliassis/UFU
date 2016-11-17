--drop function
create or replace function verificaData ( data1 varchar , hotel1 int, quarto1 int, andar1 int) returns boolean as 
$$
declare 
id int;
begin
	select reserva.id_reserva into id
	from reserva, tem,quarto 
	where  quarto.id_hotel = hotel1 and
	 quarto.num_quarto=quarto1 and quarto.andar=andar1
	  and tem.id_quarto = quarto.id_quarto and reserva.reserva_de = data1;

	  if found then 
		return true;
	  else return false;
	  end if;

end;

$$ language 'plpgsql'
--drop verifica data
CREATE OR REPLACE FUNCTION verificadata2(data1 character varying, hotel1 integer, quarto1 integer, andar1 integer)
  RETURNS boolean AS
$BODY$
declare 
id int;
begin
	select reserva.id_reserva into id
	from reserva, tem,quarto 
	where  quarto.id_hotel = hotel1 and
	 quarto.num_quarto=quarto1 and quarto.andar=andar1
	  and tem.id_quarto = quarto.id_quarto and reserva.ate = data1;

	  if found then 
		return true;
	  else return false;
	  end if;

end;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION verificadata2(character varying, integer, integer, integer)
  OWNER TO postgres;


CREATE OR REPLACE FUNCTION checarlogin(login_user character varying)
  RETURNS boolean AS
$BODY$
declare
id int;
begin
select id_hospede into id from hospede where login=login_user;
	if not found then
		return 0;
		else return true;
	end if;
end;
	$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION checarlogin(character varying)
  OWNER TO postgres;
