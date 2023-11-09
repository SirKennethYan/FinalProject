-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rainbowdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `rainbowdb` ;

-- -----------------------------------------------------
-- Schema rainbowdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rainbowdb` DEFAULT CHARACTER SET utf8 ;
USE `rainbowdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL,
  `about_me` TEXT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(45) NULL,
  `active` TINYINT NULL,
  `longitude` DOUBLE NULL,
  `latitude` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle` ;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `make` VARCHAR(45) NULL,
  `model` VARCHAR(45) NULL,
  `estimated_mpg` INT NULL,
  `estimated_range` DOUBLE NULL,
  `capacity` INT NULL,
  `user_id` INT NOT NULL,
  `is_electric` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vehicle_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_vehicle_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `destination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `destination` ;

CREATE TABLE IF NOT EXISTS `destination` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address_id` INT NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `notes` TEXT NULL,
  `phone` VARCHAR(45) NULL,
  `fee` DECIMAL(5,2) NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_destination_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_destination_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trip`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trip` ;

CREATE TABLE IF NOT EXISTS `trip` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `roundtrip` TINYINT NULL,
  `miles` INT NULL,
  `user_id` INT NOT NULL,
  `vehicle_id` INT NULL,
  `create_date` DATETIME NULL,
  `update_date` DATETIME NULL,
  `title` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(2000) NULL,
  `active` TINYINT NULL,
  `start_destination_id` INT NOT NULL,
  `end_destination_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trip_user_idx` (`user_id` ASC),
  INDEX `fk_trip_vehicle1_idx` (`vehicle_id` ASC),
  INDEX `fk_trip_destination1_idx` (`start_destination_id` ASC),
  INDEX `fk_trip_destination2_idx` (`end_destination_id` ASC),
  CONSTRAINT `fk_trip_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_vehicle1`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_destination1`
    FOREIGN KEY (`start_destination_id`)
    REFERENCES `destination` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_destination2`
    FOREIGN KEY (`end_destination_id`)
    REFERENCES `destination` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `photo` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  `trip_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `comment_date` DATETIME NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_trip1_idx` (`trip_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_trip1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `leg`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leg` ;

CREATE TABLE IF NOT EXISTS `leg` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `estimated_miles` INT NULL,
  `trip_id` INT NOT NULL,
  `actual_miles` INT NULL,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `start_destination_id` INT NOT NULL,
  `end_destination_id` INT NOT NULL,
  `leg_number` INT NOT NULL,
  `notes` TEXT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_leg_trip1_idx` (`trip_id` ASC),
  INDEX `fk_leg_destination1_idx` (`start_destination_id` ASC),
  INDEX `fk_leg_destination2_idx` (`end_destination_id` ASC),
  CONSTRAINT `fk_leg_trip1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_leg_destination1`
    FOREIGN KEY (`start_destination_id`)
    REFERENCES `destination` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_leg_destination2`
    FOREIGN KEY (`end_destination_id`)
    REFERENCES `destination` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `accomodation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accomodation` ;

CREATE TABLE IF NOT EXISTS `accomodation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `icon_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `accomodation_has_destination`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accomodation_has_destination` ;

