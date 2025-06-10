/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controle;

/**
 *
 * @author gabi
 */
public class Habitat {

    private int idHabitat;
    private String tipo;
    private String estado;
    private String clima;
    private String descricaoHabitat;

    public int getIdHabitat() {
        return idHabitat;
    }

    public void setIdHabitat(int idHabitat) {
        this.idHabitat = idHabitat;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getDescricaoHabitat() {
        return descricaoHabitat;
    }

    public void setDescricaoHabitat(String descricaoHabitat) {
        this.descricaoHabitat = descricaoHabitat;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}
