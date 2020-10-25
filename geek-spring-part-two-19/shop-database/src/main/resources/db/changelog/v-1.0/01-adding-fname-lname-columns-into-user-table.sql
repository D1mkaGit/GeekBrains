ALTER TABLE `spring_shop_db_7`.`users`
ADD COLUMN `first_name` VARCHAR(45) NULL AFTER `password`,
ADD COLUMN `last_name` VARCHAR(45) NULL AFTER `first_name`;

GO