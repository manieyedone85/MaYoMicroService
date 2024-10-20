CREATE TABLE `employees` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `updated_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `createdDate` DATETIME NOT NULL,
  `updatedDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`));