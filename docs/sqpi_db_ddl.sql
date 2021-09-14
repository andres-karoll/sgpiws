-- MySQL Workbench Synchronization
-- Generated: 2021-09-14 16:06
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Chokolate

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `sgpi_db`.`clase` 
DROP FOREIGN KEY `fk_clase_usuario`;

ALTER TABLE `sgpi_db`.`usuario` 
DROP COLUMN `visibilidad`,
ADD COLUMN `visiblidad` VARCHAR(50) NOT NULL AFTER `telefono`;

ALTER TABLE `sgpi_db`.`clase` 
CHANGE COLUMN `profesor` `profesor` VARCHAR(20) NULL DEFAULT NULL ;

ALTER TABLE `sgpi_db`.`usuarios` 
DROP INDEX `fk_usuarios_tipo_usuario_idx` ,
ADD INDEX `fk_usuarios_tipo_usuario_idx` (`tipo_usuario` ASC);
;

ALTER TABLE `sgpi_db`.`proyectos_clase` 
DROP INDEX `fk_proyectos_clase_proyecto_idx` ,
ADD INDEX `fk_proyectos_clase_proyecto_idx` (`proyecto` ASC);
;

ALTER TABLE `sgpi_db`.`antecedentes` 
DROP INDEX `fk_antecedentes_proyecto_antecedente_idx` ,
ADD INDEX `fk_antecedentes_proyecto_antecedente_idx` (`ancedente` ASC);
;

ALTER TABLE `sgpi_db`.`programas_semilleros` 
DROP INDEX `fk_programas_semillero_semillero_idx` ,
ADD INDEX `fk_programas_semillero_semillero_idx` (`semillero` ASC);
;

ALTER TABLE `sgpi_db`.`clase` 
ADD CONSTRAINT `fk_clase_usuario`
  FOREIGN KEY (`profesor`)
  REFERENCES `sgpi_db`.`usuario` (`cedula`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
