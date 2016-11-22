/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoPSP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Gregorio
 */
public class EnviarArchivo{
    
    private String nombreArchivo = "";
 
    public EnviarArchivo( String nombreArchivo ){
    this.nombreArchivo = nombreArchivo;
    }   
   
public void enviarArchivo( ) throws IOException{
    try{            
        // Creamos la direccion IP de la maquina que recibira el archivo
        InetAddress direccion = InetAddress.getByName("localhost");
        // Creamos el Socket con la direccion y elpuerto de comunicacion
        Socket socket = new Socket( direccion, 21 );            
        socket.setSoTimeout( 2000 );            
        socket.setKeepAlive( true );
        // Creamos el archivo que vamos a enviar
        File archivo = new File( nombreArchivo );
        // Obtenemos el tamaño del archivo            
        int tamañoArchivo = ( int )archivo.length();
        // Creamos el flujo de salida, este tipo de flujo nos permite             
        // hacer la escritura de diferentes tipos de datos tales como            
        // Strings, boolean, caracteres y la familia de enteros,    etc.
        DataOutputStream dos = new DataOutputStream( socket.getOutputStream() );
        System.out.println( "Enviando Archivo: "+archivo.getName() );
        // Enviamos el nombre del archivo             
        dos.writeUTF( archivo.getName() );            
        // Enviamos el tamaño del archivo            
        dos.writeInt( tamañoArchivo );            
        // Creamos flujo de entrada para realizar la lectura del archivo en bytes
        FileInputStream fis = new FileInputStream(nombreArchivo);            
        BufferedInputStream bis = new BufferedInputStream( fis );
        // Creamos el flujo de salida para enviar los datos del archivo en bytes
        BufferedOutputStream bos = new BufferedOutputStream( socket.getOutputStream());
        // Creamos un array de tipo byte con el tamaño del archivo 
        byte[] buffer = new byte[ tamañoArchivo ];
        // Leemos el archivo y lo introducimos en el array de bytes             
        bis.read( buffer );             
        // Realizamos el envio de los bytes que conforman el archivo
        for( int i = 0; i < buffer.length; i++ ){                
            bos.write( buffer[ i ] );
        }                     
    System.out.println( "Archivo Enviado: "+archivo.getName() );
    // Cerramos socket y flujos            
        bis.close();
        bos.close();
        socket.close();
        }catch( Exception e ){            
        System.out.println( e.toString() );
        }   
    }    
public static void main( String args[] ) throws IOException{
    EnviarArchivo ea = new EnviarArchivo("archivo");             
    //El archivo deberá contener la ruta completa, por ej.: D:\\ARCHIVOS\\psp.doc
    ea.enviarArchivo();  
    }   
}
