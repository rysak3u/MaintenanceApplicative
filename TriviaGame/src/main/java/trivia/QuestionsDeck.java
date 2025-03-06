package trivia;

import java.util.EnumMap;
import java.util.LinkedList;

public class QuestionsDeck implements IQuestionsDeck {
   private final EnumMap<Category,LinkedList<Question>> questions = new EnumMap<>(Category.class);

    public QuestionsDeck(){
        for(Category category : Category.values()){
            questions.put(category, new LinkedList<Question>());
            for(int i = 0;i<50;i++){
                questions.get(category).addLast(new Question(category.name() + " Question "+ i,category));
            }
        }
    }

    public String getNextQuestion(Category category){
        return questions.get(category).removeFirst().getText();
    }

}
