package finalJava;

import javax.swing.JOptionPane;

public class MainMenu extends MoneyTotal {

	private static final Object YES_NO_OPTION = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String choice;
		String playAgain = null;
		int choiceConvert;
		do{
		choice = JOptionPane.showInputDialog(null, "Which game are you going to select?\n1: Flip a coin\n2: Tic Tac Toe "
				+ "\n3: Guess the number \n4: Black Jack \n5: Russian Roulette  "); 
		choiceConvert = Integer.parseInt(choice);
		
		if(choiceConvert == 1)
		{
			do
			{
			FlipACoin.displayOutcome();
			playAgain =	JOptionPane.showInputDialog(null,  "Do you want to play again? Type 'Yes' for yes, or 'No' for no.", YES_NO_OPTION);
			}while("Yes".equals(playAgain));
		}
		if(choiceConvert == 2)
		{
			int hi;
		}
		
		if(choiceConvert == 3)
		{
			int hiTwo;
		}
		
		if(choiceConvert == 4)
		{
			BlackJack.displayBlackJack();
		}
		
		if(choiceConvert == 5)
		{
			int hiThree;
		}
		}while("No".equals(choiceConvert));
	}
}
