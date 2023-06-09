/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Draw {

    public void dibujarCirculo(Graphics g, int x, int y, String nombre) {
        Color lila = new Color(194, 140, 234);
        Color fondo = new Color(102, 34, 144);
        Color gris = new Color(221, 221, 213);

        ((Graphics2D) g).setColor(lila);
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        ((Graphics2D) g).fillOval(x, y, 15, 15);
        ((Graphics2D) g).setColor(fondo);
        ((Graphics2D) g).drawOval(x, y, 15, 15);

        ((Graphics2D) g).setColor(gris);
        Font fuente = new Font("Monospaced", Font.BOLD, 16);
        g.setFont(fuente);
        ((Graphics2D) g).drawString(nombre, x, y);
    }

    public void dibujarLinea(Graphics g, int x1, int y1, int x2, int y2, int tam) {
        int xAux = 0;
        int yAux = 0;
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2);
        ((Graphics2D) g).setStroke(stroke);
        ((Graphics2D) g).drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);
        if (x1 <= x2) {
            xAux = ((x2 - x1) / 2) + x1;
        }
        if (x1 > x2) {
            xAux = ((x1 - x2) / 2) + x2;
        }
        if (y1 < y2) {
            yAux = ((y2 - y1) / 2) + y1;
        }
        if (y1 >= y2) {
            yAux = ((y1 - y2) / 2) + y2;
        }
        Font fuente = new Font("Monospaced", Font.PLAIN, 12);
        g.setFont(fuente);
        ((Graphics2D) g).drawString(String.valueOf(tam), xAux, yAux);
    }

    public void seleccionarNodo(Graphics g, int x, int y, String n, Color co) {
        ((Graphics2D) g).setColor(co);
        ((Graphics2D) g).setStroke(new BasicStroke(4));//leda el grosor al circulo        
        ((Graphics2D) g).fillOval(x, y, 15, 15);
        ((Graphics2D) g).setColor(Color.BLACK);
        ((Graphics2D) g).drawOval(x, y, 15, 15);
    }

    public static void dibujarCamino(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2);
        ((Graphics2D) g).setStroke(stroke);
        g.setColor(color);
        g.drawLine(x1 + 10, y1 + 10, x2 + 10, y2 + 10);
    }
}
