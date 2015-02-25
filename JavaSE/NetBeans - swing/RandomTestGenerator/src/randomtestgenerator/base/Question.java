
package randomtestgenerator.base;

import randomgenerator.exception.InvalidParameters;

public class Question {
    
    private String category;
    private String question;
    
    
    public Question(String category, String question) throws InvalidParameters{
        if(category != null && question != null) {
            this.category = category;
            this.question = question;
        } else {
            throw new InvalidParameters("invalid parameters try again");
        }
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public void setCategory(String category) {
        if(category != null)
            this.category = category;
    }

    public void setQuestion(String question) {
        if(question != null)
            this.question = question;
    }
    
}
