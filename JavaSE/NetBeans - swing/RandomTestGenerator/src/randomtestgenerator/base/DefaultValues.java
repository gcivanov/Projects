

package randomtestgenerator.base;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class DefaultValues implements Serializable {
    
    private int allNumQuestions;
    private boolean randomChoiseFromCategories;
    private HashMap<String,Integer> categoriesSelectedNum;
    private String directForSaving;
    private int idForSaved;
    
    static final long serialVersionUID = -609421954100196333L;
    
    

    
    public DefaultValues(){
        categoriesSelectedNum = new HashMap<>(7);
        directForSaving = null;
        idForSaved = 0;
    }
    
    

    public int getAllNumQuestions() {
        return allNumQuestions;
    }

    public void setAllNumQuestions(int allNumQuestions) {
        if(allNumQuestions > 0)
            this.allNumQuestions = allNumQuestions;
    }

    public int getIdForSaved() {
        return idForSaved;
    }

    public void setIdForSavedPlusPlus() {
        ++this.idForSaved;
    }
    

    public boolean isRandomChoiseFromCategories() {
        return randomChoiseFromCategories;
    }

    public void setRandomChoiseFromCategories(boolean randomChoiseFromCategories) {
        this.randomChoiseFromCategories = randomChoiseFromCategories;
    }

    public String getDirectForSaving() {
        return this.directForSaving;
    }

    public  void setDirectForSaving(String directForSaving) {
        if(directForSaving != null) {
            this.directForSaving = directForSaving;
        }
    }

    public HashMap<String, Integer> getCategoriesSelectedNum() {
        return categoriesSelectedNum;
    }

    public void setCategoriesSelectedNum(HashMap<String, Integer> categoriesSelectedNum) {
        if(categoriesSelectedNum != null)
            this.categoriesSelectedNum = categoriesSelectedNum;
    }
    
    
    public void addCateroriesSelectedNum(String category, int num ) {
        if(num > 0 && category != null && categoriesSelectedNum.containsKey(category) ) {
            categoriesSelectedNum.put(category, num);
        }
    }
    
    public int getCategoriesSelectedNum(String name) {
        if(name != null && categoriesSelectedNum.containsKey(name)) {
            return categoriesSelectedNum.get(name);
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return " all Num " + allNumQuestions +
    " randomChoiseFromCat " + randomChoiseFromCategories+
    " categories NUM: " +categoriesSelectedNum + 
    " direction for saving " + directForSaving + 
    " id " + idForSaved;        
    }
    
    
}
