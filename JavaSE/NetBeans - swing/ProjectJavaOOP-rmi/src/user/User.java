
package user;


public class User implements UserI  {
    
    private String name;
    private String password;
    private boolean canEncryption;
    private boolean canDecryption;
    
    
    public User(String name , String password ) {
        setName(name);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null)
            this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null)
            this.password = password;
    }

    public boolean userCanEncryption() {
        return canEncryption;
    }

    public void setCanEncryption(boolean canEncryption) {
        this.canEncryption = canEncryption;
    }

    public void setCanDecryption(boolean canDecryption) {
        this.canDecryption = canDecryption;
    }
    

    public boolean userCanDecryption() {
        return canDecryption;
    }
    
}
