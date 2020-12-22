package com.sergiojavierre.aplicacion;

import com.sergiojavierre.entidades.Estado;
import com.sergiojavierre.entidades.Tablero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Partida {

    private Tablero tablero;

    private Servidor servidor;
    private Cliente cliente;


    public Partida(){
        tablero = new Tablero();
    }

    private void configuraConexion() throws IOException {
        System.out.print("1) Servidor\n2)Cliente\nEleccion:");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().equals("1")){
            servidor = new Servidor();
            servidor.startServer();
        }
        else{
            cliente = new Cliente();
            cliente.startClient();
        }
    }

    public void juega(){
        try {
            configuraConexion();
            Scanner scanner = new Scanner(System.in);
            while (!hasFinish()){
                System.out.print("Elige x: ");
                int x = Integer.parseInt(scanner.nextLine());
                System.out.print("Elige y: ");
                int y = Integer.parseInt(scanner.nextLine());
                tablero.setPosicion(y,x, Estado.J1);
                tablero.showTablero();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Boolean hasFinish(){
        //faltan diagonales
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
