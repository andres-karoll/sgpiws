-- MySQL Workbench Synchronization
-- Generated: 2021-09-06 09:09
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Chokolate

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `sgpi_db`.`producto` 
DROP COLUMN `fecha`;

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

ALTER TABLE `sgpi_db`.`evento` 
DROP COLUMN `fecha_fin`,
DROP COLUMN `fecha_inicio`,
ADD COLUMN `fecha` DATETIME NOT NULL AFTER `nombre`;

ALTER TABLE `sgpi_db`.`programas_semilleros` 
DROP INDEX `fk_programas_semillero_semillero_idx` ,
ADD INDEX `fk_programas_semillero_semillero_idx` (`semillero` ASC);
;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
