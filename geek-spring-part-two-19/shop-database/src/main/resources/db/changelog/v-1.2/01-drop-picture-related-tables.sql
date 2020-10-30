alter table `products_pictures` drop FOREIGN KEY `FKh3amnci4cl7xcl1al140xw79e`;
GO

-- сначала нужно удалить внешний ключ
alter table `pictures` drop FOREIGN KEY `FKe9cv52k04xoy6cj8xy308gnw3`;
GO

-- потом уникальность, которую это внешний ключ использовал
alter table `pictures` drop key `UK_ehsu2tyinopypjox1ijxt3g3c`;
GO

alter table `products_pictures` drop FOREIGN KEY `FKloucf8ggy74nmdej2jmvttvi4`;
GO

drop table `pictures`;
GO

drop table `products_pictures`;
GO

drop table `pictures_data`;
GO