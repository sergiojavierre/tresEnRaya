package com.sergiojavierre.entidades;

public class Tablero {

    Estado[][] posiciones = null;

    private final int size = 3;

    public Tablero(){
        this.posiciones = new Estado[size][size];
        initTablero();
    }

    private void initTablero(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                this.posiciones[i][j] = Estado.VA;
            }
        }
    }

    public Estado getPosicion(int x, int y){
        return this.posiciones[x][y];
    }

    public int getSize(){
        return size;
    }

    public void setPosicion(int x, int y, Estado estado){
        this.posiciones[x][y] = estado;
    }

    public void showTablero(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(this.posiciones[i][j]+"\t\t");
            }
            System.out.println("");
        }
    }

}