CREATE TABLE IF NOT EXISTS `accomodation_has_destination` (
  `accomodation_id` INT NOT NULL,
  `destination_id` INT NOT NULL,
  PRIMARY KEY (`accomodation_id`, `destination_id`),
  INDEX `fk_accomodation_has_destination_destination1_idx` (`destination_id` ASC),
  INDEX `fk_accomodation_has_destination_accomodation1_idx` (`accomodation_id` ASC),
  CONSTRAINT `fk_accomodation_has_destination_accomodation1`
    FOREIGN KEY (`accomodation_id`)
    REFERENCES `accomodation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accomodation_has_destination_destination1`
    FOREIGN KEY (`destination_id`)
    REFERENCES `destination` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity` ;

CREATE TABLE IF NOT EXISTS `activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `leg_id` INT NULL,
  `destination_id` INT NULL,
  `did_stop` TINYINT NULL,
  `description` TEXT NULL,
  `priority_level` INT NULL,
  `time_to_spend` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_leg_has_destination_destination1_idx` (`destination_id` ASC),
  INDEX `fk_leg_has_destination_leg1_idx` (`leg_id` ASC),
  CONSTRAINT `fk_leg_has_destination_leg1`
    FOREIGN KEY (`leg_id`)
    REFERENCES `leg` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_leg_has_destination_destination1`
    FOREIGN KEY (`destination_id`)
    REFERENCES `destination` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `trip_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trip_has_user` ;

CREATE TABLE IF NOT EXISTS `trip_has_user` (
  `trip_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`trip_id`, `user_id`),
  INDEX `fk_trip_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_trip_has_user_trip1_idx` (`trip_id` ASC),
  CONSTRAINT `fk_trip_has_user_trip1`
    FOREIGN KEY (`trip_id`)
    REFERENCES `trip` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trip_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_friend` ;

CREATE TABLE IF NOT EXISTS `user_has_friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_has_user_user2_idx` (`friend_id` ASC),
  INDEX `fk_user_has_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_image` ;

CREATE TABLE IF NOT EXISTS `activity_image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(2000) NULL,
  `caption` TEXT NULL,
  `activity_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_image_activity1_idx` (`activity_id` ASC),
  CONSTRAINT `fk_activity_image_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_rating` ;

CREATE TABLE IF NOT EXISTS `activity_rating` (
  `user_id` INT NOT NULL,
  `activity_id` INT NOT NULL,
  `rating` INT NULL,
  `rating_comment` TEXT NULL,
  `rating_date` DATETIME NULL,
  PRIMARY KEY (`user_id`, `activity_id`),
  INDEX `fk_user_has_activity_activity1_idx` (`activity_id` ASC),
  INDEX `fk_user_has_activity_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_activity_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS rainbowuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'rainbowuser'@'localhost' IDENTIFIED BY 'rainbowuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'rainbowuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `phone`, `image_url`, `about_me`, `create_date`, `update_date`, `active`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'Admin', 'Thurman', 'Merman', '544-344-4322', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3Mn3XDhKZOxXrtkvl4iY7ND6qfeFEMVWgXQ&usqp=CAU', '\"There is no theory of evolution. There’s only a list of animals Chuck Norris allows to live.\"', '2023-04-23', NULL, true);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `phone`, `image_url`, `about_me`, `create_date`, `update_date`, `active`) VALUES (2, 'kenny', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'User', 'Kenny', 'Yan', '322-424-7488', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm4ohG6k87EJB7waa501NbsXPiFujPrGHGIA&usqp=CAU', 'Skill Distillery Graduate !', '2023-04-23', NULL, true);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `phone`, `image_url`, `about_me`, `create_date`, `update_date`, `active`) VALUES (3, 'collin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'User', 'Collin', 'Wheat', '740-445-9884', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSx6z5p1wLD5OBTOyvjwX3Bc3B5AtKBCbWBeA&usqp=CAU', 'Skill Distillery Graduate !', '2023-04-23', NULL, true);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `phone`, `image_url`, `about_me`, `create_date`, `update_date`, `active`) VALUES (4, 'angel', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'User', 'Angel G', 'Guevara', '334-432-2344', 'https://images-na.ssl-images-amazon.com/images/I/71GW59ur2AL._AC_UL210_SR210,210_.jpg', 'Skill Distillery Graduate !', '2023-04-23', NULL, true);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `phone`, `image_url`, `about_me`, `create_date`, `update_date`, `active`) VALUES (5, 'david', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'User', 'David', 'Dunlevy', '375-334-2773', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-UKSIEYJWfeOGjjKfm0chCvQYOSFbw_TzVg&usqp=CAU', 'Skill Distillery Graduate !', '2023-04-23', NULL, true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (1, '7400 E Orchard Rd', NULL, 'Denver', 'Colorado', '80111', NULL, -104.90272, 39.608785);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (2, '1032 Learned Ave', NULL, 'Salt Lake City', 'Utah', '84116', NULL, -111.891111, 40.760833);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (3, '3131 Las Vegas Blvd', NULL, 'Las Vegas', 'Nevada', '89109', true, -115.136389, 36.175);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (4, '1600 Amphitheater Pkwy', NULL, 'Mountain View', 'California', '94043', true, -122.45422660252977, 37.389444);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (5, '2121 E Craig Rd', NULL, 'Las Vegas', 'Nevada', '89030', true, -115.118824, 36.238521);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (6, '2222 Shearn St', NULL, 'Houston', 'Texas', '77007', true, -95.3836231, 29.7680711);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (7, '237 Hermitage Ave', NULL, 'Nashville', 'Tennessee', '37210', true, -86.7652816, 36.1520845);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (8, '1238 DeKalb Ave', NULL, 'Atlanta', 'Georga', '30307', true, -84.3913665, 33.7586154);
INSERT INTO `address` (`id`, `street`, `street2`, `city`, `state`, `zip`, `active`, `longitude`, `latitude`) VALUES (9, '22 greenbriar', NULL, 'Columbus ', 'Ohio', '43125', true,  -83.000556, 39.962222);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vehicle`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (1, 'Toyota', 'Carolla', 23, 300, 4, 1, false);
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (2, 'Ford', 'Ranger', 18, 350, 2, 1, false);
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (3, 'Dodge', 'Charger', 18, 450, 5, 2, false);
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (4, 'Tesla', 'Model S', null, 500, 6, 2, true);
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (5, 'Nissan ', 'Leaf', null, 500, 5, 3, true);
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (6, 'BMW', 'I3', null, 450, 5, 4, true);
INSERT INTO `vehicle` (`id`, `make`, `model`, `estimated_mpg`, `estimated_range`, `capacity`, `user_id`, `is_electric`) VALUES (7, 'Jeep', 'Wrangler', 17, 550, 5, 5, false);

COMMIT;


-- -----------------------------------------------------
-- Data for table `destination`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (1, 'McDonalds', 1, 'food', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLM3TZRu5pu01awnakUTlIk0FoKk-7J36zfQ&usqp=CAU', NULL, '777-353-2242', NULL, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (2, 'Shell', 2, 'gas', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAR3H124hCNTvyjc8Tt-c7dlqw8m6zLHzX3w&usqp=CAU', NULL, '453-664-5325', 2.89, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (3, 'Hilton', 3, 'hotel', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMKBenOrt4DW-rHUFDGuJO-6EWZva0Wys59A&usqp=CAU', NULL, '876-332-2763', 99.99, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (4, 'Taco Bell', 4, 'food', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHU02W1VHs___NC4kRo4A8uc_2X43Nw449DQ&usqp=CAU', NULL, '247-538-9822', NULL, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (5, 'Wing Stop', 5, 'food', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQuKMu7wIe11DPkpubVPlyS1VZk7BNZoZk8w&usqp=CAU', NULL, '422-944-4782', NULL, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (6, 'BP', 6, 'gas', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9NtdP6h03-guzDahvsLyc3ulZwzRCyl2cvw&usqp=CAU', NULL, '740-442-2031', 2.98, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (7, 'Quik Trip', 7, 'gas', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7kD9RDqwUX1k0srjcY4ugDqwsDZ2hRRuj9A&usqp=CAU', NULL, '322-326-8831', 2.75, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (8, 'Holiday Inn', 8, 'hotel', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBrRKXN5bQ95rxdrfBFBR4gPypzF8jXFk8ow&usqp=CAU', NULL, '322-211-3197', NULL, true);
INSERT INTO `destination` (`id`, `name`, `address_id`, `description`, `image_url`, `notes`, `phone`, `fee`, `active`) VALUES (9, 'Worlds largest ball of string', 9, 'sight', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9otaBWkFcevBdEW4Wfwllg2d3DNdfvteDpA&usqp=CAU', NULL, NULL, 5.00, true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trip`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `trip` (`id`, `start_date`, `end_date`, `roundtrip`, `miles`, `user_id`, `vehicle_id`, `create_date`, `update_date`, `title`, `description`, `image_url`, `active`, `start_destination_id`, `end_destination_id`) VALUES (1, '2023-04-23', '2023-04-29', true, 3500, 1, 1, NULL, NULL, 'Skill Distillery Alum Tour', 'Cross Country Trip!', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-CYcYaVQnP9DF8EQ7fIgEL-raKRaXNO7uqpRgSaOVQSqU6K6rovc9mLYDc2plipuboIA&usqp=CAU', true, 4, 9);
INSERT INTO `trip` (`id`, `start_date`, `end_date`, `roundtrip`, `miles`, `user_id`, `vehicle_id`, `create_date`, `update_date`, `title`, `description`, `image_url`, `active`, `start_destination_id`, `end_destination_id`) VALUES (2, '2019-01-01', '2019-01-09', true, 600, 2, 3, NULL, NULL, 'Vegas Trip', 'My first trip from Denver to Vegas', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZXWedvqc3ZZqnTIqlp-Kyi0BWTBOknEJ3Dwmn2-v3kHcwzyXOdHT_LdGr-vNG4dXtUKg&usqp=CAU', false, 3, 1);
INSERT INTO `trip` (`id`, `start_date`, `end_date`, `roundtrip`, `miles`, `user_id`, `vehicle_id`, `create_date`, `update_date`, `title`, `description`, `image_url`, `active`, `start_destination_id`, `end_destination_id`) VALUES (3, '2022-06-03', '2022-07-01', false, 366, 2, 5, NULL, NULL, 'Summer Break', '', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPCWS4qr1QByDcUkr-9uIajXFPFsxCkqlsBCZh9Wr2Osywiu5z14_fQfNkiFsDvEx9Bec&usqp=CAU', false, 1, 2);
INSERT INTO `trip` (`id`, `start_date`, `end_date`, `roundtrip`, `miles`, `user_id`, `vehicle_id`, `create_date`, `update_date`, `title`, `description`, `image_url`, `active`, `start_destination_id`, `end_destination_id`) VALUES (4, '2023-04-30', '2023-05-05', false, 344, 3, 1, NULL, NULL, 'Last trip of the year', '', 'https://us.123rf.com/450wm/edgarbullon/edgarbullon2005/edgarbullon200500265/148254276-epic-adventurous-extreme-sport-composite-of-rock-climbing-man-rappelling-from-a-cliff-mountain.jpg?ver=6', true, 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `comment` (`id`, `photo`, `description`, `trip_id`, `user_id`, `comment_date`, `active`) VALUES (1, NULL, 'Lets meet up \'round noon', 1, 1, '2023-04-24', true);
INSERT INTO `comment` (`id`, `photo`, `description`, `trip_id`, `user_id`, `comment_date`, `active`) VALUES (2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDUHh80Xfyjpftoj27riiA5j4pEqXhWdTi6g&usqp=CAU', 'Cant Wait!', 1, 3, '2023-04-24', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `leg`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `leg` (`id`, `estimated_miles`, `trip_id`, `actual_miles`, `name`, `description`, `start_destination_id`, `end_destination_id`, `leg_number`, `notes`, `active`) VALUES (1, 570, 1, null, 'Going to vegas !', NULL, 4, 5, 1, NULL, true);
INSERT INTO `leg` (`id`, `estimated_miles`, `trip_id`, `actual_miles`, `name`, `description`, `start_destination_id`, `end_destination_id`, `leg_number`, `notes`, `active`) VALUES (2, 1400, 1, null, 'On to Huston', NULL, 5, 6, 2, NULL, true);
INSERT INTO `leg` (`id`, `estimated_miles`, `trip_id`, `actual_miles`, `name`, `description`, `start_destination_id`, `end_destination_id`, `leg_number`, `notes`, `active`) VALUES (3, 780, 1, NULL, 'Nashville here we come', NULL, 6, 7, 3, NULL, true);
INSERT INTO `leg` (`id`, `estimated_miles`, `trip_id`, `actual_miles`, `name`, `description`, `start_destination_id`, `end_destination_id`, `leg_number`, `notes`, `active`) VALUES (4, 250, 1, NULL, 'Hotlanta', NULL, 7, 8, 4, NULL, true);
INSERT INTO `leg` (`id`, `estimated_miles`, `trip_id`, `actual_miles`, `name`, `description`, `start_destination_id`, `end_destination_id`, `leg_number`, `notes`, `active`) VALUES (5, 570, 1, NULL, 'Columbus ', NULL, 8, 9, 5, NULL, true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `accomodation`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `accomodation` (`id`, `name`, `description`, `icon_url`) VALUES (1, 'Gas', NULL, NULL);
INSERT INTO `accomodation` (`id`, `name`, `description`, `icon_url`) VALUES (2, 'EV Charger', NULL, NULL);
INSERT INTO `accomodation` (`id`, `name`, `description`, `icon_url`) VALUES (3, 'Pet Friendly', NULL, NULL);
INSERT INTO `accomodation` (`id`, `name`, `description`, `icon_url`) VALUES (4, 'Kid Friendly', NULL, NULL);
INSERT INTO `accomodation` (`id`, `name`, `description`, `icon_url`) VALUES (5, 'Food', NULL, NULL);
INSERT INTO `accomodation` (`id`, `name`, `description`, `icon_url`) VALUES (6, 'Pool', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `accomodation_has_destination`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `accomodation_has_destination` (`accomodation_id`, `destination_id`) VALUES (1, 2);
INSERT INTO `accomodation_has_destination` (`accomodation_id`, `destination_id`) VALUES (2, 2);
INSERT INTO `accomodation_has_destination` (`accomodation_id`, `destination_id`) VALUES (5, 1);
INSERT INTO `accomodation_has_destination` (`accomodation_id`, `destination_id`) VALUES (4, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `activity` (`id`, `leg_id`, `destination_id`, `did_stop`, `description`, `priority_level`, `time_to_spend`) VALUES (1, 1, 1, true, 'lunch break', 5, '1 hour');
INSERT INTO `activity` (`id`, `leg_id`, `destination_id`, `did_stop`, `description`, `priority_level`, `time_to_spend`) VALUES (2, 2, 3, true, 'sleep', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `trip_has_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `trip_has_user` (`trip_id`, `user_id`) VALUES (1, 1);
INSERT INTO `trip_has_user` (`trip_id`, `user_id`) VALUES (2, 3);
INSERT INTO `trip_has_user` (`trip_id`, `user_id`) VALUES (3, 2);
INSERT INTO `trip_has_user` (`trip_id`, `user_id`) VALUES (4, 2);
INSERT INTO `trip_has_user` (`trip_id`, `user_id`) VALUES (4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `user_has_friend` (`user_id`, `friend_id`) VALUES (1, 2);
INSERT INTO `user_has_friend` (`user_id`, `friend_id`) VALUES (1, 3);
INSERT INTO `user_has_friend` (`user_id`, `friend_id`) VALUES (2, 3);
INSERT INTO `user_has_friend` (`user_id`, `friend_id`) VALUES (1, 4);
INSERT INTO `user_has_friend` (`user_id`, `friend_id`) VALUES (3, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `activity_image` (`id`, `image_url`, `caption`, `activity_id`) VALUES (1, NULL, 'my first pic', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `rainbowdb`;
INSERT INTO `activity_rating` (`user_id`, `activity_id`, `rating`, `rating_comment`, `rating_date`) VALUES (1, 1, 5, 'best ever', NULL);

COMMIT;

