package com.sergiojavierre.aplicacion;


import java.io.IOException;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    public Servidor() throws IOException {super("servidor");} //Se usa el constructor para servidor de Conexion

    public void cerrarSesion(){
        try {
            ss.close();//Se finaliza la conexión con el cliente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}