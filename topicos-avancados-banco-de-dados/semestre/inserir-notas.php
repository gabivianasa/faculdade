<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  try {
    $pdo = new PDO('mysql:host=mysql;dbname=semestre', 'root', 'rootpassword');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $stmt = $pdo->prepare("INSERT INTO semestre.notas(nome_aluno, nota1, nota2) VALUES (:nome_aluno, :nota1, :nota2)");
    $stmt->bindParam(':nome_aluno', $_POST['nome_aluno']);
    $stmt->bindParam(':nota1', $_POST['nota1']);
    $stmt->bindParam(':nota2', $_POST['nota2']);
    $stmt->execute();

    header('Location: semestre.php');
    exit;
  } catch (PDOException $e) {
    echo "Erro ao inserir nota: " . $e->getMessage();
  }
}
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Inserir Notas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
  <div class="container mt-5" style="width: 550px;">
    <h1>Inserir Notas</h1>
    <form method="POST" action="">
      <div class="input-group mb-3">
        <label class="input-group-text" for="nome_aluno">Nome do Aluno:</label>
        <input class="form-control" type="text" id="nome_aluno" name="nome_aluno" required>
      </div>

      <div class="input-group mb-3">
        <label class="input-group-text" for="nota1">Nota 1:</label>
        <input class="form-control" type="text" id="nota1" name="nota1" step="0.01" required><br><br>
      </div>

      <div class="input-group mb-3">
        <label class="input-group-text" for="nota2">Nota 2:</label>
        <input class="form-control" type="text" id="nota2" name="nota2" step="0.01" required><br><br>
      </div>

      <div class="d-flex justify-content-end align-items-center">
        <button class='btn btn-secondary me-2' onclick="window.location.href='semestre.php'">Voltar</button>
        <button class='btn btn-primary' type="submit">Inserir Nota</button>
      </div>
    </form>
  </div>
</body>

</html>