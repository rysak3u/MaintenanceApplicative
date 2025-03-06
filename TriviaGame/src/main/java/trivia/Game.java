package trivia;

// REFACTOR ME
public class Game implements IGame {
   IPlayerManager playerManager;
   IQuestionsDeck questions;
   ILogger logger;

   public Game() {
      playerManager = new PlayerManager();
      questions = new QuestionsDeck();
      logger = new ConsoleLogger();
   }

   public boolean add(String playerName) {
      playerManager.addPlayer(playerName);

      logger.log(playerName + " was added");
      logger.log("They are player number " + playerManager.howManyPlayers());
      return true;
   }

   public void handlePlayerInPenaltyBox(int roll){
      if (roll % 2 != 0) {
         logger.log(playerManager.getCurrentPlayer().getName() + " is getting out of the penalty box");
         playerManager.getCurrentPlayer().setInPenaltyBox(false);
         handleMove(roll);
      } else {
         logger.log(playerManager.getCurrentPlayer().getName() + " is not getting out of the penalty box");
      }
   }


   public void roll(int roll) {
      Player player = playerManager.getNextPlayer();
      logger.log(player.getName() + " is the current player");
      logger.log("They have rolled a " + roll);

      if (player.isInPenaltyBox()) {
        handlePlayerInPenaltyBox(roll);
      }else{
         handleMove(roll);
      }
      
   }
   public void handleMove(int roll){
      playerManager.getCurrentPlayer().move(roll);;
      logger.log(playerManager.getCurrentPlayer().getName()
                         + "'s new location is "
                         + playerManager.getCurrentPlayer().getPosition());
      logger.log("The category is " + currentCategory());
      askQuestion();
   }
   private void askQuestion() {
      logger.log(questions.getNextQuestion(currentCategory()));
   }


   private Category currentCategory() {
      return Category.fromPosition(playerManager.getCurrentPlayer().getPosition()-1);
   }

   public boolean handleCorrectAnswer() {
      if(!playerManager.getCurrentPlayer().isInPenaltyBox()){
         logger.log("Answer was correct!!!!");
         playerManager.getCurrentPlayer().addGoldCoin();
         logger.log(playerManager.getCurrentPlayer().getName()
                            + " now has "
                            + playerManager.getCurrentPlayer().getGoldCoins()
                            + " Gold Coins.");
      }

      return !didPlayerWin();
   }

   public boolean wrongAnswer() {
      logger.log("Question was incorrectly answered");
      logger.log(playerManager.getCurrentPlayer().getName() + " was sent to the penalty box");
      playerManager.getCurrentPlayer().setInPenaltyBox(true);

      return true;
   }


   private boolean didPlayerWin() {
      return playerManager.getCurrentPlayer().getGoldCoins() == 6;
   }
}
