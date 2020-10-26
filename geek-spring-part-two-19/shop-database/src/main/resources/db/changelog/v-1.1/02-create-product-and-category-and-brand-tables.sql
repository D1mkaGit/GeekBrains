create table brands (
       id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

GO

CREATE TABLE categories (
        id bigint not null auto_increment,
        name varchar(255) not null,
        PRIMARY KEY (id)
    ) engine=InnoDB;

GO

CREATE TABLE products (
        id bigint not null auto_increment,
        name varchar(255) not null,
        price DECIMAL not null,
        brand_id bigint not null,
        category_id bigint not null,
        PRIMARY KEY (id)
    ) engine=InnoDB;

GO

alter table brands
       add constraint Unique_brands_name unique (name);
GO

alter table categories
       add constraint Unique_categories_name unique (name);
GO

 alter table products
       add constraint fk_brand_id
       foreign key (brand_id)
       references brands (id);
GO

 alter table products
       add constraint fk_categories_id
       foreign key (category_id)
       references brands (id);
GO
