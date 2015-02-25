package randomtestgenerator.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class WriteReadFromSer {
    
    private final String path = "dir.bin";
    private File file;
    
    public WriteReadFromSer() {
        createFile();
    }
    
    
    private void createFile(){
        try{
            file = new File(path);
            if(!file.isFile()) {
//                file.mkdirs();
                file.createNewFile();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isEmpty(){
        if(file.length() < 2) 
            return true;
        
        return false;
    }
    
    public void clear() throws IOException{
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
        
    }
    
    public void write( DefaultValues def ) throws IOException {
        this.clear();
        
        ObjectOutputStream ous = new ObjectOutputStream( new FileOutputStream(path));
        ous.writeObject(def);


        ous.close();
    }
    
    public DefaultValues read() throws IOException, ClassNotFoundException {
        DefaultValues returnValue = null;
        if(file.length() > 0) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            returnValue = (DefaultValues) ois.readObject();
            
            ois.close();
        }
        return returnValue;
    }
    
    
//    public void w(WriteReadFromSer wr){
//        DefaultValues dv2 = new DefaultValues();
//        
//        dv2.setAllNumQuestions(522255);
//        dv2.setRandomChoiseFromCategories(true);
//        HashMap<String, Integer> list1 = new HashMap<>();
//        list1.put("Kuma lisa bqga",99);
//        list1.put("Kum4o vul4o - bai asen",919191919);
//        dv2.setCategoriesSelectedNum(list1);
//        
//        System.out.println(" start ");
//        try{
//            wr.write(dv2);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("  end  ");
//    }
//    public void r(WriteReadFromSer wr ) {
//        System.out.println(" start 2");
//        
//        try{
//            
//            DefaultValues d;
//            d  = wr.read();
//            System.out.println(d);
////            System.out.println(wr.readAll());
//            
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("  end  2");
//    }
//    
//    public static void main(String[] args) {
//        
//        //test ! spam ;d
//        
//        System.out.println(File.separator);
//        
//        WriteReadFromSer wr1 = new WriteReadFromSer();
//        
//        WriteReadFromSer wr2 = new WriteReadFromSer();
//        
//        DefaultValues dv = new DefaultValues();
//        
//        dv.setAllNumQuestions(100);
//        dv.setRandomChoiseFromCategories(false);
//        HashMap<String,Integer> list = new HashMap<>();
//        list.put("123 qga",21233);
//        list.put("Kum4o vul4o",200);
//        dv.setCategoriesSelectedNum(list);
//        
//        DefaultValues dv2 = new DefaultValues();
//        
//        dv2.setAllNumQuestions(522255);
//        dv2.setRandomChoiseFromCategories(true);
//        HashMap<String, Integer> list1 = new HashMap<>();
//        list1.put("Kuma lisa bqga",99);
//        list1.put("Kum4o vul4o - bai asen",919191919);
//        dv2.setCategoriesSelectedNum(list1);
//        
////        for(int i = 0 ; i < 12 ; i++) {
////            DefaultValues dv3 = DefaultValues.getInstance();
////
////            dv3.setAllNumQuestions(i);
////            dv3.setRandomChoiseFromCategories(true);
////            HashMap<String, Integer> list2 = new HashMap<>();
////            list2.put("Kuma lisa bqga",99+i);
////            list2.put("Kum4o vul4o - bai asen",91919191+i);
////            dv3.setCategoriesSelectedNum(list1);
////        
////            try{
////                wr1.write(dv3);
////            }catch(Exception e) {
////                e.printStackTrace();
////            }
////        
////        }
//        
//        System.out.println(" start ");
//        try{
////            wr1.write(dv);
//            wr1.clear();
////            wr1.write(dv2);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("  end  ");
//        
//        
//        
//        wr1.w(wr1);
//        
//        WriteReadFromSer wr3 = new WriteReadFromSer();
//        wr3.r(wr3);
//        try{
//            wr3.clear();
//        }catch(Exception e){
//        }
//    }
    
}
