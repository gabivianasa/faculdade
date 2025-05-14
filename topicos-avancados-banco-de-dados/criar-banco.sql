CREATE DATABASE IF NOT EXISTS semestre;

CREATE TABLE IF NOT EXISTS semestre.notas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_aluno VARCHAR(100) NOT NULL,
    nota1 DECIMAL(10,2) NOT NULL,
    nota2 DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS semestre.semestre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_aluno VARCHAR(100) NOT NULL,
    nota1 DECIMAL(10,2) NOT NULL,
    nota2 DECIMAL(10,2) NOT NULL,
    media DECIMAL(10,2) NOT NULL
);

DELIMITER //
CREATE PROCEDURE semestre.mostrar_maior_menor_media()
BEGIN
  DECLARE nome_maior VARCHAR(100);
  DECLARE nome_menor VARCHAR(100);
  DECLARE media_maior DECIMAL(10,2);
  DECLARE media_menor DECIMAL(10,2);

  SELECT nome_aluno, media 
  INTO nome_maior, media_maior
  FROM semestre
  ORDER BY media DESC
  LIMIT 1;

  SELECT nome_aluno, media 
  INTO nome_menor, media_menor
  FROM semestre
  ORDER BY media ASC
  LIMIT 1;

  SELECT 
    nome_maior AS "aluno_maior_media", 
    media_maior AS "maior_media", 
    nome_menor AS "aluno_menor_media", 
    media_menor AS "menor_media";
END //
DELIMITER ;

CREATE TRIGGER semestre.calcular_media_after_insert
AFTER INSERT ON notas
FOR EACH ROW
INSERT INTO semestre (nome_aluno, nota1, nota2, media)
VALUES (NEW.nome_aluno, NEW.nota1, NEW.nota2, (NEW.nota1 + NEW.nota2) / 2);

CREATE TRIGGER semestre.calcular_media_after_delete
AFTER DELETE ON notas
FOR EACH ROW
DELETE FROM semestre
WHERE nome_aluno = OLD.nome_aluno AND
nota1 = OLD.nota1 AND
nota2 = OLD.nota2;
