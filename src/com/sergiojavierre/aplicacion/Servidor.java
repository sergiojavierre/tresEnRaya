package com.sergiojavierre.aplicacion;


import java.io.BufferedReader;
import java.io.IOException;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    public Servidor() throws IOException {super("servidor");} //Se usa el constructor para servidor de Conexion
    private BufferedReader entrada;

    public void startServer()//Método para iniciar el servidor
    {
        try
        {
            System.out.println("Esperando..."); //Esperando conexión
            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
            System.out.println("Cliente en línea");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getMessage() throws IOException {
        return din.readUTF();
    }

    public void sendPosiciones(int x, int y){
        try {
            dout.writeUTF(x+";"+y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarSesion(){
        try {
            ss.close();//Se finaliza la conexión con el cliente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}