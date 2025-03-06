package trivia;

public class Question {
    private final String text;
    private final Category category;

    public Question(String text, Category category){
        this.text = text;
        this.category = category;
    }
    
    public String getText(){
        return text;
    }
    public Category getCategory(){
        return category;
    }
}
