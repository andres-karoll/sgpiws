-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sgpi_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sgpi_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sgpi_db` DEFAULT CHARACTER SET utf8 ;
CREATE USER 'sgpi_db'@'%' IDENTIFIED BY 'SGPI2021';
GRANT ALL PRIVILEGES ON `sgpi_db`.* TO 'sgpi_db'@'%';
FLUSH PRIVILEGES;
USE `sgpi_db` ;

-- -----------------------------------------------------
-- Table `sgpi_db`.`grupo_investigacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`grupo_investigacion` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `fecha_fun` DATE NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `fecha_cat` DATE NOT NULL,
  `director_grupo` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_grupo_investigacion_usuario_idx` (`director_grupo` ASC),
  CONSTRAINT `fk_grupo_investigacion_usuario`
    FOREIGN KEY (`director_grupo`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`linea_investigacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`linea_investigacion` (
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(150) NOT NULL,
  `fecha` DATE NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`semillero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`semillero` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `fecha_fun` DATE NOT NULL,
  `grupo_investigacion` INT NOT NULL,
  `lider_semillero` VARCHAR(20) NOT NULL,
  `linea_investigacion` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_semillero_grupo_investigacion_idx` (`grupo_investigacion` ASC),
  INDEX `fk_semillero_usuario_idx` (`lider_semillero` ASC),
  INDEX `fk_semillero_linea_investigacion_idx` (`linea_investigacion` ASC),
  CONSTRAINT `fk_semillero_grupo_investigacion`
    FOREIGN KEY (`grupo_investigacion`)
    REFERENCES `sgpi_db`.`grupo_investigacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_semillero_usuario`
    FOREIGN KEY (`lider_semillero`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_semillero_linea_investigacion`
    FOREIGN KEY (`linea_investigacion`)
    REFERENCES `sgpi_db`.`linea_investigacion` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`facultad` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `decano` VARCHAR(20) NOT NULL,
  `coor_inv` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_facultad_usuario_decano_idx` (`decano` ASC),
  INDEX `fk_facultad_usuario_coor_inv_idx` (`coor_inv` ASC),
  CONSTRAINT `fk_facultad_usuario_decano`
    FOREIGN KEY (`decano`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_facultad_usuario_coor_inv`
    FOREIGN KEY (`coor_inv`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`programa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`programa` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `facultad_id` INT NOT NULL,
  `director` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_programa_facultad_idx` (`facultad_id` ASC),
  INDEX `fk_programa_usuario_idx` (`director` ASC),
  CONSTRAINT `fk_programa_facultad`
    FOREIGN KEY (`facultad_id`)
    REFERENCES `sgpi_db`.`facultad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_programa_usuario`
    FOREIGN KEY (`director`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`usuario` (
  `cedula` VARCHAR(20) NOT NULL,
  `cod_universitario` INT(11) NOT NULL,
  `correo_est` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(100) NOT NULL,
  `nombres` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `visiblidad` VARCHAR(50) NOT NULL,
  `correo_personal` VARCHAR(45) NULL,
  `semillero_id` INT NULL,
  `programa_id` INT NOT NULL,
  PRIMARY KEY (`cedula`),
  UNIQUE INDEX `cod_estudiantil_UNIQUE` (`cod_universitario` ASC),
  UNIQUE INDEX `correo_est_UNIQUE` (`correo_est` ASC),
  INDEX `fk_usuario_semillero_idx` (`semillero_id` ASC),
  INDEX `fk_usuario_programa_idx` (`programa_id` ASC),
  CONSTRAINT `fk_usuario_semillero`
    FOREIGN KEY (`semillero_id`)
    REFERENCES `sgpi_db`.`semillero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_programa`
    FOREIGN KEY (`programa_id`)
    REFERENCES `sgpi_db`.`programa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`macro_proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`macro_proyecto` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` LONGTEXT NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`tipo_proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`tipo_proyecto` (
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`proyecto` (
  `id` INT(6) NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `descripcion` LONGTEXT NOT NULL,
  `macro_proyecto` INT NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL,
  `semillero` INT NULL,
  `retroalimentacion_final` LONGTEXT NULL,
  `visibilidad` TINYINT NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `metodologia` LONGTEXT NOT NULL,
  `conclusiones` LONGTEXT NULL,
  `justificacion` LONGTEXT NOT NULL,
  `tipo_proyecto` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_proyecto_macro_proyecto_idx` (`macro_proyecto` ASC),
  INDEX `fk_proyecto_semillero_idx` (`semillero` ASC),
  INDEX `fk_proyecto_tipo_proyecto1_idx` (`tipo_proyecto` ASC),
  CONSTRAINT `fk_proyecto_macro_proyecto`
    FOREIGN KEY (`macro_proyecto`)
    REFERENCES `sgpi_db`.`macro_proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_semillero`
    FOREIGN KEY (`semillero`)
    REFERENCES `sgpi_db`.`semillero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_tipo_proyecto1`
    FOREIGN KEY (`tipo_proyecto`)
    REFERENCES `sgpi_db`.`tipo_proyecto` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`area_conocimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`area_conocimiento` (
  `id` INT(6) NOT NULL,
  `nombre` VARCHAR(100) NOT NULL,
  `gran_area` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`producto` (
  `id` INT(6) NOT NULL,
  `titulo_producto` VARCHAR(100) NOT NULL,
  `tipo_producto` VARCHAR(100) NOT NULL,
  `url_repo` VARCHAR(150) NOT NULL,
  `proyecto` INT(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_productos_proyecto_idx` (`proyecto` ASC),
  CONSTRAINT `fk_productos_proyecto`
    FOREIGN KEY (`proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`convocatoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`convocatoria` (
  `id` INT NOT NULL,
  `nombre_convocatoria` VARCHAR(45) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_final` DATE NOT NULL,
  `contexto` LONGTEXT NOT NULL,
  `numero_productos` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `entidad` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`materia` (
  `catalogo` VARCHAR(10) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `programa` INT NOT NULL,
  PRIMARY KEY (`catalogo`),
  INDEX `fk_materia_programa_idx` (`programa` ASC),
  CONSTRAINT `fk_materia_programa`
    FOREIGN KEY (`programa`)
    REFERENCES `sgpi_db`.`programa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`clase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`clase` (
  `numero` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `semestre` VARCHAR(45) NOT NULL,
  `materia` VARCHAR(10) NOT NULL,
  `profesor` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`numero`),
  INDEX `fk_clase_materia_idx` (`materia` ASC),
  INDEX `fk_clase_usuario_idx` (`profesor` ASC),
  CONSTRAINT `fk_clase_materia`
    FOREIGN KEY (`materia`)
    REFERENCES `sgpi_db`.`materia` (`catalogo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clase_usuario`
    FOREIGN KEY (`profesor`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`proyectos_convocatoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`proyectos_convocatoria` (
  `proyectos` INT(15) NOT NULL,
  `convocatoria` INT NOT NULL,
  `id_proyecto` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`proyectos`, `convocatoria`),
  INDEX `fk_proyectos_convocatoria_convocatoria_idx` (`convocatoria` ASC),
  INDEX `fk_proyectos_convocatoria_Proyecto_idx` (`proyectos` ASC),
  CONSTRAINT `fk_proyectos_convocatoria_proyecto`
    FOREIGN KEY (`proyectos`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_convocatoria_convocatoria`
    FOREIGN KEY (`convocatoria`)
    REFERENCES `sgpi_db`.`convocatoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`tipo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`tipo_usuario` (
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nombre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`usuarios` (
  `usuario` VARCHAR(20) NOT NULL,
  `tipo_usuario` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`usuario`, `tipo_usuario`),
  INDEX `fk_usuarios_tipo_usuario_idx` (`tipo_usuario` ASC),
  INDEX `fk_usuarios_usuario_idx` (`usuario` ASC),
  CONSTRAINT `fk_usuarios_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_tipo_usuario`
    FOREIGN KEY (`tipo_usuario`)
    REFERENCES `sgpi_db`.`tipo_usuario` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`proyectos_clase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`proyectos_clase` (
  `proyecto` INT(100) NOT NULL,
  `clase` INT NOT NULL,
  PRIMARY KEY (`clase`, `proyecto`),
  INDEX `fk_proyectos_clase_clase_idx` (`clase` ASC),
  INDEX `fk_proyectos_clase_proyecto_idx` (`proyecto` ASC),
  CONSTRAINT `fk_proyectos_clase_proyecto`
    FOREIGN KEY (`proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyectos_clase_clase`
    FOREIGN KEY (`clase`)
    REFERENCES `sgpi_db`.`clase` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`comentario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`comentario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comentario` LONGTEXT NOT NULL,
  `calificacion` DOUBLE NULL,
  `fase` VARCHAR(45) NOT NULL,
  `nivel` VARCHAR(45) NOT NULL,
  `fecha` VARCHAR(45) NOT NULL,
  `producto_id` INT(6) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comentario_producto_idx` (`producto_id` ASC),
  CONSTRAINT `fk_comentario_producto`
    FOREIGN KEY (`producto_id`)
    REFERENCES `sgpi_db`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`presupuesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`presupuesto` (
  `id` INT NOT NULL,
  `monto` DOUBLE NOT NULL,
  `fecha` DATE NOT NULL,
  `proyecto` INT(100) NOT NULL,
  `descripcion` LONGTEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_presupuesto_proyecto_idx` (`proyecto` ASC),
  CONSTRAINT `fk_presupuesto_proyecto`
    FOREIGN KEY (`proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`compra` (
  `id` INT NOT NULL,
  `fecha_solicitud` DATE NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `codigo_compra` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `fecha_compra` DATE NULL,
  `estado` INT NOT NULL,
  `link` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `presupuesto` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_compra_presupuesto_idx` (`presupuesto` ASC),
  CONSTRAINT `fk_compras_presupuesto`
    FOREIGN KEY (`presupuesto`)
    REFERENCES `sgpi_db`.`presupuesto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`detalle_convocatoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`detalle_convocatoria` (
  `id` INT NOT NULL,
  `objetivos_convocatoria` VARCHAR(45) NOT NULL,
  `requisitos` VARCHAR(45) NOT NULL,
  `modalidade` VARCHAR(45) NOT NULL,
  `convocatoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_detalle_convocatoria_convocatoria_idx` (`convocatoria_id` ASC),
  CONSTRAINT `fk_detalle_convocatoria_convocatoria`
    FOREIGN KEY (`convocatoria_id`)
    REFERENCES `sgpi_db`.`convocatoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`antecedentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`antecedentes` (
  `proyecto` INT(100) NOT NULL,
  `ancedente` INT(100) NOT NULL,
  PRIMARY KEY (`proyecto`, `ancedente`),
  INDEX `fk_antecedentes_proyecto_antecedente_idx` (`ancedente` ASC),
  INDEX `fk_antecedentes_proyecto_proyecto_idx` (`proyecto` ASC),
  CONSTRAINT `fk_antecedentes_proyecto_proyecto`
    FOREIGN KEY (`proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_antecedentes_proyecto_antecedente`
    FOREIGN KEY (`ancedente`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`evento` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `entidad` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NOT NULL,
  `sitio_web` VARCHAR(45) NULL,
  `url_memoria` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`programas_semilleros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`programas_semilleros` (
  `programa` INT NOT NULL,
  `semillero` INT NOT NULL,
  PRIMARY KEY (`programa`, `semillero`),
  INDEX `fk_programas_semillero_semillero_idx` (`semillero` ASC),
  INDEX `fk_programas_semillero_programa_idx` (`programa` ASC),
  CONSTRAINT `fk_programas_semillero_programa`
    FOREIGN KEY (`programa`)
    REFERENCES `sgpi_db`.`programa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_programas_semillero_semillero`
    FOREIGN KEY (`semillero`)
    REFERENCES `sgpi_db`.`semillero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`programas_grupos_inv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`programas_grupos_inv` (
  `programa` INT NOT NULL,
  `grupo_investigacion` INT NOT NULL,
  PRIMARY KEY (`programa`, `grupo_investigacion`),
  INDEX `fk_programas_grupos_inv_grupo_investigacion_idx` (`grupo_investigacion` ASC),
  INDEX `fk_programas_grupos_inv_programa_idx` (`programa` ASC),
  CONSTRAINT `fk_programas_grupos_inv_programa`
    FOREIGN KEY (`programa`)
    REFERENCES `sgpi_db`.`programa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_programas_grupos_inv_grupo_investigacion`
    FOREIGN KEY (`grupo_investigacion`)
    REFERENCES `sgpi_db`.`grupo_investigacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`grupo_inv_lineas_inv`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`grupo_inv_lineas_inv` (
  `grupo_investigacion` INT NOT NULL,
  `linea_investigacion` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`grupo_investigacion`, `linea_investigacion`),
  INDEX `fk_grupo_inv_lineas_inv_linea_invest_idx` (`linea_investigacion` ASC),
  INDEX `fk_grupo_inv_lineas_inv_grupo_invest_idx` (`grupo_investigacion` ASC),
  CONSTRAINT `fk_grupo_inv_lineas_inv_grupo_invest`
    FOREIGN KEY (`grupo_investigacion`)
    REFERENCES `sgpi_db`.`grupo_investigacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_inv_lineas_inv_linea_investig`
    FOREIGN KEY (`linea_investigacion`)
    REFERENCES `sgpi_db`.`linea_investigacion` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`participaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`participaciones` (
  `evento_id` INT NOT NULL,
  `proyecto_id_proyecto` INT(6) NOT NULL,
  `fecha_part` DATE NOT NULL,
  `reconocimientos` VARCHAR(10) NULL,
  PRIMARY KEY (`evento_id`, `proyecto_id_proyecto`),
  INDEX `fk_participaciones_proyecto_idx` (`proyecto_id_proyecto` ASC),
  INDEX `fk_participaciones_evento_idx` (`evento_id` ASC),
  CONSTRAINT `fk_participaciones_evento`
    FOREIGN KEY (`evento_id`)
    REFERENCES `sgpi_db`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participaciones_proyecto`
    FOREIGN KEY (`proyecto_id_proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`participantes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`participantes` (
  `usuario` VARCHAR(20) NOT NULL,
  `proyecto` INT(6) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NULL,
  `estado` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`usuario`, `proyecto`, `fecha_inicio`),
  INDEX `fk_participantes_proyecto_idx` (`proyecto` ASC),
  INDEX `fk_participantes_usuario_idx` (`usuario` ASC),
  CONSTRAINT `fk_participantes_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `sgpi_db`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participantes_proyecto`
    FOREIGN KEY (`proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sgpi_db`.`areas_conocimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sgpi_db`.`areas_conocimiento` (
  `proyecto` INT(6) NOT NULL,
  `area_conocimiento` INT(6) NOT NULL,
  PRIMARY KEY (`proyecto`, `area_conocimiento`),
  INDEX `fk_areas_conocimiento_area_conocimiento_idx` (`area_conocimiento` ASC),
  INDEX `fk_areas_conocimiento_proyecto_idx` (`proyecto` ASC),
  CONSTRAINT `fk_areas_conocimiento_proyecto`
    FOREIGN KEY (`proyecto`)
    REFERENCES `sgpi_db`.`proyecto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_areas_conocimiento_area_conocimiento`
    FOREIGN KEY (`area_conocimiento`)
    REFERENCES `sgpi_db`.`area_conocimiento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
