CREATE TABLE pais ( --ok
id_pais serial PRIMARY KEY,
nome varchar,
capital varchar,
ddi int,
moeda varchar
);

CREATE TABLE tipoquarto ( --ok
id_tipo serial PRIMARY KEY,
qtdcamas int,
fumanteArea boolean,
lotacao int,
valor numeric(6,2),
descri text
);

CREATE TABLE hotel ( --ok
id_hotel serial PRIMARY KEY,
nome varchar,
endereco varchar,
tel bigint,
cidade varchar,
id_pais int,
FOREIGN KEY(id_pais) REFERENCES pais (id_pais) on update restrict on delete restrict
);

CREATE TABLE hospede ( --ok
id_hospede serial PRIMARY KEY ,
rg varchar unique,
CPF char(11) UNIQUE,
login varchar not null unique,
senha varchar not null,
nome varchar,
endereco varchar,
tel bigint
);

CREATE TABLE  quarto (
id_quarto serial PRIMARY KEY,
num_quarto int not null,
andar int not null,
id_tipo int references tipoquarto on update cascade on delete set null,
id_hotel int references hotel on update restrict on delete cascade
);


CREATE TABLE produto ( --ok
id_prod serial PRIMARY KEY ,
valor numeric(6,2),
nome varchar
);

CREATE TABLE reserva ( --ok
id_reserva serial PRIMARY KEY,
reserva_de date,
sitpg boolean,
checkin timestamp,
checkout timestamp,
reserva_ate date
);

CREATE TABLE faz ( --ok
id_reserva int,
id_hospede int,
FOREIGN KEY(id_reserva) REFERENCES reserva (id_reserva) on update restrict on delete cascade,
FOREIGN KEY(id_hospede) REFERENCES hospede (id_hospede) on update restrict on delete no action
);

CREATE TABLE tem ( --ok
id_reserva int primary key,
id_quarto int,
FOREIGN KEY(id_reserva) REFERENCES reserva (id_reserva) on update restrict on delete cascade,
FOREIGN KEY(id_quarto) REFERENCES quarto  on update cascade on delete cascade
);

CREATE TABLE classifica (
data timestamp,
id_hospede int,
id_hotel int,
FOREIGN KEY(id_hospede) REFERENCES hospede (id_hospede) on update restrict on delete no action,
FOREIGN KEY(id_hotel) REFERENCES hotel (id_hotel) on update restrict on delete cascade
);

CREATE TABLE consome (
qtd int,
data date,
id_prod int,
id_reserva int,
FOREIGN KEY(id_prod) REFERENCES produto (id_prod) on update cascade on delete no action,
FOREIGN KEY(id_reserva) REFERENCES reserva (id_reserva) on update restrict on delete cascade
);

CREATE TABLE estoque (
qtd int,
id_prod int,
id_quarto int primary key, 
FOREIGN KEY (id_quarto) REFERENCES quarto on update cascade on delete cascade,
FOREIGN KEY(id_prod) REFERENCES produto (id_prod) on update cascade on delete no action
)
