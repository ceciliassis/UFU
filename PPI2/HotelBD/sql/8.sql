
CREATE OR REPLACE FUNCTION sp_cancela_reserva()
  RETURNS trigger AS
$BODY$
begin
	insert into log_cancela_reserva(data,id_reserva) values(now(),old.id_reserva); 
	return null;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION sp_cancela_reserva()
  OWNER TO postgres;

create trigger tr_cancela_reserva after delete on reserva for each row execute procedure sp_cancela_reserva()

create table log_cancela_reserva(
	id serial primary key,
	data timestamp,
	id_reserva int	
)

alter table log_cancela_reserva add de varchar
alter table log_cancela_reserva add ate varchar
alter table log_cancela_reserva add num_quarto int
alter table log_cancela_reserva add andar_quarto int
alter table log_cancela_reserva add hospede int
alter table log_cancela_reserva alter hotel type varchar



CREATE OR REPLACE FUNCTION sp_cancela_reserva()
  RETURNS trigger AS
$BODY$
declare 
qnum int;
qandar int;
hospede int;
rde varchar;
rate varchar;
rhotel varchar;
begin
	select hospede,de,ate,hotel,andar,quarto into hospede,rde,rate,rhotel,qandar,qnum from viewreserva where codigo=old.reserva;
	insert into log_cancela_reserva(data,id_reserva) values(now(),old.id_reserva); 
	return old;
end;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION sp_cancela_reserva()
  OWNER TO postgres;
  
  
  --drop
create table log_altera_reserva(
	id serial primary key,
	data timestamp,
	id_reserva int,
	de varchar,
	ate varchar
)
 --drop
create or replace function sp_altera_reserva () returns trigger as 
$$
begin
	insert into log_altera_reserva(data,id_reserva,de,ate) values (now(),old.id_reserva,old.reserva_de,old.reserva_ate);
	return null;
end;
$$ language 'plpgsql';
 --drops
create trigger tr_altera_reserva after update on reserva for each row execute procedure sp_altera_reserva()


alter table classifica alter id_classifica type int
alter table classifica add controle serial
