INSERT INTO `countriesandcities`.`01_country`  values (0,'Russia');
SELECT * from `countriesandcities`.`01_country` ;

INSERT INTO `countriesandcities`.`02_region`  values (0,1,'Leningradski');
select * from `countriesandcities`.`02_region`;
ALTER TABLE `countriesandcities`.`02_region`;
ALTER TABLE `countriesandcities`.`02_region` AUTO_INCREMENT = 1;

INSERT INTO `countriesandcities`.`04_city` VALUES(0,0,1,'St.Petersburg');
SELECT * from `countriesandcities`.`04_city`;