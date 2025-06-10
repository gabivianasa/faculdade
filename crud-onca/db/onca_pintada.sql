-- Host: localhost
-- Tempo de geração: 06/06/2025 às 05:41
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `onca_pintada`
--
CREATE DATABASE IF NOT EXISTS `onca_pintada` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `onca_pintada`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `habitat`
--

DROP TABLE IF EXISTS `habitat`;
CREATE TABLE IF NOT EXISTS `habitat` (
  `id_habitat` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `estado` varchar(80) NOT NULL,
  `clima` varchar(80) NOT NULL,
  `descricao_habitat` longtext DEFAULT NULL,
  PRIMARY KEY (`id_habitat`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `monitoramento`
--

DROP TABLE IF EXISTS `monitoramento`;
CREATE TABLE IF NOT EXISTS `monitoramento` (
  `id_monitoramento` int(11) NOT NULL AUTO_INCREMENT,
  `id_onca` int(11) DEFAULT NULL,
  `id_habitat` int(11) DEFAULT NULL,
  `nome` varchar(80) NOT NULL,
  `sexo` enum('M','F') NOT NULL,
  `idade` int(11) NOT NULL,
  `quantidade_femeas_brasil` int(11) DEFAULT 0,
  `quantidade_machos_brasil` int(11) DEFAULT 0,
  PRIMARY KEY (`id_monitoramento`),
  KEY `id_onca` (`id_onca`),
  KEY `id_habitat` (`id_habitat`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- --------------------------------------------------------

--
-- Estrutura para tabela `onca`
--

DROP TABLE IF EXISTS `onca`;
CREATE TABLE IF NOT EXISTS `onca` (
  `id_onca` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sexo` varchar(10) NOT NULL CHECK (`sexo` in ('M','F')),
  `idade` int(11) NOT NULL,
  `peso` decimal(5,2) NOT NULL CHECK (`peso` >= 0),
  `comportamento` longtext NOT NULL,
  `descricao_fisica` text NOT NULL,
  `data_ultima_observacao` timestamp NOT NULL DEFAULT current_timestamp(),
  `in_viva` tinyint(1) DEFAULT 1,
  `id_habitat` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_onca`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Pesquisador
--

DROP TABLE IF EXISTS `pesquisador`;
CREATE TABLE IF NOT EXISTS `pesquisador`(
id_pesquisador int primary key auto_increment,
nome varchar(80) not null,
email varchar(80) not null,
cpf varchar(15),
endereco varchar(80)
);

--
-- Acionadores `onca`
--
DROP TRIGGER IF EXISTS `trg_after_insert_onca`;
DELIMITER $$
CREATE TRIGGER `trg_after_insert_onca` AFTER INSERT ON `onca` FOR EACH ROW BEGIN
    DECLARE total_machos INT;
    DECLARE total_femeas INT;
    DECLARE nome_onca VARCHAR(80);
    DECLARE sexo_onca CHAR(1);
    DECLARE idade_onca INT;
    DECLARE id_habitat INT;

    -- Obter os dados da onça inserida
    SELECT nome, sexo, idade, id_habitat INTO nome_onca, sexo_onca, idade_onca, id_habitat 
    FROM onca WHERE id_onca = NEW.id_onca;

    -- Contagem das onças macho e fêmea vivas
    SELECT COUNT(*) INTO total_machos FROM onca WHERE sexo = 'M' AND in_viva = TRUE;
    SELECT COUNT(*) INTO total_femeas FROM onca WHERE sexo = 'F' AND in_viva = TRUE;

    INSERT INTO monitoramento (
        id_onca,
        id_habitat,
        nome,
        sexo,
        idade,
        quantidade_femeas_brasil,
        quantidade_machos_brasil
    )
    VALUES (
        NEW.id_onca,
        NEW.id_habitat,
        nome_onca,   
        sexo_onca,
        idade_onca,
        total_femeas,
        total_machos
    );
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `trg_after_update_onca`;
DELIMITER $$
CREATE TRIGGER `trg_after_update_onca` AFTER UPDATE ON onca
FOR EACH ROW
BEGIN
    DECLARE total_machos INT DEFAULT 0;
    DECLARE total_femeas INT DEFAULT 0;

    -- Executa apenas se a onça foi marcada como morta
--     IF OLD.in_viva = 1 AND NEW.in_viva = 0 THEN
       IF OLD.in_viva <> NEW.in_viva THEN

        -- Conta todos os machos vivos
        SELECT COUNT(*) INTO total_machos
        FROM onca
        WHERE sexo = 'M' AND in_viva = 1;

        -- Conta todas as fêmeas vivas
        SELECT COUNT(*) INTO total_femeas
        FROM onca
        WHERE sexo = 'F' AND in_viva = 1;

        -- Atualiza a tabela monitoramento com as novas contagens
        UPDATE monitoramento
        SET
            quantidade_machos_brasil = total_machos,
            quantidade_femeas_brasil = total_femeas;
    END IF;
END
$$
DELIMITER ;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `monitoramento`
--
ALTER TABLE `monitoramento`
  ADD CONSTRAINT `monitoramento_ibfk_1` FOREIGN KEY (`id_onca`) REFERENCES `onca` (`id_onca`),
  ADD CONSTRAINT `monitoramento_ibfk_2` FOREIGN KEY (`id_habitat`) REFERENCES `habitat` (`id_habitat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
