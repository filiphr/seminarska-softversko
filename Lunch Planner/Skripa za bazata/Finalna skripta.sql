SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `DBSoftversko` ;
CREATE SCHEMA IF NOT EXISTS `DBSoftversko` DEFAULT CHARACTER SET utf8 ;
USE `DBSoftversko` ;

-- -----------------------------------------------------
-- Table `DBSoftversko`.`korisnik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`korisnik` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`korisnik` (
  `Ime` VARCHAR(30) NULL DEFAULT NULL ,
  `Prezime` VARCHAR(40) NULL DEFAULT NULL ,
  `User` VARCHAR(15) NOT NULL ,
  `Email` VARCHAR(45) NULL DEFAULT NULL ,
  `Password` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`User`) ,
  UNIQUE INDEX `User_UNIQUE` (`User` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`restoran`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`restoran` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`restoran` (
  `Ime` VARCHAR(50) NOT NULL ,
  `Adresa` VARCHAR(45) NULL DEFAULT NULL ,
  `Telefon` VARCHAR(25) NULL ,
  PRIMARY KEY (`Ime`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`stavkameni`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`stavkameni` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`stavkameni` (
  `Ime` VARCHAR(70) NOT NULL ,
  PRIMARY KEY (`Ime`) ,
  UNIQUE INDEX `Ime_UNIQUE` (`Ime` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`arhiviranagrupa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`arhiviranagrupa` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`arhiviranagrupa` (
  `Vreme` DATE NOT NULL ,
  `Korisnik_User` VARCHAR(15) NOT NULL ,
  `Restoran` VARCHAR(45) NULL ,
  `StavkaMeni` VARCHAR(45) NULL ,
  `ID` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`ID`) ,
  CONSTRAINT `fk_ArhiviranaGrupa_Korisnik1`
    FOREIGN KEY (`Korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`meni`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`meni` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`meni` (
  `idMeni` INT(11) NOT NULL AUTO_INCREMENT ,
  `Cena` INT(11) NULL DEFAULT NULL ,
  `Restoran_Ime` VARCHAR(50) NOT NULL ,
  `stavkameni_Ime` VARCHAR(70) NOT NULL ,
  PRIMARY KEY (`idMeni`) ,
  INDEX `fk_Meni_Restoran1` (`Restoran_Ime` ASC) ,
  INDEX `fk_meni_stavkameni1` (`stavkameni_Ime` ASC) ,
  CONSTRAINT `fk_Meni_Restoran1`
    FOREIGN KEY (`Restoran_Ime` )
    REFERENCES `DBSoftversko`.`restoran` (`Ime` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_meni_stavkameni1`
    FOREIGN KEY (`stavkameni_Ime` )
    REFERENCES `DBSoftversko`.`stavkameni` (`Ime` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`tekovnagrupa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`tekovnagrupa` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`tekovnagrupa` (
  `idTekovnaGrupa` INT(11) NOT NULL AUTO_INCREMENT ,
  `Vreme` TIME NULL DEFAULT NULL ,
  `Korisnik_User` VARCHAR(15) NOT NULL ,
  `Restoran_Ime` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`idTekovnaGrupa`) ,
  INDEX `fk_TekovnaGrupa_Korisnik1` (`Korisnik_User` ASC) ,
  INDEX `fk_TekovnaGrupa_Restoran1` (`Restoran_Ime` ASC) ,
  CONSTRAINT `fk_TekovnaGrupa_Korisnik1`
    FOREIGN KEY (`Korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TekovnaGrupa_Restoran1`
    FOREIGN KEY (`Restoran_Ime` )
    REFERENCES `DBSoftversko`.`restoran` (`Ime` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`naracka`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`naracka` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`naracka` (
  `idNaracka` INT(11) NOT NULL AUTO_INCREMENT ,
  `Komentar` VARCHAR(250) NULL DEFAULT NULL ,
  `Korisnik_User` VARCHAR(15) NOT NULL ,
  `TekovnaGrupa_idTekovnaGrupa` INT(11) NOT NULL ,
  `stavkameni_Ime` VARCHAR(70) NULL DEFAULT NULL ,
  `Naracal_User` VARCHAR(15) NULL ,
  PRIMARY KEY (`idNaracka`) ,
  INDEX `fk_Naracka_Korisnik` (`Korisnik_User` ASC) ,
  INDEX `fk_Naracka_TekovnaGrupa1` (`TekovnaGrupa_idTekovnaGrupa` ASC) ,
  INDEX `fk_naracka_stavkameni1` (`stavkameni_Ime` ASC) ,
  INDEX `fk_naracka_korisnik1` (`Naracal_User` ASC) ,
  CONSTRAINT `fk_Naracka_Korisnik`
    FOREIGN KEY (`Korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Naracka_TekovnaGrupa1`
    FOREIGN KEY (`TekovnaGrupa_idTekovnaGrupa` )
    REFERENCES `DBSoftversko`.`tekovnagrupa` (`idTekovnaGrupa` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_naracka_stavkameni1`
    FOREIGN KEY (`stavkameni_Ime` )
    REFERENCES `DBSoftversko`.`stavkameni` (`Ime` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_naracka_korisnik1`
    FOREIGN KEY (`Naracal_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`preferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`preferences` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`preferences` (
  `Vreme` TIME NULL DEFAULT NULL ,
  `Korisnik_User` VARCHAR(15) NOT NULL ,
  `Restoran_Ime` VARCHAR(50) NULL DEFAULT NULL ,
  `stavkameni_Ime` VARCHAR(70) NULL DEFAULT NULL ,
  `Komentar` VARCHAR(250) NULL DEFAULT NULL ,
  PRIMARY KEY (`Korisnik_User`) ,
  INDEX `fk_Preferences_Korisnik1` (`Korisnik_User` ASC) ,
  INDEX `fk_Preferences_Restoran1` (`Restoran_Ime` ASC) ,
  INDEX `fk_preferences_stavkameni1` (`stavkameni_Ime` ASC) ,
  CONSTRAINT `fk_Preferences_Korisnik1`
    FOREIGN KEY (`Korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Preferences_Restoran1`
    FOREIGN KEY (`Restoran_Ime` )
    REFERENCES `DBSoftversko`.`restoran` (`Ime` )
    ON DELETE SET NULL
    ON UPDATE SET NULL,
  CONSTRAINT `fk_preferences_stavkameni1`
    FOREIGN KEY (`stavkameni_Ime` )
    REFERENCES `DBSoftversko`.`stavkameni` (`Ime` )
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`korisnik_has_preferences`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`korisnik_has_preferences` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`korisnik_has_preferences` (
  `korisnik_User` VARCHAR(15) NOT NULL ,
  `preferences_Korisnik_User` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`korisnik_User`, `preferences_Korisnik_User`) ,
  INDEX `fk_korisnik_has_preferences_preferences1` (`preferences_Korisnik_User` ASC) ,
  INDEX `fk_korisnik_has_preferences_korisnik1` (`korisnik_User` ASC) ,
  CONSTRAINT `fk_korisnik_has_preferences_korisnik1`
    FOREIGN KEY (`korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_korisnik_has_preferences_preferences1`
    FOREIGN KEY (`preferences_Korisnik_User` )
    REFERENCES `DBSoftversko`.`preferences` (`Korisnik_User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`Pokani`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`Pokani` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`Pokani` (
  `korisnik_User` VARCHAR(15) NOT NULL ,
  `tekovnagrupa_idTekovnaGrupa` INT(11) NOT NULL ,
  PRIMARY KEY (`korisnik_User`, `tekovnagrupa_idTekovnaGrupa`) ,
  INDEX `fk_korisnik_has_tekovnagrupa_tekovnagrupa1` (`tekovnagrupa_idTekovnaGrupa` ASC) ,
  INDEX `fk_korisnik_has_tekovnagrupa_korisnik1` (`korisnik_User` ASC) ,
  CONSTRAINT `fk_korisnik_has_tekovnagrupa_korisnik1`
    FOREIGN KEY (`korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_korisnik_has_tekovnagrupa_tekovnagrupa1`
    FOREIGN KEY (`tekovnagrupa_idTekovnaGrupa` )
    REFERENCES `DBSoftversko`.`tekovnagrupa` (`idTekovnaGrupa` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`Notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`Notification` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`Notification` (
  `IDNotification` INT NOT NULL AUTO_INCREMENT ,
  `Notification` VARCHAR(250) NOT NULL ,
  `korisnik_User` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`IDNotification`) ,
  INDEX `fk_Notification_korisnik1` (`korisnik_User` ASC) ,
  CONSTRAINT `fk_Notification_korisnik1`
    FOREIGN KEY (`korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBSoftversko`.`Administrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DBSoftversko`.`Administrator` ;

CREATE  TABLE IF NOT EXISTS `DBSoftversko`.`Administrator` (
  `korisnik_User` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`korisnik_User`) ,
  CONSTRAINT `fk_Administrator_korisnik1`
    FOREIGN KEY (`korisnik_User` )
    REFERENCES `DBSoftversko`.`korisnik` (`User` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
