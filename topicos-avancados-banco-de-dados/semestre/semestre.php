<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<?php

try {
  echo "<div class='container'>";
  $pdo = new PDO('mysql:host=mysql;dbname=semestre', 'root', 'rootpassword');
  $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

  $procedureName = 'mostrar_maior_menor_media';

  $stmt = $pdo->prepare("CALL $procedureName()");
  $stmt->execute();

  echo "<div class='d-flex justify-content-end align-items-center my-5'>
    <button class='btn btn-primary' onclick=\"window.location.href='inserir-notas.php'\">Inserir</button>
  </div>";

  echo "<table class='table table-hover caption-top mb-5'>";
  echo "<caption>Lista de notas maiores, menores e média</caption>";
  echo "<tr>
    <th>Aluno com maior média</th>
    <th>Maior média</th>
    <th>Aluno com menor média</th>
    <th>Menor média</th>
  </tr>";
  while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
    if ($row['aluno_maior_media'] === null) {
      echo "<tr>
        <td colspan=4 style='text-align: center;'>Não há notas cadastradas!</td>
      </tr>";
      break;
    }

    echo "<tr>";
    echo "<td>" . htmlspecialchars($row['aluno_maior_media']) . "</td>";
    echo "<td>" . htmlspecialchars($row['maior_media']) . "</td>";
    echo "<td>" . htmlspecialchars($row['aluno_menor_media']) . "</td>";
    echo "<td>" . htmlspecialchars($row['menor_media']) . "</td>";
    echo "</tr>";
  }
  echo "</table>";

  $stmt->closeCursor();

  $stmt = $pdo->prepare("SELECT id, nome_aluno, nota1, nota2 FROM notas");
  $stmt->execute();

  echo "<table class='table caption-top table-hover'>";
  echo "<caption>Lista de alunos</caption>";
  echo "<tr>
    <th>ID</th>
    <th>Nome do Aluno</th>
    <th>Nota 1</th>
    <th>Nota 2</th>
    <th>Ações</th>
  </tr>";

  if ($stmt->rowCount() === 0) {
    echo "<tr>
      <td colspan=5 style='text-align: center;'>Não há notas cadastradas!</td>
    </tr>";
  }
  while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
    echo "<tr>";
    echo "<td>" . htmlspecialchars($row['id']) . "</td>";
    echo "<td>" . htmlspecialchars($row['nome_aluno']) . "</td>";
    echo "<td>" . htmlspecialchars($row['nota1']) . "</td>";
    echo "<td>" . htmlspecialchars($row['nota2']) . "</td>";
    echo "<td>
      <form action='apagar-notas.php' method='POST' style='display:inline;'>
        <input type='hidden' name='id' value='" . htmlspecialchars($row['id']) . "' />
        <button class='btn btn-danger' type='submit'>Excluir</button>
      </form>
    </td>";
    echo "</tr>";
  }
  echo "</table>";

  $stmt->closeCursor();
  $stmt = null;
} catch (PDOException $e) {
  echo "Connection failed: " . $e->getMessage();
}

echo "</div>";
