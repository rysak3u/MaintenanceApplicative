package trivia;

import java.util.LinkedList;

public abstract class Questions {
   String categorie;
   LinkedList<String> questions;


   public String getCategorie(){
    return categorie+"";
   }

@SuppressWarnings("unchecked")
public LinkedList<String> getQuestions(){
    return (LinkedList<String>) questions.clone();
   }

public String askQuestion(){
    return questions.removeFirst();
}
}
