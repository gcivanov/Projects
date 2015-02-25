/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi.server.register;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

public class RegisterRMIServer {
    
    public static void main(String args[]) {
        try {
            ServerInterface si = new ServerImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MyRMI", si);
            
            
            System.out.printf("Student server %s registered",si);

        }
        catch (Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        
    }
}