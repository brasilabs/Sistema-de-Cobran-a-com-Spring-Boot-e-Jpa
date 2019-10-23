CREATE TABLE situacao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO situacao (nome) values ('Em Negociação');
INSERT INTO situacao (nome) values ('Negociado');
INSERT INTO situacao (nome) values ('Pagou');
INSERT INTO situacao (nome) values ('Quebrou o Acordo');
INSERT INTO situacao (nome) values ('Não Encontrado');