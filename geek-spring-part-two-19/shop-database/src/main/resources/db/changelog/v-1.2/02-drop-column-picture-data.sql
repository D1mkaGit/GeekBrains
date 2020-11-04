alter table `pictures_data` drop column file_name;
GO

alter table pictures_data modify `data` longblob NOT NULL;
GO
