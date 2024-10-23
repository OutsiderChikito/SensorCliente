/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package RMISensor;

import java.util.Random;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void sleep(int milsegs){
        try {
                Thread.sleep(milsegs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    
    public static void main(String[] args) {
        Random rm = new Random();
        int temperatura = 22; //Temperatura inicial
        int limSuper= 30; //Limite superior
        int limInfe= 20; //Limite inferior
        System.out.println("Iniciando conexión con el sensor...");
        
        try {
            
            // Buscar el registro RMI
            Registry registry = LocateRegistry.getRegistry("172.31.25.28", 8080);

            // Obtener la referencia al servicio remoto
            Sensor sensor = (Sensor) registry.lookup("SensorService");
            
            System.out.println("Conexión realizada Correctamante");
            
            while (true) {
                
            int cambio = rm.nextInt(5) - 2; //Rangos de -2 y 2
            temperatura += cambio;
            
            if (limInfe<temperatura && limSuper>temperatura) {
                // Llamada remota al metodo
                sensor.sensorTemperatura(temperatura);
                sleep(1500);
            } else if (limSuper<=temperatura) {
                sensor.sensorTemperatura(limSuper);
                temperatura = limInfe;
            } else{
                temperatura = limInfe;
            }
        }

        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}
