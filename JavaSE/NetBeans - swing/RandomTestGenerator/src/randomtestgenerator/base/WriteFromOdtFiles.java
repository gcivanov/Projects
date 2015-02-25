package randomtestgenerator.base;

import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.text.Paragraph;


// http://incubator.apache.org/odftoolkit/mailing-lists.html
public class WriteFromOdtFiles {

    public WriteFromOdtFiles() {
        
    }
    
    public synchronized void newOdt(String file , String info){
        TextDocument outputOdt;
        try {
            
            outputOdt = TextDocument.newTextDocument();
            
            outputOdt.addParagraph(info);
            
            outputOdt.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /* dont read new lines ... :
    public void read(String filePath ){
        TextDocument textdoc;
        try {
            textdoc = (TextDocument) TextDocument.loadDocument( filePath );
            
            String res = textdoc.getContentRoot().getTextContent();
            
            for(int i = 0 ; i < res.length() ; i++) {
                if(res.charAt(i)=='\n')
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!111");
            }
            
            
            Paragraph p1 = textdoc.getParagraphByIndex(0, true);
            String s = p1.getTextContent();
            System.out.println(" "+p1.getTextContent());

            System.out.println(textdoc.getParagraphByIndex(1, true).getTextContent());



            for(int i = 0 ; i < s.length() ; i++) {
                    if(s.charAt(i)=='\n')
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!111");
            }

            System.out.println(textdoc.getParagraphContainerElement().getTextContent().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */    
    
    
}
