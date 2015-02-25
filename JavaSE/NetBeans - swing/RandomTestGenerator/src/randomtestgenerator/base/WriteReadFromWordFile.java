
package randomtestgenerator.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.POIXMLException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WriteReadFromWordFile {
    

    
    public WriteReadFromWordFile() {
        
    }
    
    
    public List<String> read(String filePath){
        synchronized(filePath) {
            List<String> result = null;
            try{
                XWPFDocument document = new XWPFDocument( new FileInputStream(
                 new File(filePath) ));

                XWPFWordExtractor extractor = new XWPFWordExtractor(document);

                String text = extractor.getText();

                if(text.isEmpty() || text.equals("") || text.equals("\n") ||  text.equals("\r") )
                    return null;

                result = new LinkedList<>();
                
                int countForNewQ=0;
                int countForSpaces=0;

                StringBuilder sb = new StringBuilder();

                for(int i = 0 ; i < text.length(); i++) {
                        if(sb != null && !sb.toString().equals("")&& !sb.toString().equals("\n")
                                    && !sb.toString().equals("\r") && countForNewQ == 2 && i > 8) {
                            
                            countForNewQ = 0;
                            result.add(sb.toString());
                            sb = new StringBuilder();

                        }
                        
                        if( text.charAt(i) != '\n' && text.charAt(i) != '\r' ){
                            countForNewQ = 0;
                            
                            if(i > 0 && text.charAt(i)==' ' ) {
                                ++countForSpaces;
                            } else {
                                countForSpaces = 0;
                            }
                            if( countForSpaces <= 1 && text.charAt(i) != '\t' && countForNewQ < 2 ) {
                                sb.append(text.charAt(i));
                            }
                        }
                        else {
                            ++countForNewQ;
                            if(i > 0 && countForNewQ == 1 && (text.charAt(i - 1) != '\n' && text.charAt(i - 1) != '\r') ){ 
//                                    || ( text.charAt(i) != '\n' && text.charAt(i) != '\r' ) ) {
                                sb.append("\n");
                            }
                        }

                }
                if(sb != null && !sb.toString().equals("")&& !sb.toString().equals("\n")
                            && !sb.toString().equals("\r") ) {
                    result.add(sb.toString());
                }


            }
            catch(POIXMLException e){
                result = readDoc(filePath);
            }
            catch(FileNotFoundException e){ 
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

            return result;
        }
    }
    
    private List<String> readDoc(String filePath) {
        synchronized(filePath) {
            List<String> result = null;
            try {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
                HWPFDocument doc = new HWPFDocument(fs);
                WordExtractor extractor = new WordExtractor(doc);

                String text = extractor.getText();

                if(text.isEmpty() || text.equals("") || text.equals("\n") || text.equals("\r"))
                    return null;

                result = new LinkedList<>();

                int countForNewQ=0;
                int countForSpaces=0;

                StringBuilder sb = new StringBuilder();

                for(int i = 0 ; i < text.length(); i++) {

                    if(!sb.toString().equals("") && !sb.toString().equals("\n")
                                && !sb.toString().equals("\r") && countForNewQ > 2 && i > 8) {

                        countForNewQ = 0;
                        result.add(sb.toString());
                        sb = new StringBuilder();
                    }

                    if( text.charAt(i) != '\n' && text.charAt(i) != '\r' ){
                        countForNewQ = 0;

                        if(i > 0 && text.charAt(i)==' ' ) {
                            ++countForSpaces;
                        } else {
                                countForSpaces = 0;
                        }
                        if( countForSpaces <= 1 && text.charAt(i) != '\t' && countForNewQ < 1 ) {
                            sb.append(text.charAt(i));
                        }
                    }
                    else {
                        ++countForNewQ;
                        if(i > 0 && countForNewQ == 1 && (text.charAt(i - 1) != '\n' && text.charAt(i - 1) != '\r') ){
                            sb.append("\n");
                        }
                    }

                }
                if(!sb.toString().equals("")&& !sb.toString().equals("\n")
                            && !sb.toString().equals("\r") ) {
                    result.add(sb.toString());
                }

            }
            catch(FileNotFoundException e){
                e.printStackTrace();

            }
            catch(IOException e){
                e.printStackTrace();

            }

            return result;
        }
    }
    public synchronized void newDocx(String file, String paragraphs){
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        
        for(int index = 0 ; index < paragraphs.length() ; ++index ) {
            if(index > 0 && paragraphs.charAt(index-1) == '\n' && paragraphs.charAt(index) == '\n' ) {
                paragraph = document.createParagraph();
                run = paragraph.createRun();
            } else if(index > 0 && paragraphs.charAt(index-1) != '\n' && paragraphs.charAt(index) == '\n' ) {
                run.addBreak();
            }
            
            run.setText(""+paragraphs.charAt(index));
            run.setFontSize(14);
//            run.setFontFamily("Times New Roman");
        }
        
        
        
        try(FileOutputStream out = new FileOutputStream(file);) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
    
    
    
    /*
    public synchronized void newDocx(String file, List<String> listParagraphs){
        XWPFDocument document = new XWPFDocument();
        
        
        for(int index = 0 ; index < listParagraphs.size() ; ++index ) {
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setNumID(BigInteger.ONE);
            XWPFRun run = paragraph.createRun();
            
            run.setText(listParagraphs.get(index));
            run.setFontSize(14);
//            run.setFontFamily("Times New Roman");
        }
        
        
        
        try(FileOutputStream out = new FileOutputStream(file);) {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
    */
}
