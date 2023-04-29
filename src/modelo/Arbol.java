/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


public class Arbol {

    private int coorX[] = new int[21];
    private int coorY[] = new int[21];
    private int nombre[] = new int[21];
    private int Coeficiente[][] = new int[21][21];
    private int Adyacencia[][] = new int[21][21];
    private int enArbol[];

    public int getCoeficiente(int i, int j) {
        return Coeficiente[i][j];
    }

    public int getAdyacencia(int i, int j) {
        return Adyacencia[i][j];
    }

    public int getEnArbol(int i) {
        return enArbol[i];
    }

    public void setCoeficiente(int i, int j, int mCoeficiente) {
        this.Coeficiente[i][j] = mCoeficiente;
    }

    public void setAdyacencia(int i, int j, int mAdyacencia) {
        this.Adyacencia[i][j] = mAdyacencia;
    }

    public int getCordeX(int i) {
        return coorX[i];
    }

    public int getCordeY(int i) {
        return coorY[i];
    }

    public int getNombre(int i) {
        return nombre[i];
    }

    public void setCordeX(int i, int cordeX) {
        this.coorX[i] = cordeX;
    }

    public void setCordeY(int i, int cordeY) {
        this.coorY[i] = cordeY;
    }

    public void setNombre(int i, int nombre) {
        this.nombre[i] = nombre;
    }

    public void setEnArbol(int i, int enArbol) {
        this.enArbol[i] = enArbol;
    }

    public void crearEnArbol(int i) {
        enArbol = new int[i];
    }
}
