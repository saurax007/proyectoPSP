/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoPSP;

/**
 *
 * @author Gregorio y Ion
 */
import java.io.IOException;
import java.net.*;
public class Servidor {
    public static void main(String[] args) {
        try {InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
        DatagramSocket datagramSocket = new DatagramSocket(addr);
        while (true) {byte[] buffer = new byte[25];
        DatagramPacket datagrama1 = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagrama1);
        String respuesta = datagrama1.getAddress().toString();
        DatagramPacket datagrama2 = new DatagramPacket(respuesta.getBytes(),respuesta.getBytes().length, datagrama1.getAddress(), datagrama1.getPort());
        datagramSocket.send(datagrama2);}} catch (IOException e) {e.printStackTrace();}
    }
}