package com.sergiojavierre.aplicacion;

import com.sergiojavierre.entidades.Estado;
import com.sergiojavierre.entidades.Tablero;

import java.util.Scanner;

public class Partida {

    private Tablero tablero;

    public Partida(){
        tablero = new Tablero();
    }

    public void juega(){
        Scanner scanner = new Scanner(System.in);
        while (!hasFinish()){
            System.out.print("Elige x: ");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.print("Elige y: ");
            int y = Integer.parseInt(scanner.nextLine());
            tablero.setPosicion(y,x, Estado.J1);
            tablero.showTablero();
        }
    }

    public Boolean hasFinish(){
        for(int i = 0; i < tablero.getSize(); i++){
            Estado estado = tablero.getPosicion(i,0);
            if(estado != Estado.VA) {
                Boolean correct = true;
                for (int j = 1; j < tablero.getSize(); j++) {
                    Estado current = tablero.getPosicion(i, j);
                    if (estado != current) {
                        correct = false;
                        break;
                    }
                }
                if (correct) return true;
            }
        }
        for(int i = 0; i < tablero.getSize(); i++){
            Estado estado = tablero.getPosicion(0,i);
            if(estado != Estado.VA) {
                Boolean correct = true;
                for (int j = 1; j < tablero.getSize(); j++) {
                    Estado current = tablero.getPosicion(j, i);
                    if (estado != current) {
                        correct = false;
                        break;
                    }
                }
                if (correct) return true;
            }
        }
        return false;
    }

}
