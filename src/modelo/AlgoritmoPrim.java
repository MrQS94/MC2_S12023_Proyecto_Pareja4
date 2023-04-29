/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.ControladorGrafos;
import java.awt.Color;
import vista.Grafos;


public class AlgoritmoPrim {

    private int cumulado;
    private int aristaMenor;
    private int fin;
    private boolean estaNodo = false;
    private boolean aumentaTamano;
    private int nodoApuntado;
    private int nodoApuntador;
    private int tamano;
    private int arsitaMayor;
    private Arbol arboles;
    private int limite;
    private int nodoOrigen;

    private Grafos grafo;
    private ControladorGrafos control;

    public AlgoritmoPrim(Arbol arbol, int limite, int aristaMayor, Grafos grafo, ControladorGrafos control) {
        this.cumulado = 0;
        this.aristaMenor = 0;
        this.fin = 0;
        this.estaNodo = false;
        this.aumentaTamano = false;
        this.nodoApuntado = 0;
        this.nodoApuntador = 0;
        this.tamano = 1;
        this.arsitaMayor = aristaMayor;
        this.arboles = arbol;
        this.limite = limite;
        this.grafo = grafo;
        this.control = control;
    }

    public int getCumulado() {
        return cumulado;
    }

    public void prim() {
        Draw draw = new Draw();
        this.nodoOrigen = control.ingresarNodoOrigen("Ingrese Nodo Origen..", "nodo Origen No existe", limite);
        grafo.jPanel.paint(grafo.jPanel.getGraphics());
        control.R_repaint(limite, arboles);
        arboles.crearEnArbol(limite);
        arboles.setEnArbol(0, nodoOrigen);
        //algoritmo de Prim ---->>
        do {
            this.aristaMenor = this.arsitaMayor;
            this.fin = 2;
            for (int j = 0; j < tamano; j++) {
                for (int k = 0; k < limite; k++) {
                    if (arboles.getAdyacencia(k, arboles.getEnArbol(j)) == 1) {
                        for (int h = 0; h < tamano; h++) {
                            if (arboles.getEnArbol(h) == k) {
                                this.estaNodo = true;
                                break;
                            }
                        }
                        if (estaNodo == false) {
                            if (arboles.getCoeficiente(k, arboles.getEnArbol(j)) <= aristaMenor && arboles.getCoeficiente(k, arboles.getEnArbol(j)) > 0) {
                                aristaMenor = arboles.getCoeficiente(k, arboles.getEnArbol(j));
                                this.nodoApuntado = k;
                                this.aumentaTamano = true;
                                this.nodoApuntador = arboles.getEnArbol(j);
                                this.fin = 1;
                            }
                        }
                        this.estaNodo = false;
                    }
                }
            }//fin  for (int j = 0; j < tamano; j++)              
            if (aumentaTamano == true) {
                draw.dibujarCamino(grafo.jPanel.getGraphics(), arboles.getCordeX(nodoApuntador), arboles.getCordeY(nodoApuntador), arboles.getCordeX(nodoApuntado), arboles.getCordeY(nodoApuntado), Color.red);
                draw.seleccionarNodo(grafo.jPanel.getGraphics(), arboles.getCordeX(nodoApuntador), arboles.getCordeY(nodoApuntador), null, Color.red);
                draw.seleccionarNodo(grafo.jPanel.getGraphics(), arboles.getCordeX(nodoApuntado), arboles.getCordeY(nodoApuntado), null, Color.red);
                arboles.setEnArbol(tamano, nodoApuntado);
                this.tamano++;
                this.aumentaTamano = false;
                this.cumulado += this.aristaMenor;
            }
        } while (fin < 2);
    }

}
