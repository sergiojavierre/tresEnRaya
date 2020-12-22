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
            cliente.sendEstado(1,1);
        }
    }

    public void juega(){
        try {
            Scanner scanner = new Scanner(System.in);
            configuraConexion();
            while (true){
                if(cliente != null) {
                    cliente.sendMessage(scanner.nextLine());
                    String dataCliente = cliente.readData();
                    System.out.println(dataCliente);
                }
                else{
                    String dataServer = servidor.getMessage();
                    servidor.sendMessage(scanner.nextLine());
                    System.out.println(dataServer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            configuraConexion();
            Scanner scanner = new Scanner(System.in);
            while (!hasFinish()){
                if(servidor!=null){
                    String message = servidor.getMessage();
                    System.out.println(message);
                }
                System.out.print("Elige x: ");
                int x = Integer.parseInt(scanner.nextLine());
                System.out.print("Elige y: ");
                int y = Integer.parseInt(scanner.nextLine());
                tablero.setPosicion(y,x, Estado.J1);
                if(servidor!=null){
                    servidor.sendPosiciones(x,y);
                }
                else{
                    cliente.sendEstado(x,y);
                }
                tablero.showTablero();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
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
