/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.compilador.frontEnd;

/**
 *
 * @author Aluno
 */
public class Token {
     private String identificador;
     private String posicao;

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

   

    public Token() {
        this.identificador = "";
        this.posicao= "";
    }

    public Token(String identificador, String posicao) {
        this.identificador = identificador;
        this.posicao = posicao;
    }
     @Override
    public String toString(){
        return "<"+ this.identificador + ","+ this.posicao+">";
    }
        
     
}
