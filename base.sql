SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`instructor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`instructor` (
  `idInstructor` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(25) NULL DEFAULT NULL,
  `Apellido` VARCHAR(30) NULL DEFAULT NULL,
  `Contraseña` VARCHAR(15) NULL DEFAULT NULL,
  `Mail` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idInstructor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comentarios` (
  `idComentarios` INT(11) NOT NULL AUTO_INCREMENT,
  `Instructor_idInstructor` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idComentarios`, `Instructor_idInstructor`),
  INDEX `fk_Comentarios_Instructor1_idx` (`Instructor_idInstructor` ASC),
  CONSTRAINT `fk_Comentarios_Instructor1`
    FOREIGN KEY (`Instructor_idInstructor`)
    REFERENCES `mydb`.`instructor` (`idInstructor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`ejercicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ejercicios` (
  `idEjercicios` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idEjercicios`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`instruido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`instruido` (
  `idInstruido` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(25) NULL DEFAULT NULL,
  `Apellido` VARCHAR(45) NULL DEFAULT NULL,
  `Mail` VARCHAR(45) NULL DEFAULT NULL,
  `Contrasena` VARCHAR(15) NULL DEFAULT NULL,
  `Fecha` VARCHAR(25) NULL DEFAULT NULL,
  `Peso` INT(5) NULL DEFAULT NULL,
  `Altura` INT(5) NULL DEFAULT NULL,
  `Sexo` VARCHAR(10) NULL DEFAULT NULL,
  `Complicaciones` VARCHAR(45) NULL DEFAULT NULL,
  `FueAlGym` VARCHAR(15) NULL DEFAULT NULL,
  `Estado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idInstruido`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`rutinas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`rutinas` (
  `idRutinas` INT(11) NOT NULL AUTO_INCREMENT,
  `Calificacion` VARCHAR(45) NULL DEFAULT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRutinas`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '				';


-- -----------------------------------------------------
-- Table `mydb`.`rutinasejercicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`rutinasejercicios` (
  `idRutEj` INT(11) NOT NULL AUTO_INCREMENT,
  `Peso` INT(11) NOT NULL,
  `Series` INT(11) NOT NULL,
  `rutinas_idRutinas` INT(11) NOT NULL,
  `ejercicios_idEjercicios` INT(11) NOT NULL,
  PRIMARY KEY (`idRutEj`, `rutinas_idRutinas`, `ejercicios_idEjercicios`),
  INDEX `fk_rutinasejercicios_rutinas1_idx` (`rutinas_idRutinas` ASC),
  INDEX `fk_rutinasejercicios_ejercicios1_idx` (`ejercicios_idEjercicios` ASC),
  CONSTRAINT `fk_rutinasejercicios_rutinas1`
    FOREIGN KEY (`rutinas_idRutinas`)
    REFERENCES `mydb`.`rutinas` (`idRutinas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rutinasejercicios_ejercicios1`
    FOREIGN KEY (`ejercicios_idEjercicios`)
    REFERENCES `mydb`.`ejercicios` (`idEjercicios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`rutinas_has_instruido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`rutinas_has_instruido` (
  `rutinas_idRutinas` INT(11) NOT NULL,
  `instruido_idInstruido` INT(11) NOT NULL,
  PRIMARY KEY (`rutinas_idRutinas`, `instruido_idInstruido`),
  INDEX `fk_rutinas_has_instruido_instruido1_idx` (`instruido_idInstruido` ASC),
  INDEX `fk_rutinas_has_instruido_rutinas1_idx` (`rutinas_idRutinas` ASC),
  CONSTRAINT `fk_rutinas_has_instruido_rutinas1`
    FOREIGN KEY (`rutinas_idRutinas`)
    REFERENCES `mydb`.`rutinas` (`idRutinas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rutinas_has_instruido_instruido1`
    FOREIGN KEY (`instruido_idInstruido`)
    REFERENCES `mydb`.`instruido` (`idInstruido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `mydb` ;

-- -----------------------------------------------------
-- procedure Alta
-- -----------------------------------------------------

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Alta`()
BEGIN

INSERT INTO mydb.instruido (Nombre) VALUES (nom);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure traerNyA
-- -----------------------------------------------------

DELIMITER $$
USE `mydb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `traerNyA`()
BEGIN
SELECT Nombre,Apellido FROM Instruido;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;