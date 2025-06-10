/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controle;

import java.sql.Timestamp;
/**
 *
 * @author gabi
 */
public class Onca {

    private int idOnca;
    private String nome;
    private String sexo;
    private int idade;
    private double peso;
    private String comportamento;
    private String descricaoFisica;
    private Timestamp dataUltimaObservacao;
    private byte inViva;
    private int idHabitat;

    public int getIdOnca() {
        return idOnca;
    }

    public void setIdOnca(int idOnca) {
        this.idOnca = idOnca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getComportamento() {
        return comportamento;
    }

    public void setComportamento(String comportamento) {
        this.comportamento = comportamento;
    }

    public String getDescricaoFisica() {
        return descricaoFisica;
    }

    public void setDescricaoFisica(String descricaoFisica) {
        this.descricaoFisica = descricaoFisica;
    }

    public Timestamp getDataUltimaObservacao() {
        return dataUltimaObservacao;
    }

    public void setDataUltimaObservacao(Timestamp dataUltimaObservacao) {
        this.dataUltimaObservacao = dataUltimaObservacao;
    }

    public byte getInViva() {
        return inViva;
    }

    public void setInViva(byte inViva) {
        this.inViva = inViva;
    }

    public int getIdHabitat() {
        return idHabitat;
    }

    public void setIdHabitat(int idHabitat) {
        this.idHabitat = idHabitat;
    }
    
    
    
          
}
