package com.sergiojavierre.aplicacion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion
{
    private final int PUERTO = 1234; //Puerto para la conexión
    private final String HOST = "192.168.0.170"; //Host para la conexión
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente

    DataInputStream din=null;
    DataOutputStream dout=null;
    BufferedReader br=null;

    public Conexion(String tipo) throws IOException //Constructor
    {
        if(tipo.equalsIgnoreCase("servidor"))
        {
            ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            cs = ss.accept();
        }
        else
        {
            cs = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
        din=new DataInputStream(cs.getInputStream());
        dout=new DataOutputStream(cs.getOutputStream());
        br=new BufferedReader(new InputStreamReader(System.in));
    }

    public void sendMessage(String message) throws IOException {
        dout.writeUTF(message);
        dout.flush();
    }


    public String readData() throws IOException {
        return din.readUTF();
    }
}