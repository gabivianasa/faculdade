<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  try {
    $pdo = new PDO('mysql:host=mysql;dbname=semestre', 'root', 'rootpassword');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $stmt = $pdo->prepare("DELETE FROM semestre.notas WHERE id = :id");
    $stmt->bindParam(':id', $_POST['id'], PDO::PARAM_INT);
    $stmt->execute();

    header('Location: ' . $_SERVER['HTTP_REFERER']);
    exit;
  } catch (PDOException $e) {
    echo "Erro ao apagar nota: " . $e->getMessage();
  }
}
