--drop function formatData

create or replace function formatData(int) returns text as 
$$
declare 
 resp text;
 dia double precision;
 mes double precision;
 ano double precision;
 data1 timestamp;
begin
 select data into data1 from msg where id=$1;
 select extract(day from data1) into dia;
 select extract(month from data1) into mes;
 select extract(year from data1) into ano;
 resp:= dia || '/' || mes || '/' || ano;

 return resp;
end;
$$language 'plpgsql';
