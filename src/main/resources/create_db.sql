CREATE SCHEMA `face_catch` ;

CREATE TABLE `face_catch`.`personnel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `image` MEDIUMBLOB NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `face_catch`.`profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `face_catch`.`personnel_profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `personnel_id` INT NOT NULL,
  `profilecol_id` INT NOT NULL,
  PRIMARY KEY (`id`));
  
  /*ALTER TABLE face_catch.personnel AUTO_INCREMENT = 4;*/