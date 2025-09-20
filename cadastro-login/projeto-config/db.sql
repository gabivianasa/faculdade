CREATE SCHEMA IF NOT EXISTS `cadastroLogin` DEFAULT CHARACTER SET utf8;

USE `cadastroLogin`;

CREATE TABLE IF NOT EXISTS `cadastroLogin`.`usuario` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`nome` VARCHAR(80) NOT NULL,
`email` VARCHAR(50) NOT NULL,
`senha` VARCHAR(8)
) ENGINE=InnoDB;

SELECT * from usuario;

-- TRUNCATE table usuario;
-- ALTER TABLE usuario AUTO_INCREMENT = 1;


