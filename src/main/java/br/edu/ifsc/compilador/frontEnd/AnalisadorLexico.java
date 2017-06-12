/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.compilador.frontEnd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class AnalisadorLexico {

    private final char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
        'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

    private final char[] simbolos = {'+', '*', '/', '=', '{', '}', '[', ']', '(', ')', ';', '.'};

    private final List<Token> listaToken;

    public AnalisadorLexico() {
        this.listaToken = new ArrayList<>();
    }

    public List<Token> getListaToken() {
        return this.listaToken;
    }

    public void addToken(Token token) {
        this.listaToken.add(token);
    }

    public void removeToken(Token token) {
        this.listaToken.remove(token);
    }

    public void updateToken(Token token, int index) {
        this.listaToken.add(index, token);
    }

    public Token getToken(int index) {
        return this.listaToken.get(index);
    }

    public boolean verificaAlfabeto(char caractere) {

        for (int i = 0; i < alfabeto.length; i++) {
            if (alfabeto[i] == caractere) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaNumero(char caractere) {
        return Character.isDigit(caractere);
    }
    
    public boolean verificarSimbolo(char caractere){
        for (int i = 0; i < simbolos.length; i++) {
            if (simbolos[i] == caractere) {
                return true;
            }
        }
        return false;
    }

    public void entradaToTokens(String entrada) {
        int estado = 0;
        String identificador = "";
        String numero = "";
        String simbolo = "";
        int cont = 0;
        for (int i = 0; i < entrada.length(); i++) {
            if (verificaAlfabeto(entrada.charAt(i)) && estado == 0) {
                identificador = "";
                estado = 1;
            }
            if (estado == 1) {
                if (verificaAlfabeto(entrada.charAt(i)) || verificaNumero(entrada.charAt(i))) {
                    identificador += entrada.charAt(i);
                } else {
                    estado = 2;
                }
                if (i == entrada.length() - 1) {
                    estado = 2;
                } else if (!verificaAlfabeto(entrada.charAt(i + 1)) && !verificaNumero(entrada.charAt(i + 1))) {
                    estado = 2;
                }
            }

            if (estado == 0 && entrada.charAt(i) == '-') {
                estado = 4;
                if (i < entrada.length() - 1) {
                    if (estado == 4 && !verificaNumero(entrada.charAt(i + 1))) {
                        numero = "-";
                        estado = 5;
                    } else {
                        numero = "-";
                        estado = 6;
                    }
                } else {
                    numero = "-";
                    estado = 5;
                }
            }
            if (estado == 0 && verificaNumero(entrada.charAt(i))) {
                numero = "";
                estado = 6;
            }
            
            if(estado == 8){
                if(verificaNumero(entrada.charAt(i))){
                    numero += entrada.charAt(i);
                }
                if (i == entrada.length() - 1) {
                    estado = 7;
                } else if (!verificaNumero(entrada.charAt(i + 1))) {
                    estado = 7;
                }
                
            }
            
            if(estado == 0 && verificarSimbolo(entrada.charAt(i))){
                simbolo = "";
                simbolo += entrada.charAt(i);
                estado = 9;
            }

            if (estado == 6) {
                if (verificaNumero(entrada.charAt(i))) {
                    numero += entrada.charAt(i);
                } 
                if (i == entrada.length() - 1) {
                    estado = 7;
                } else if (!verificaNumero(entrada.charAt(i + 1)) && entrada.charAt(i + 1) != '.') {
                    estado = 7;
                } else if(!verificaNumero(entrada.charAt(i + 1)) && entrada.charAt(i + 1) == '.'){
                    if(i < entrada.length() - 2  && verificaNumero(entrada.charAt(i + 2))){
                        numero += ".";
                        estado = 8;
                    }else{
                        estado = 7;
                    }
                }
            }

            switch (estado) {
                case 2:
                    this.addToken(new Token(identificador, Integer.toString(cont++)));
                    estado = 0;
                    break;
                case 5:
                    
                    this.addToken(new Token(numero, ""));
                    estado = 0;
                    break;
                case 7:
                    this.addToken(new Token(numero, ""));
                    estado = 0;
                    break;
                case 9:
                    this.addToken(new Token(simbolo, ""));
                    estado = 0;
                    break;
                default:
                    break;
            }
        }

    }
    
    public String getStringsToken(){
        String texto = "";
        for(Token token : this.listaToken){
            texto += token.toString() + "\n";
        }
        return texto;
    }

}
