package finalJava;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.*;
public class FlipACoin {

	

	private static final String YES_NO_OPTION = null;

	public static void displayOutcome()
	{
	do{
		Random r = new Random();
		int computer = r.nextInt(2) + 1;
		String userPick, computerPick, result = null;
		String userAnswer, computerAnswer = null;
		int answerInt,  winnings = 0, lose;
		
		
		userAnswer = JOptionPane.showInputDialog(null, "Choose \n1 for Heads\n2 for Tails");
		answerInt = Integer.parseInt(userAnswer);
		
		if(answerInt == 1)
		{
			userAnswer = "Heads";
			if(computer == 1)
			{
				computerAnswer = "Heads";
				result = "You Win Hahahahaha!";
				winnings = winnings + 25;
			}
			else if(computer  == 2)
			{
				computerAnswer = "Tails";
				result = "You lose, oh no!";
				winnings = winnings - 25;
			}
		}
		if(answerInt == 2)
		{
			userAnswer = "Tails";
			if(computer == 2)
			{
				computerAnswer = "Tails";
				result = "You Win Hahahahaha!";
				winnings = winnings + 25;
			}
			else if(computer  == 1)
			{
				computerAnswer = "Heads";
				result = "You lose, oh no!";
				winnings = winnings - 25;
			}
		}

		JOptionPane.showMessageDialog(null,  "You picked " + userAnswer + ". The coin landed on " + computerAnswer + ". " + result);
		JOptionPane.showInputDialog(null,  "Do you want to play again?", YES_NO_OPTION);
	}while("Yes".equals(YES_NO_OPTION));
	}
}
