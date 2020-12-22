package com.sergiojavierre.aplicacion;

import com.sergiojavierre.entidades.Estado;

import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends Conexion
{
    public Cliente() throws IOException {super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            //Se enviarán dos mensajes
            for (int i = 0; i < 2; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");
            }

            cs.close();//Fin de la conexión

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void sendEstadoPosiciones(Estado estado, int x, int y){
        try {
            salidaServidor.writeUTF(x+";"+y+";"+estado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}