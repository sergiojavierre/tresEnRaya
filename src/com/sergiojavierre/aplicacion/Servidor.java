package com.sergiojavierre.aplicacion;

import com.sergiojavierre.entidades.Estado;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());
/*
            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");
*/
            //Se obtiene el flujo entrante desde el cliente
            entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
/*


            System.out.println("Fin de la conexión");
*/
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getMessage() throws IOException {
        while((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
        {
            return mensajeServidor;
        }
        return "empty";
    }

    public void sendPosiciones(int x, int y){
        try {
            salidaCliente.writeUTF(x+";"+y);
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