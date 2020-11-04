alter table pictures_data add file_name varchar(255);
GO

alter table pictures_data modify `data` longblob NULL;
GO
