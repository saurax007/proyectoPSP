/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoPSP;

/**
 *
 * @author Gregorio
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {public static void main(String[] args) {
    try {
        DatagramSocket datagramSocket = new DatagramSocket();
        
        String token = "id";
        InetAddress addr1;
        try {
            addr1 = InetAddress.getByName("localhost");
            } catch (UnknownHostException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        DatagramPacket datagrama1 = new DatagramPacket(token.getBytes(),token.getBytes().length, addr1, 5555);
        datagramSocket.send(datagrama1);
        byte[] buffer = new byte[128];
        DatagramPacket datagrama2 = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagrama2);
        String respuesta = new String(buffer);
        System.out.println("Mi dirección es "+respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}