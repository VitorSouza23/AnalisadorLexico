/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.compilador.frontEnd;

import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class Main {
    public static void main(String[] args) {
        AnalisadorLexico analisa = new AnalisadorLexico();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite alguma coisa....");
        analisa.entradaToTokens(entrada.nextLine());
        analisa.getListaToken().forEach((token) -> System.out.println(token.toString()));
    }
}
