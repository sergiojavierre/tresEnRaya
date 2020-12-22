package com.sergiojavierre.aplicacion;

import com.sergiojavierre.entidades.Estado;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cliente extends Conexion
{
    public Cliente() throws IOException {super("cliente");} //Se usa el constructor para cliente de Conexion


    public void cerrarSesion(){
        try {
            ss.close();//Se finaliza la conexión con el cliente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}