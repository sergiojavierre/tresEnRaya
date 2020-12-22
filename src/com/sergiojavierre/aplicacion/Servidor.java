package com.sergiojavierre.aplicacion;


import java.io.BufferedReader;
import java.io.IOException;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    public Servidor() throws IOException {super("servidor");} //Se usa el constructor para servidor de Conexion

    public void sendPosiciones(int x, int y){
        try {
            dout.writeUTF(x+";"+y);
            dout.flush();
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