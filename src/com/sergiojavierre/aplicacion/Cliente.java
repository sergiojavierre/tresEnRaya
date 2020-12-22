package com.sergiojavierre.aplicacion;

import com.sergiojavierre.entidades.Estado;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente extends Conexion
{
    public Cliente() throws IOException {super("cliente");} //Se usa el constructor para cliente de Conexion
    private BufferedReader entrada;

    public void startClient() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));
/*
            //Se enviarán dos mensajes
            for (int i = 0; i < 2; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");
            }
*/

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void sendEstado(int x, int y){
        try {
            salidaServidor.writeUTF(x+";"+y);
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