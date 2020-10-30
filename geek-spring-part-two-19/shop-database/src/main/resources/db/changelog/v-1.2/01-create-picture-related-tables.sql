CREATE TABLE `pictures_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
GO

CREATE TABLE `pictures` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `picture_data_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ehsu2tyinopypjox1ijxt3g3c` (`picture_data_id`),
  CONSTRAINT `FKe9cv52k04xoy6cj8xy308gnw3` FOREIGN KEY (`picture_data_id`) REFERENCES `pictures_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
GO

CREATE TABLE `products_pictures` (
  `product_id` bigint(20) NOT NULL,
  `picture_id` bigint(20) NOT NULL,
  KEY `FKh3amnci4cl7xcl1al140xw79e` (`product_id`),
  KEY `FKloucf8ggy74nmdej2jmvttvi4` (`picture_id`),
  CONSTRAINT `FKh3amnci4cl7xcl1al140xw79e` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKloucf8ggy74nmdej2jmvttvi4` FOREIGN KEY (`picture_id`) REFERENCES `pictures` (`id`)
) ENGINE=InnoDB
GO