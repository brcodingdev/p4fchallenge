
CREATE TABLE `state` (
  `id` integer NOT NULL,
  `country` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `state` (id, country, name) VALUES (1, 'BR', 'São Paulo');
INSERT INTO `state` (id, country, name) VALUES (2, 'BR', 'Bahia');

CREATE TABLE `city` (
  `id` integer NOT NULL,
  `name` varchar(255) NOT NULL,
  `state_id` integer NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `city_state_id_fk` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `city` (id, name, state_id) VALUES (1, 'São Paulo', 1);
INSERT INTO `city` (id, name, state_id) VALUES (2, 'Salvador', 2);

CREATE TABLE `address` (
  `id` integer NOT NULL AUTO_INCREMENT,
  `address` varchar(2000) NOT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `district` varchar(255) NOT NULL,
  `number` integer NOT NULL,
  `zip_code` varchar(8) NOT NULL,
  `city_id` integer NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `address_city_id_fk` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX address_zip_code_idx ON address(zip_code);

CREATE TABLE `user` (
  `id` integer NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE INDEX user_username_idx ON `user`(username);

INSERT INTO `user` (username, password, role) VALUES ('goku', '$2a$10$Szg4OyHX7UPzQY7JSVGIR.WYtk8KufgA2GgvoGzQxl.dCKqCsyLa.', 'ADMIN');

