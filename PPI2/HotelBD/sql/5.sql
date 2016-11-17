-- View: viewreserva

-- DROP VIEW viewreserva;

CREATE OR REPLACE VIEW viewreserva AS 
 SELECT hospede.id_hospede,
    reserva.id_reserva AS codigo,
    reserva.reserva_de as de,
    reserva.reserva_ate as ate,
    reserva.checkin,
    reserva.checkout,
    hotel.nome as hotel,
    quarto.num_quarto as quarto,
    quarto.andar as andar,
    reserva.sitpg AS pagamento,
    faz.data as data,
    tipoquarto.nome as tipo
   FROM reserva,
    hospede,
    faz, tem, compoe, hotel, quarto, tipoquarto
  WHERE faz.id_hospede = hospede.id_hospede and faz.id_reserva=reserva.id_reserva
  and hotel.id_hotel=compoe.id_hotel and compoe.num_quarto=quarto.num_quarto 
  and tem.id_reserva=reserva.id_reserva and tem.num_quarto = quarto.num_quarto and quarto.id_tipo = tipoquarto.id_tipo



-- drop function
	-- converter as datas para que de certo

	create or replace function verificar_reserva(data1 timestamp , hotel1 int , quarto1 int  ) returns boolean as 
	$$
	begin
	select reserva.id_reserva from reserva, quarto, hotel, compoe,tem where reserva_de = data1 and compoe.id_hotel = hotel1 and compoe.num_quarto = quarto1
	 and id_hotel = hotel1 and quarto.num_quarto = quarto1 and tem.num_quarto=quarto1 and tem.id_reserva = reserva.id_reserva
and compoe.num_quarto=quarto.num_quarto;
if found then
	return true;
else 
	return false;
	end if ;
	end;
	$$ language 'plpgsql'; 
