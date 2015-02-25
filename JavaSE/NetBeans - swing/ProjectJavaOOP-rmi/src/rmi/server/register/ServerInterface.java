/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi.server.register;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterface extends Remote {
    
    boolean existUser(String Username , String pass ) throws RemoteException;
    
    boolean userCanEncryption(String name) throws RemoteException;;
    boolean userCanDecryption(String name) throws RemoteException;;
    
    String encryptionCode(String code) throws RemoteException;;
    String decryptionCode(String code) throws RemoteException;;
    
}
