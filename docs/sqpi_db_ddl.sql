-- MySQL Workbench Synchronization
-- Generated: 2021-09-06 18:25
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Chokolate

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `sgpi_db`.`programa` 
DROP FOREIGN KEY `fk_programa_usuario`;

ALTER TABLE `sgpi_db`.`facultad` 
DROP FOREIGN KEY `fk_facultad_usuario_coor_inv`;

ALTER TABLE `sgpi_db`.`producto` 
DROP COLUMN `fecha`;

ALTER TABLE `sgpi_db`.`usuarios` 
DROP INDEX `fk_usuarios_tipo_usuario_idx` ,
ADD INDEX `fk_usuarios_tipo_usuario_idx` (`tipo_usuario` ASC);
;

ALTER TABLE `sgpi_db`.`programa` 
CHANGE COLUMN `director` `director` VARCHAR(50) NULL DEFAULT NULL ;

ALTER TABLE `sgpi_db`.`facultad` 
CHANGE COLUMN `decano` `decano` VARCHAR(20) NULL DEFAULT NULL ,
CHANGE COLUMN `coor_inv` `coor_inv` VARCHAR(20) NULL DEFAULT NULL ;

ALTER TABLE `sgpi_db`.`proyectos_clase` 
DROP INDEX `fk_proyectos_clase_proyecto_idx` ,
ADD INDEX `fk_proyectos_clase_proyecto_idx` (`proyecto` ASC);
;

ALTER TABLE `sgpi_db`.`antecedentes` 
DROP INDEX `fk_antecedentes_proyecto_antecedente_idx` ,
ADD INDEX `fk_antecedentes_proyecto_antecedente_idx` (`ancedente` ASC);
;

ALTER TABLE `sgpi_db`.`evento` 
DROP COLUMN `fecha_fin`,
DROP COLUMN `fecha_inicio`,
ADD COLUMN `fecha` DATETIME NOT NULL AFTER `nombre`;

ALTER TABLE `sgpi_db`.`programas_semilleros` 
DROP INDEX `fk_programas_semillero_semillero_idx` ,
ADD INDEX `fk_programas_semillero_semillero_idx` (`semillero` ASC);
;

ALTER TABLE `sgpi_db`.`programa` 
ADD CONSTRAINT `fk_programa_usuario`
  FOREIGN KEY (`director`)
  REFERENCES `sgpi_db`.`usuario` (`cedula`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sgpi_db`.`facultad` 
DROP FOREIGN KEY `fk_facultad_usuario_decano`;

ALTER TABLE `sgpi_db`.`facultad` ADD CONSTRAINT `fk_facultad_usuario_decano`
  FOREIGN KEY (`decano`)
  REFERENCES `sgpi_db`.`usuario` (`cedula`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_facultad_usuario_coor_inv`
  FOREIGN KEY (`coor_inv`)
  REFERENCES `sgpi_db`.`usuario` (`cedula`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
