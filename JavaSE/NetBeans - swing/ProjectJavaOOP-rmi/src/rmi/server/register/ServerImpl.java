package rmi.server.register;

//import com.thoughtworks.xstream.XStream;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import substitution.cipher.SubstitutionCipher;
import user.*;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    
    private final List<UserI> list;
    private final HashMap<String,UserI> bufferMap;
    
    private JTextArea displayArea;
    private JFrame jFrame;
    
    public ServerImpl() throws RemoteException {
        list = new ArrayList<>(GenerateUsers.generateUsers());
        
        //initialize new bufferMap for information 
        bufferMap = new HashMap<>(list.size());
        for(int index = 0 ; index < list.size() ; ++index) {
            bufferMap.put(list.get(index).getName(), list.get(index));
        }
        startFrame();
    }
    
    private void startFrame() {
        jFrame = new JFrame("Server RMI");
        displayArea = new JTextArea();
        
        jFrame.add( new JScrollPane( displayArea ), BorderLayout.CENTER );
        jFrame.setSize( 220, 300 );
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible( true );
        
        displayMessage(" server is started \n");
    }
    
    @Override
    public boolean existUser(String username, String pass) throws RemoteException {
        displayMessage("User: " + username + " existUser \n");
        
        if( username != null && pass != null ) {
            if(bufferMap.containsKey(username)) {
                if(bufferMap.get(username).getPassword().equals(pass)) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean userCanEncryption(String name) throws RemoteException {
        displayMessage("User: " + name + "userCanEncryption \n");
        
        return bufferMap.get(name).userCanEncryption();
    }

    @Override
    public boolean userCanDecryption(String name) throws RemoteException {
        displayMessage("User: " + name + "userCanDecryption \n");
        
        return bufferMap.get(name).userCanDecryption();
    }

    @Override
    public String encryptionCode(String code) throws RemoteException {
        displayMessage("  encryptionCode \n");
        
        if(code != null) {
            return SubstitutionCipher.encript(code);
        }
        return null;
    }

    @Override
    public String decryptionCode(String code) throws RemoteException {
        displayMessage("  decryptionCode \n");
        
        if(code != null) {
            return SubstitutionCipher.decript(code);
        }
        return null;
    }
    
    private void displayMessage( final String messageToDisplay )
    {
       SwingUtilities.invokeLater(
          new Runnable() 
          {
             @Override
             public void run()
             {
                displayArea.append( messageToDisplay );
             } 
          }
       );
    }
    
//    private void readFromXML(){
//        UserI s =  list.get(0);
//        XStream xstream = new XStream();
//        System.out.println(xstream.toXML(s));
//        
//    }
//    public static void main(String args[]) {
//        try{
//        ServerImpl ss = new ServerImpl();
//        ss.readFromXML();
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
}


