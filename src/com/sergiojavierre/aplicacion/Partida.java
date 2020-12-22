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
            //servidor.startServer();
        }
        else{
            cliente = new Cliente();
           // cliente.sendEstado(1,1);
        }
    }

    private void chat(){
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
                    String dataServer = servidor.readData();
                    System.out.println(dataServer);
                    servidor.sendMessage(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setXY(int x, int y){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elige x: ");
        x = Integer.parseInt(scanner.nextLine());
        System.out.print("Elige y: ");
        y = Integer.parseInt(scanner.nextLine());
        tablero.setPosicion(y,x, Estado.J1);
    }

    public void juega(){
        try {
            configuraConexion();
            while (!hasFinish()){
                int x = 0, y = 0;
                if(servidor!=null){
                    String message = servidor.readData();
                    System.out.println(message);
                    setXY(x,y);
                    servidor.sendPosiciones(x,y);
                }
                else{
                    setXY(x,y);
                    cliente.sendPosiciones(x,y);
                    tablero.showTablero();
                    String message = cliente.readData();
                    System.out.println(message);
                }
               // tablero.showTablero();
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
