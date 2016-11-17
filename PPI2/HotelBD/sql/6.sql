--drop view 
create or replace view viewReserva as 
select hospede.id_hospede as hospede, reserva.id_reserva as codigo, 
reserva_de as de, reserva_ate as ate, checkin, checkout, 
sitpg as pagamento , hotel.nome as hotel, quarto.andar, 
quarto.num_quarto as quarto,
faz.data as data ,tipoquarto.nome as tipo
from reserva , hospede , faz , tipoquarto , hotel , tem , quarto
where faz.id_hospede=hospede.id_hospede 
and faz.id_reserva = reserva.id_reserva
and tem.id_reserva = reserva.id_reserva
and tem.id_quarto = quarto.id_quarto
and quarto.id_hotel = hotel.id_hotel

alter table tem add constraint tem_pkey primary key (id_reserva)

alter table faz add constraint faz_pkey primary key (id_reserva)

alter table estoque add constraint estoque_pkey primary key
 (id_prod,num_quarto,andar)
 
 alter table consome add constraint consume_pkey primary key
 (id_reserva)
 
 alter table compoe add constraint compoe_pkey primary key
 (id_hotel, andar,num_quarto)
 
 alter table estoque add constraint estoque_pkey primary key (id_quarto)
 
 alter table tem add constraint tem_pkey primary key (id_reserva)
 
 -- drop view
 create or replace view viewquartos as 
select num_quarto, andar, tipoquarto.nome as tipo, tipoquarto.descri, hotel.id_hotel as hotel
from quarto, tipoquarto, hotel 
where quarto.id_hotel = hotel.id_hotel
and tipoquarto.id_tipo = quarto.id_tipo 
