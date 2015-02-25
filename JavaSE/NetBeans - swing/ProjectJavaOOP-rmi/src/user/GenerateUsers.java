
package user;
//import com.thoughtworks.xstream.*;
import java.io.File;
import java.util.Formatter;

import java.util.LinkedList;

public abstract class GenerateUsers {
    
    public static LinkedList<UserI> generateUsers(){
        UserI a = new User("A X", "123");
        UserI b = new User("B X", "123");
        UserI c = new User("C X", "123");
        UserI d = new User("D X", "12345");
        
        a.setCanDecryption(true);
        a.setCanEncryption(true);
        
        b.setCanDecryption(true);
        b.setCanEncryption(false);
        
        c.setCanDecryption(false);
        c.setCanEncryption(true);
        
        
        LinkedList<UserI> list = new LinkedList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        
//        XStream xstream = new XStream();
//        try{
//            Formatter f = new Formatter(new File("myFile"));
//            
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
        
        return list;
    }
    
    
}
