INSERT INTO usuario (nome, email, senha, situacao, telefone) VALUES ('Admin', 'admin@admin.com', '9fe76e18dd29100230680fa6c812e26c4da4b3cf48a85850910e6f4400a6b3ff9ec05190c175f592', 0, '34998824228');

INSERT INTO `permissao` (`id_permissao`, `role`) VALUES
	(1, 'ADMIN'),
	(3, 'FARMACIA'),
	(2, 'USER');
	
insert into usuario_permissao (id_usuario, id_permissao) values (1, 1);
insert into usuario_permissao (id_usuario, id_permissao) values (1, 2);
insert into usuario_permissao (id_usuario, id_permissao) values (1, 3);