/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.List;
import randomtestgenerator.base.WriteReadFromWordFile;

public class ThreadForReadWordFile implements Runnable {
    private List<String> list;
    private String path;
    
    public ThreadForReadWordFile( String path ) {
        this.path = path;
    }
    
    
    
    @Override
    public void run() {
        WriteReadFromWordFile wr = new WriteReadFromWordFile();
        list = wr.read(path);
    }

    
    public List<String> getList() {
        return list;
    }
    
    
    
    
}
