package trivia;

// REFACTOR ME
public class Game implements IGame {
   IPlayerManager playerManager;
   IQuestionsDeck questions;

   public Game() {
      playerManager = new PlayerManager();
      questions = new QuestionsDeck();
   }

   public boolean isPlayable() {
      return (playerManager.howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      playerManager.addPlayer(playerName);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + playerManager.howManyPlayers());
      return true;
   }

   public void handlePlayerInPenaltyBox(int roll){
      if (roll % 2 != 0) {
         System.out.println(playerManager.getCurrentPlayer().getName() + " is getting out of the penalty box");
         playerManager.getCurrentPlayer().setInPenaltyBox(false);
         handleMove(roll);
      } else {
         System.out.println(playerManager.getCurrentPlayer().getName() + " is not getting out of the penalty box");
      }
   }


   public void roll(int roll) {
      Player player = playerManager.getNextPlayer();
      System.out.println(player.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (player.isInPenaltyBox()) {
        handlePlayerInPenaltyBox(roll);
      }else{
         handleMove(roll);
      }
      
   }
   public void handleMove(int roll){
      playerManager.getCurrentPlayer().move(roll);;
      System.out.println(playerManager.getCurrentPlayer().getName()
                         + "'s new location is "
                         + playerManager.getCurrentPlayer().getPosition());
      System.out.println("The category is " + currentCategory());
      askQuestion();
   }
   private void askQuestion() {
      System.out.println(questions.getNextQuestion(currentCategory()));
   }


   private Category currentCategory() {
      return Category.fromPosition(playerManager.getCurrentPlayer().getPosition()-1);
   }

   public boolean handleCorrectAnswer() {
      if(!playerManager.getCurrentPlayer().isInPenaltyBox()){
         System.out.println("Answer was correct!!!!");
         playerManager.getCurrentPlayer().addGoldCoin();
         System.out.println(playerManager.getCurrentPlayer().getName()
                            + " now has "
                            + playerManager.getCurrentPlayer().getGoldCoins()
                            + " Gold Coins.");
      }

      return !didPlayerWin();
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(playerManager.getCurrentPlayer().getName() + " was sent to the penalty box");
      playerManager.getCurrentPlayer().setInPenaltyBox(true);

      return true;
   }


   private boolean didPlayerWin() {
      return playerManager.getCurrentPlayer().getGoldCoins() == 6;
   }
}
