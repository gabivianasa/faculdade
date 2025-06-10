/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controle;

/**
 *
 * @author gabi
 */
public enum Sexo {
    MASCULINO("M"),
    FEMININO("F");

    private final String valor;

    Sexo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
    public static Sexo setarValor(String valor) {
        if (valor == null) {
            return null;
        }
        
        switch(valor.toUpperCase()) {
            case "M" -> {
                return MASCULINO;
            }
            case "F" -> {
                return FEMININO;
            }
            default -> throw new IllegalArgumentException("Tipo de sexo desconhecido");
        }
    }
}
