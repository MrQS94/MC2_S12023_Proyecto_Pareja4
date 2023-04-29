/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.lang.*;
import controlador.ControladorGrafos;
import vista.Grafos;


public class Main {
    
    public static void main(String[] args) {
        Grafos grafos = new Grafos();
        ControladorGrafos control = new ControladorGrafos(grafos);
        grafos.setVisible(true);
    }
   

}
