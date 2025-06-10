/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controle;

/**
 *
 * @author gabi
 */
public class Monitoramento {

    private int idMonitoramento;
    private int idOnca;
    private int idHabitat;
    private String nome;
    private Sexo sexo;
    private int idade;
    private int quantidadeFemeasBrasil;
    private int quantidadeMachosBrasil;

    public int getIdMonitoramento() {
        return idMonitoramento;
    }

    public void setIdMonitoramento(int idMonitoramento) {
        this.idMonitoramento = idMonitoramento;
    }

    public int getIdOnca() {
        return idOnca;
    }

    public void setIdOnca(int idOnca) {
        this.idOnca = idOnca;
    }

    public int getIdHabitat() {
        return idHabitat;
    }

    public void setIdHabitat(int idHabitat) {
        this.idHabitat = idHabitat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getQuantidadeFemeasBrasil() {
        return quantidadeFemeasBrasil;
    }

    public void setQuantidadeFemeasBrasil(int quantidadeFemeasBrasil) {
        this.quantidadeFemeasBrasil = quantidadeFemeasBrasil;
    }

    public int getQuantidadeMachosBrasil() {
        return quantidadeMachosBrasil;
    }

    public void setQuantidadeMachosBrasil(int quantidadeMachosBrasil) {
        this.quantidadeMachosBrasil = quantidadeMachosBrasil;
    }

}
