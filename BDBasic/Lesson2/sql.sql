# Создаем базу данных
-- CREATE SCHEMA `geodata` ;
# заходим в папку с sql файлами и правим tables.sql, добавляем к таблицам название базы данных, для импорта
# открываем там командную строку(CMD) и делаем импорт этих файлов в базу коммандами: 
# F:\Downloads\OneDrive\GeekBrains\BD_BASIC\Lesson2\geodata>"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p < tables.sql
# F:\Downloads\OneDrive\GeekBrains\BD_BASIC\Lesson2\geodata>"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p < __countries.sql
# F:\Downloads\OneDrive\GeekBrains\BD_BASIC\Lesson2\geodata>"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p < _cities.sql
# F:\Downloads\OneDrive\GeekBrains\BD_BASIC\Lesson2\geodata>"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -p < _regions.sql

# Правим первую таблицу `geodata`.`_countries` согласно методичке:
ALTER TABLE `geodata`.`_countries` 
DROP COLUMN `title_cz`,
DROP COLUMN `title_lv`,
DROP COLUMN `title_lt`,
DROP COLUMN `title_ja`,
DROP COLUMN `title_pl`,
DROP COLUMN `title_it`,
DROP COLUMN `title_fr`,
DROP COLUMN `title_de`,
DROP COLUMN `title_pt`,
DROP COLUMN `title_es`,
DROP COLUMN `title_en`,
DROP COLUMN `title_be`,
DROP COLUMN `title_ua`,
CHANGE COLUMN `country_id` `id` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `title_ru` `title` VARCHAR(150) NOT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `countryTitle` (`title` ASC) VISIBLE;


# Правим вторую таблицу `geodata`.`_regions` согласно методичке:
ALTER TABLE `geodata`.`_regions` 
DROP COLUMN `title_cz`,
DROP COLUMN `title_lv`,
DROP COLUMN `title_lt`,
DROP COLUMN `title_ja`,
DROP COLUMN `title_pl`,
DROP COLUMN `title_it`,
DROP COLUMN `title_fr`,
DROP COLUMN `title_de`,
DROP COLUMN `title_pt`,
DROP COLUMN `title_es`,
DROP COLUMN `title_en`,
DROP COLUMN `title_be`,
DROP COLUMN `title_ua`,
CHANGE COLUMN `region_id` `id` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `country_id` `country_id` INT NULL ,
CHANGE COLUMN `title_ru` `title` VARCHAR(150) NULL DEFAULT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `title_index` (`title` ASC) VISIBLE,
ADD INDEX `country_id_idx` (`country_id` ASC) INVISIBLE;
;
ALTER TABLE `geodata`.`_regions` 
ADD CONSTRAINT `country_id`
  FOREIGN KEY (`country_id`)
  REFERENCES `geodata`.`_countries` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

# Правим третью таблицу `geodata`.`_regions` согласно методичке (так, как все падает с ошибкой, запускаем частями):
ALTER TABLE `geodata`.`_cities` 
DROP COLUMN `region_cz`,
DROP COLUMN `area_cz`,
DROP COLUMN `title_cz`;

ALTER TABLE `geodata`.`_cities` 
DROP COLUMN `region_lv`,
DROP COLUMN `area_lv`,
DROP COLUMN `title_lv`,
DROP COLUMN `region_lt`,
DROP COLUMN `area_lt`,
DROP COLUMN `title_lt`;

ALTER TABLE `geodata`.`_cities` 
DROP COLUMN `region_ja`,
DROP COLUMN `area_ja`,
DROP COLUMN `title_ja`,
DROP COLUMN `region_pl`,
DROP COLUMN `area_pl`,
DROP COLUMN `title_pl`,
DROP COLUMN `region_it`,
DROP COLUMN `area_it`,
DROP COLUMN `title_it`;

ALTER TABLE `geodata`.`_cities` 
DROP COLUMN `region_fr`,
DROP COLUMN `area_fr`,
DROP COLUMN `title_fr`,
DROP COLUMN `region_de`,
DROP COLUMN `area_de`,
DROP COLUMN `title_de`,
DROP COLUMN `region_pt`,
DROP COLUMN `area_pt`,
DROP COLUMN `title_pt`,
DROP COLUMN `region_es`,
DROP COLUMN `area_es`,
DROP COLUMN `title_es`;

ALTER TABLE `geodata`.`_cities` 
DROP COLUMN `region_en`,
DROP COLUMN `area_en`,
DROP COLUMN `title_en`,
DROP COLUMN `region_be`,
DROP COLUMN `area_be`,
DROP COLUMN `title_be`,
DROP COLUMN `region_ua`,
DROP COLUMN `area_ua`,
DROP COLUMN `title_ua`,
DROP COLUMN `region_ru`,
DROP COLUMN `area_ru`;

ALTER TABLE `geodata`.`_cities` 
CHANGE COLUMN `city_id` `id` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `title_ru` `title` VARCHAR(150) NULL DEFAULT NULL ,
ADD PRIMARY KEY (`id`);
;

ALTER TABLE `geodata`.`_cities` 
ADD INDEX `title_index` (`title` ASC) VISIBLE;

ALTER TABLE `geodata`.`_cities` 
ADD CONSTRAINT `cityes_country_id`
  FOREIGN KEY (`country_id`)
  REFERENCES `geodata`.`_countries` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `geodata`.`_cities` 
ADD CONSTRAINT `cityes_region_id`
  FOREIGN KEY (`region_id`)
  REFERENCES `geodata`.`_regions` (`id`)
  ON DELETE SET NULL
  ON UPDATE NO ACTION;