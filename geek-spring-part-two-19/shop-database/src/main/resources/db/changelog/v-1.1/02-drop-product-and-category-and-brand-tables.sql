alter table brands
       drop index Unique_brands_name;
GO

alter table categories
       drop index Unique_categories_name;
GO

alter table products
       drop foreign key fk_brand_id;
GO

alter table products
        drop foreign key fk_categories_id;
GO

drop table categories
GO

drop table products
GO

drop table brands
GO
