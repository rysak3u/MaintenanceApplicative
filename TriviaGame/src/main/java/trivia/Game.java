package trivia;

import java.util.ArrayList;

// REFACTOR ME
public class Game implements IGame {
   ArrayList<Player> players = new ArrayList<Player>();
   IQuestionsDeck questions;
   int currentPlayer = -1; //On Commence à -1 car on fait le getNextPlayer() dans le roll

   public Game() {
      questions = new QuestionsDeck();
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void handlePlayerInPenaltyBox(int roll){
      if (roll % 2 != 0) {
         System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
         getCurrentPlayer().setInPenaltyBox(false);
         handleMove(roll);
      } else {
         System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
      }
   }

   public Player getCurrentPlayer(){
      return players.get(currentPlayer);
   }

   public void roll(int roll) {
      getNextPlayer();
      System.out.println(players.get(currentPlayer).getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (getCurrentPlayer().isInPenaltyBox()) {
        handlePlayerInPenaltyBox(roll);
      }else{
         handleMove(roll);
      }
      
   }
   public void handleMove(int roll){
      getCurrentPlayer().move(roll);;
      System.out.println(players.get(currentPlayer).getName()
                         + "'s new location is "
                         + getCurrentPlayer().getPosition());
      System.out.println("The category is " + currentCategory());
      askQuestion();
   }
   private void askQuestion() {
      System.out.println(questions.getNextQuestion(currentCategory()));
   }


   private Category currentCategory() {
      return Category.fromPosition(getCurrentPlayer().getPosition()-1);
   }

   public boolean handleCorrectAnswer() {
      if(!getCurrentPlayer().isInPenaltyBox()){
         System.out.println("Answer was correct!!!!");
         getCurrentPlayer().addGoldCoin();
         System.out.println(players.get(currentPlayer).getName()
                            + " now has "
                            + getCurrentPlayer().getGoldCoins()
                            + " Gold Coins.");
      }

      return !didPlayerWin();
   }
   public void getNextPlayer(){
      currentPlayer++;
      if (currentPlayer >= players.size()) currentPlayer = 0;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
      getCurrentPlayer().setInPenaltyBox(true);

      return true;
   }


   private boolean didPlayerWin() {
      return getCurrentPlayer().getGoldCoins() == 6;
   }
}
