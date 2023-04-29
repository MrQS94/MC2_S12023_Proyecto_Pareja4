/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.AlgoritmoPrim;
import modelo.Arbol;
import modelo.Draw;
import vista.Grafos;

public class ControladorGrafos implements MouseListener, ActionListener {

    private Grafos grafo;
    private Arbol arbol = new Arbol();
    private int limite = 0;
    private int n = 0, nn = 0, id, id2;
    private int aristaMayor;

    public ControladorGrafos(Grafos grafo) {
        this.grafo = grafo;
        this.grafo.jButtonPrim.addActionListener(this);
        this.grafo.jPanel.addMouseListener(this);
        this.grafo.jButtonLimpiar.addActionListener(this);
    }

    public void R_repaint(int tope, Arbol arboles) {//pinta lo q esta antes en el panel 
        Draw draw = new Draw();
        for (int j = 0; j < tope; j++) {
            for (int k = 0; k < tope; k++) {
                if (arboles.getAdyacencia(j, k) == 1) {
                    draw.dibujarLinea(grafo.jPanel.getGraphics(), arboles.getCordeX(j), arboles.getCordeY(j), arboles.getCordeX(k), arboles.getCordeY(k), arboles.getCoeficiente(j, k));
                }
            }
        }
        for (int j = 0; j < tope; j++) {
            draw.dibujarCirculo(grafo.jPanel.getGraphics(), arboles.getCordeX(j), arboles.getCordeY(j), String.valueOf(arboles.getNombre(j)));
        }
    }

    public int ingresarNodoOrigen(String nodoOrige, String noExiste, int tope) {
        int nodoOrigen = 0;
        try {
            nodoOrigen = Integer.parseInt(JOptionPane.showInputDialog(nodoOrige)); // Cambiar con JTextField
            if (nodoOrigen >= tope) {
                JOptionPane.showMessageDialog(null, "" + noExiste + "\nDebe de ingresar  un Nodo existente");
                nodoOrigen = ingresarNodoOrigen(nodoOrige, noExiste, tope);
            }
        } catch (Exception ex) {
            nodoOrigen = ingresarNodoOrigen(nodoOrige, noExiste, tope);
        }
        return nodoOrigen;
    }

    private int ingresarTamano(String tama) {
        int tamano = 0;
        try {
            tamano = Integer.parseInt(JOptionPane.showInputDialog("" + tama));
            if (tamano < 1) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar un Tamaño Aceptado..");
                tamano = ingresarTamano(tama);//no es nesario hacer esto
            }
        } catch (Exception ex) {
            tamano = ingresarTamano(tama);
        }
        return tamano;
    }

    private boolean clicDerechoSobreNodo(int xxx, int yyy) {
        Draw draw = new Draw();
        for (int j = 0; j < limite; j++) {
            if ((xxx + 2) > arbol.getCordeX(j) && xxx < (arbol.getCordeX(j) + 13) && (yyy + 2) > arbol.getCordeY(j) && yyy < (arbol.getCordeY(j) + 13)) {

                if (n == 0) {
                    id = j;
                    R_repaint(limite, arbol);
                    draw.seleccionarNodo(grafo.jPanel.getGraphics(), arbol.getCordeX(j), arbol.getCordeY(j), null, Color.orange);
                    n++;
                } else {
                    id2 = j;
                    n++;
                    draw.seleccionarNodo(grafo.jPanel.getGraphics(), arbol.getCordeX(j), arbol.getCordeY(j), null, Color.orange);
                    if (id == id2) {
                        n = 0;
                        draw.dibujarCirculo(grafo.jPanel.getGraphics(), arbol.getCordeX(id), arbol.getCordeY(id), String.valueOf(arbol.getNombre(id)));

                        id = -1;
                        id2 = -1;
                    }
                }
                nn = 0;
                return true;
            }
        }
        return false;
    }

    private void clicIzqSobreNodo(int xxx, int yyy) {
        Draw draw = new Draw();
        for (int j = 0; j < limite; j++) {
            if ((xxx + 2) > arbol.getCordeX(j) && xxx < (arbol.getCordeX(j) + 13) && (yyy + 2) > arbol.getCordeY(j) && yyy < (arbol.getCordeY(j) + 13)) {
                if (nn == 0) {
                    R_repaint(limite, arbol);
                }
                nn++;
                n = 0;
                id = -1;
                draw.seleccionarNodo(grafo.jPanel.getGraphics(), arbol.getCordeX(j), arbol.getCordeY(j), null, Color.GREEN);
                break;
            }
        }
    }

    private void Prin() {
        if (limite < 1) {
            JOptionPane.showMessageDialog(null, "Aun no se ha creado Un nodo");
        } else {
            ControladorGrafos control = new ControladorGrafos(grafo);
            AlgoritmoPrim Prim = new AlgoritmoPrim(arbol, limite, aristaMayor, grafo, control);
            Prim.prim();
            grafo.jTextFieldTotal.setText(String.valueOf(Prim.getCumulado()));
        }
    }

    private void Limpiar() {
        grafo.jButtonPrim.setEnabled(true);
        grafo.jTextFieldTotal.setText("");
        grafo.jPanel.repaint();
        limite = 0;
        JOptionPane.showMessageDialog(null, "Puede volver a dibujar el grafo que necesite.", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xxx, yyy;
        xxx = e.getX();
        yyy = e.getY();
        Draw draw = new Draw();
        if (e.isMetaDown()) {
            clicIzqSobreNodo(xxx, yyy);
            if (nn == 2) {
                nn = 0;
            }
        } else {
            if (!clicDerechoSobreNodo(xxx, yyy)) {
                if (limite < 20) {
                    arbol.setCordeX(limite, xxx);
                    arbol.setCordeY(limite, yyy);
                    arbol.setNombre(limite, limite);
                    draw.dibujarCirculo(grafo.jPanel.getGraphics(), arbol.getCordeX(limite), arbol.getCordeY(limite), String.valueOf(arbol.getNombre(limite)));
                    limite++;
                } else {
                    JOptionPane.showMessageDialog(null, "Se ha llegado al Maximo de nodos..");
                }
            }
            if (n == 2) {
                n = 0;
                int tamano = ingresarTamano("Ingrese Tamaño");
                if (aristaMayor < tamano) {
                    aristaMayor = tamano;
                }
                arbol.setAdyacencia(id2, id, 1);
                arbol.setAdyacencia(id, id2, 1);
                arbol.setCoeficiente(id2, id, tamano);
                arbol.setCoeficiente(id, id2, tamano);
                draw.dibujarLinea(grafo.jPanel.getGraphics(), arbol.getCordeX(id), arbol.getCordeY(id), arbol.getCordeX(id2), arbol.getCordeY(id2), tamano);
                draw.dibujarCirculo(grafo.jPanel.getGraphics(), arbol.getCordeX(id), arbol.getCordeY(id), String.valueOf(arbol.getNombre(id)));
                draw.dibujarCirculo(grafo.jPanel.getGraphics(), arbol.getCordeX(id2), arbol.getCordeY(id2), String.valueOf(arbol.getNombre(id2)));
                id = -1;
                id2 = -1;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == grafo.jButtonPrim) {
            Prin();
            grafo.jButtonPrim.setEnabled(false);
        } else if (e.getSource() == grafo.jButtonLimpiar) {
            Limpiar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
