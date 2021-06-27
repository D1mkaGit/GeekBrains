CREATE SCHEMA `cinema` ;

CREATE TABLE `cinema`.`movies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`id`));
  
  
  CREATE TABLE `cinema`.`schedules` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

CREATE TABLE `cinema`.`tikets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `movie_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
  ALTER TABLE `cinema`.`schedules` 
ADD INDEX `fkShedulesMovies_idx` (`movie_id` ASC) VISIBLE;
;
ALTER TABLE `cinema`.`schedules` 
ADD CONSTRAINT `fkShedulesMovies`
  FOREIGN KEY (`movie_id`)
  REFERENCES `cinema`.`movies` (`id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE;
  
  ALTER TABLE `cinema`.`schedules` 
ADD INDEX `fkShedulesTickets_idx` (`time` ASC) VISIBLE;
ALTER TABLE `cinema`.`schedules` ALTER INDEX `fkShedulesMovies_idx` INVISIBLE;

  
  ALTER TABLE `cinema`.`tikets` 
ADD INDEX `fkTicketsMovies_idx` (`movie_id` ASC) VISIBLE,
ADD INDEX `fkTicketsScheules_idx` (`movie_time` ASC) VISIBLE;
;
ALTER TABLE `cinema`.`tikets` 
ADD CONSTRAINT `fkTicketsMovies`
  FOREIGN KEY (`movie_id`)
  REFERENCES `cinema`.`movies` (`id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
ADD CONSTRAINT `fkTicketsScheules`
  FOREIGN KEY (`movie_time`)
  REFERENCES `cinema`.`schedules` (`time`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;
  
  
  
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('Семейка Крудс 2', '90');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('Отец', '90');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('Аферистка', '120');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('Годзилла против Конга', '120');



INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('1', '5', '2021-06-03T11:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('1', '6', '2021-06-03T15:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('1', '7', '2021-06-03T18:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('2', '6', '2021-06-03T11:30:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('2', '7', '2021-06-03T16:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('2', '10', '2021-06-03T21:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('3', '5', '2021-06-03T10:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('3', '7', '2021-06-03T14:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('3', '10', '2021-06-03T20:00:00');
INSERT INTO `cinema`.`schedules` (`movie_id`, `price`, `time`) VALUES ('4', '6', '2021-06-03T17:00:00');


INSERT INTO `cinema`.`tikets` (`movie_id`, `price`, `movie_time`) VALUES ('1', '5', '2021-06-03T11:00:00');
INSERT INTO `cinema`.`tikets` (`movie_id`, `price`, `movie_time`) VALUES ('1', '5', '2021-06-03T11:00:00');
INSERT INTO `cinema`.`tikets` (`movie_id`, `price`, `movie_time`) VALUES ('1', '5', '2021-06-03T11:00:00');
INSERT INTO `cinema`.`tikets` (`movie_id`, `price`, `movie_time`) VALUES ('3', '5', '2021-06-03T11:00:00');


-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. 
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;

WITH intervals AS (
SELECT S.movie_id, 
S.id as session_id, 
F.name, 
S.time as start_time, 
F.duration,
DATE_ADD(time, INTERVAL F.duration MINUTE) as end_time
FROM schedules S 
INNER JOIN movies F on S.movie_id = F.id)
SELECT I1.name as "фильм 1", 
I1.start_time as "время начала", 
I1.duration as "длительность",
I2.name as "фильм 2", 
I2.start_time as "время начала", 
I2.duration as "длительность"
FROM intervals I1
INNER JOIN intervals I2 ON I1.start_time < I2.end_time AND I1.end_time > I2.start_time
AND I1.session_id != I2.session_id
AND I2.session_id > I1.session_id

-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. 
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;

WITH intervals AS (
SELECT S.movie_id, 
S.id as schedule_id, 
F.name, 
S.time as start_time, 
F.duration,
DATE_ADD(time, INTERVAL F.duration MINUTE) as end_time
FROM schedules S 
INNER JOIN movies F on S.movie_id = F.id)
SELECT I1.name as "фильм 1", 
I1.start_time as "время начала", 
I1.duration as "длительность", -- I1.end_time,
I2.name as "фильм 2", -- добавил для удобства
I2.start_time as "время начала второго фильма", -- I2.end_time,
TIMESTAMPDIFF(MINUTE, I1.end_time, I2.start_time) as "длительность перерыва"
FROM intervals I1
INNER JOIN intervals I2 ON I1.end_time < I2.start_time
order by TIMESTAMPDIFF(MINUTE, I1.end_time, I2.start_time) -- если оставить киилистическое название, то ордер не просходит 





  