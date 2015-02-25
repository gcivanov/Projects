/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

public interface UserI {
    public String getName();
    public void setName(String name);
    public String getPassword();
    public void setPassword(String password);
    public boolean userCanEncryption();
    public void setCanEncryption(boolean canEncryption);
    public void setCanDecryption(boolean canDecryption);
    public boolean userCanDecryption();
}
