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
public class RelatorioHistorico {

    private String nome;
    private Sexo sexo;
    private int idade;
    private String habitat;
    private int quantidadeFemeasBrasil;
    private int quantidadeMachosBrasil;
    private Timestamp dataUltimaObservacao;
    private String inViva;

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

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
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

    public Timestamp getDataUltimaObservacao() {
        return dataUltimaObservacao;
    }

    public void setDataUltimaObservacao(Timestamp dataUltimaObservacao) {
        this.dataUltimaObservacao = dataUltimaObservacao;
    }

    public String getInViva() {
        return inViva;
    }

    public void setInViva(String inViva) {
        this.inViva = inViva;
    }

}
