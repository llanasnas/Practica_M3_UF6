/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Model.Cliente;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alumne
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static Scanner read = new Scanner(System.in);
    
    public static void main(String[] args) {
        // TODO code application logic here
        mostrarMenuInici();
             
        
    }
    public static void mostrarMenuInici(){
    
        int opc = 0;
        System.out.println("Bienvenido a Avalon (La competencia de Amazon)");
        do{
            
            
            System.out.println("_________MENÚ_________");
            System.out.println("1.Iniciar sesión");
            System.out.println("2.Registrarse");
            System.out.println("3.Salir");
            try{
            opc = read.nextInt();
            
            switch(opc){
            
                case 1 :
                    
                    break;
                    
                case 2 :
                    
                    break;
                    
                case 3:
                    System.out.println("Saliendo...");
                    break;
                    
                default:
                    System.out.println("opcio incorrecte");
                    break;
            
            }
            }catch(InputMismatchException e){
                System.err.println("Introduce una opción válida");
            }
            
        }while(opc!=3);    
    }
    public static void registro(){
        boolean correcto =true;
        String[] datos = {"Nombre: ","Apellido: ","Direccion: ","Telefono: ","Correo: ","Contraseña: "};        
        String aux;
        Cliente c = new Cliente();
        for (String data: datos) {
            switch(data){
            
                case "Nombre: ":
                
                        System.out.println(datos[0]);
                        c.setNombre(String.valueOf(read.nextInt())) ;
                      
                    break;
                case "Apellido: ":

                    System.out.println(datos[1]);
                    c.setApellido(String.valueOf(read.nextInt()));
                    
                        break;
                case "Direccion: ":
                    
                    System.out.println(datos[2]);
                    c.setDireccion(String.valueOf(read.nextInt()));
                    
                    break;
                    
                case "Telefono: ":
                    
                    while(correcto){
                    
                        System.out.println(datos[3]);
                        aux = String.valueOf(read.nextInt());
                        
                    }
                    
                    
                    break;                    
            }
        }
        
        
    }
    
    public static void login(){
        
        String user,passwd = "";
        boolean correcto = false;
        
        System.out.println("Introduce el correo:");
        while(!correcto){
            user = read.next();
            if (!user.contains("@") || !user.contains(".")) {
                System.err.println("Introduce un formato de email correcto");
            }else{
                correcto = true;
            }
        }
        System.out.println("Introduce la contraseña:");
        passwd = read.next();
        
        
        
    }
    
    
}
