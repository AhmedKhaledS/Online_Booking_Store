-- MySQL Script generated by MySQL Workbench
-- 13 ماي, 2018 EET 10:13:18 م
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Book_Store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Book_Store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Book_Store` DEFAULT CHARACTER SET utf8 ;
USE `Book_Store` ;

-- -----------------------------------------------------
-- Table `Book_Store`.`PUBLISHER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Book_Store`.`PUBLISHER` (
  `Publisher_id` INT NOT NULL,
  `Name` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `Telephone_number` VARCHAR(20) NULL,
  PRIMARY KEY (`Publisher_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Store`.`BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Book_Store`.`BOOK` (
  `ISBN` INT NOT NULL,
  `Title` VARCHAR(45) NULL,
  `Publisher_id` INT NULL,
  `Publication_year` YEAR NULL,
  `Category` ENUM('Science', 'Art', 'Religion', 'History', 'Geography') NULL,
  `Price` FLOAT NOT NULL,
  `No_of_copies` INT NOT NULL,
  `Min_Quantity` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  INDEX `Publisher_id_idx` (`Publisher_id` ASC),
  CONSTRAINT `Publisher_id_BOOK_AUTHORS_PUBLISHER_fk`
    FOREIGN KEY (`Publisher_id`)
    REFERENCES `Book_Store`.`PUBLISHER` (`Publisher_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Store`.`BOOK_AUTHORS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Book_Store`.`BOOK_AUTHORS` (
  `ISBN` INT NOT NULL,
  `Author_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ISBN`, `Author_name`),
  CONSTRAINT `ISBN_BOOK_AUTHORS_BOOK_fk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `Book_Store`.`BOOK` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Store`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Book_Store`.`USER` (
  `E_mail` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Lastname` VARCHAR(45) NOT NULL,
  `Firstname` VARCHAR(45) NOT NULL,
  `Phone_number` VARCHAR(20) NULL,
  `Shopping_address` VARCHAR(45) NOT NULL,
  `User_type` ENUM('Customer', 'Manager') NOT NULL,
  PRIMARY KEY (`E_mail`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Store`.`ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Book_Store`.`ORDER` (
  `E_mail` VARCHAR(45) NOT NULL,
  `ISBN` INT NOT NULL,
  `Quantity` INT NOT NULL,
  `State` ENUM('IN_PROGRESS', 'COMPLETED') NOT NULL,
  `Date` Date NOT NULL,
  `Order_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Order_id`, `E_mail`),
  INDEX `E_mail_idx` (`E_mail` ASC),
  INDEX `ISBN_idx` (`ISBN` ASC),
  CONSTRAINT `E_mail_ORDER_USER_fk`
    FOREIGN KEY (`E_mail`)
    REFERENCES `Book_Store`.`USER` (`E_mail`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `ISBN_ORDER_BOOK_fk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `Book_Store`.`BOOK` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Store`.`MANAGER_ORDERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Book_Store`.`MANAGER_ORDERS` (
  `Order_id` INT NOT NULL AUTO_INCREMENT,
  `ISBN` INT NOT NULL,
  `Quantity` INT NULL,
  PRIMARY KEY (`Order_id`),
  INDEX `ISBN_idx` (`ISBN` ASC),
  CONSTRAINT `ISBN_MANAGED_ORDERS_BOOK_fk`
    FOREIGN KEY (`ISBN`)
    REFERENCES `Book_Store`.`BOOK` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `Book_Store`;

DELIMITER $$
USE `Book_Store`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Store`.`BOOK_AFTER_UPDATE` AFTER UPDATE ON `BOOK`
FOR EACH ROW
BEGIN
	IF ( NEW.Min_quantity > NEW.No_of_copies )
    THEN
		INSERT INTO MANAGER_ORDERS (ISBN, Quantity) VALUES (NEW.ISBN, NEW.Min_quantity * 2);
    END IF;
END$$

USE `Book_Store`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Store`.`ORDER_BEFORE_INSERT` BEFORE INSERT ON `ORDER`
FOR EACH ROW
BEGIN
	IF ( ( (SELECT No_of_copies FROM BOOK WHERE ISBN = NEW.ISBN) - NEW.Quantity ) < 0)
    THEN
		SIGNAL SQLSTATE '45000' SET message_text = 'This quantity cannot be fetched.';
    END IF;
END$$

USE `Book_Store`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Store`.`ORDER_BEFORE_UPDATE` BEFORE UPDATE ON `ORDER`
FOR EACH ROW
BEGIN
	IF (OLD.State = 'IN_PROGRESS' AND NEW.State = 'COMPLETED')
    THEN
	UPDATE BOOK SET No_of_copies = No_of_copies - NEW.Quantity WHERE ISBN = NEW.ISBN;

	END IF;
END$$

USE `Book_Store`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Store`.`MANAGER_ORDERS_BEFORE_DELETE` BEFORE DELETE ON `MANAGER_ORDERS`
FOR EACH ROW
BEGIN
	UPDATE BOOK SET No_of_copies = No_of_copies + OLD.Quantity WHERE ISBN = OLD.ISBN;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
