INSERT INTO `brands` VALUES (11,'Aeropro'),(8,'Beechcraft'),(2,'Cessna'),(4,'Cirrus'),(7,'Maule'),(3,'Piper'),(6,'Robin'),(10,'Rotorway'),(9,'Slingsby'),(5,'Socata');
GO

INSERT INTO `categories` VALUES (7,'Helicopter'),(5,'Jet'),(4,'Multi Engine'),(3,'Single Engine'),(6,'Vintage');
GO

-- SET FOREIGN_KEY_CHECKS=0;
-- GO
-- INSERT INTO `pictures` VALUES (13,'image/jpeg','PHOTO-2020-10-27-11-50-54.jpeg',1),(14,'image/jpeg','PHOTO-2020-10-27-11-51-24.jpeg',2),(15,'image/jpeg','PHOTO-2020-10-27-11-51-25-5.jpeg',3),(16,'image/jpeg','IMG_2881.jpeg',4),(17,'image/jpeg','IMG_2883-rotated.jpeg',5),(18,'image/jpeg','IMG_2886.jpeg',6),(19,'image/jpeg','PHOTO-2020-10-23-09-01-55.jpeg',7),(20,'image/jpeg','PHOTO-2020-10-23-09-03-12.jpeg',8),(21,'image/jpeg','PHOTO-2020-10-23-09-03-15-3.jpeg',9),(22,'image/jpeg','2b013f1e-d84e-4b23-8f26-83b59cb761ba.jpg',10),(23,'image/jpeg','810b8e02-04b4-4275-92f4-333912d0a302.jpg',11),(24,'image/jpeg','1772e7a1-8bc8-4b4c-b361-efda19ef0324.jpg',12),(25,'image/jpeg','PHOTO-2020-10-16-10-21-47.jpeg',13),(26,'image/jpeg','PHOTO-2020-10-16-10-21-48-2.jpeg',14),(27,'image/jpeg','PHOTO-2020-10-16-10-21-48-5.jpeg',15),(28,'image/jpeg','1c5bae40-cf86-4468-97ed-543c2a6917f3.jpg',16),(29,'image/jpeg','5af117fd-2d4d-4a72-8838-ad71e00dbde1.jpg',17),(30,'image/jpeg','5100510d-3f82-4695-8926-6ee699cd3030.jpg',18),(31,'image/jpeg','065d3e5d-9912-4c58-bf6a-2ebe6520d968.jpg',19),(32,'image/jpeg','d16aa238-2741-4721-81d5-4704737243d6.jpg',20),(33,'image/jpeg','e75e82af-844a-4faf-b1e9-afca180ccc86.jpg',21),(34,'image/jpeg','IMG_7069.jpeg',22),(35,'image/jpeg','IMG_7080.jpeg',23),(36,'image/jpeg','kTmMXe2tQTOddvUMYMjpQ_thumb_42.jpeg',24);
-- GO
-- SET FOREIGN_KEY_CHECKS=1;
-- GO

INSERT INTO `products` VALUES (2,'Cessna Reims 172H G-MFAC',64995,2,3),(3,'Rotorway 162F (modified) G-ZHWH',0,10,7),(4,'Cessna Reims 172H G-CIBB',64495,2,3),(5,'Eurofox 912iS G-ODGC',0,11,3),(6,'Cessna 182T G1000 G-CEFV',205000,2,3),(7,'Piper PA28 Cherokee Arrow III G-CBPI',179995,3,3),(8,'SR22 TN G3 GTS, G-ENKH',375000,4,3),(9,'Piper PA28R Cherokee Arrow 180 N900PH',39500,3,3);
GO

-- INSERT INTO `products_pictures` VALUES (2,13),(2,14),(2,15),(3,16),(3,17),(3,18),(4,19),(4,20),(4,21),(5,22),(5,23),(5,24),(6,25),(6,26),(6,27),(7,28),(7,29),(7,30),(8,31),(8,32),(8,33),(9,34),(9,35),(9,36);
-- GO