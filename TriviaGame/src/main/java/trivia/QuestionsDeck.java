package trivia;

import java.util.EnumMap;
import java.util.LinkedList;

public class QuestionsDeck {
   private final EnumMap<Category,LinkedList<String>> questions = new EnumMap<>(Category.class);

    public QuestionsDeck(){
        for(Category category : Category.values()){
            questions.put(category, new LinkedList<String>());
            for(int i = 0;i<50;i++){
                questions.get(category).addLast(category.name() + " Question "+ i);
            }
        }
    }

    public String getNextQuestion(Category category){
        return questions.get(category).removeFirst();
    }

}
