package trivia;

import java.util.ArrayList;

// REFACTOR ME
public class Game implements IGame {
   ArrayList<Player> players = new ArrayList<Player>();
   QuestionsDeck questions;
   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

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
         isGettingOutOfPenaltyBox = true;
         Player player = players.get(currentPlayer);
         System.out.println(players.get(currentPlayer).getName() + " is getting out of the penalty box");
         players.get(currentPlayer).move(roll);
         System.out.println(player.getName()
                            + "'s new location is "
                            + player.getPosition());
         System.out.println("The category is " + currentCategory());
         askQuestion();
      } else {
         System.out.println(players.get(currentPlayer).getName() + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
      }
   }
   public Player getCurrentPlayer(int pos){
      return players.get(pos);
   }
   public void roll(int roll) {
      System.out.println(players.get(currentPlayer).getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (getCurrentPlayer(currentPlayer).isInPenaltyBox()) {
        handlePlayerInPenaltyBox(roll);
      } else {

         getCurrentPlayer(currentPlayer).move(roll);;

         System.out.println(players.get(currentPlayer).getName()
                            + "'s new location is "
                            + getCurrentPlayer(currentPlayer).getPosition());
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      System.out.println(questions.getNextQuestion(currentCategory()));
   }


   private Category currentCategory() {
      return Category.fromPosition(getCurrentPlayer(currentPlayer).getPosition()-1);
   }

   public boolean handleCorrectAnswer() {
      if (getCurrentPlayer(currentPlayer).isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was corrent!!!!");
            getCurrentPlayer(currentPlayer).addGoldCoin();
            System.out.println(players.get(currentPlayer).getName()
                               + " now has "
                               + getCurrentPlayer(currentPlayer).getGoldCoins()
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            getNextPlayer();

            return winner;
         } else {
            getNextPlayer();
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         getCurrentPlayer(currentPlayer).addGoldCoin();
         System.out.println(players.get(currentPlayer).getName()
                            + " now has "
                            + getCurrentPlayer(currentPlayer).getGoldCoins()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         getNextPlayer();
         return winner;
      }
   }
   public void getNextPlayer(){
      currentPlayer++;
      if (currentPlayer >= players.size()) currentPlayer = 0;
   }
   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer).getName() + " was sent to the penalty box");
      getCurrentPlayer(currentPlayer).setInPenaltyBox(true);

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(getCurrentPlayer(currentPlayer).getGoldCoins() == 6);
   }
}
