/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMISensor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sensor extends Remote{
    public void sensorTemperatura(int a) throws RemoteException;
}
