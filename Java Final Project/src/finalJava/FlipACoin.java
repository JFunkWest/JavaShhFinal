package finalJava;
import javax.swing.JOptionPane;

import java.util.Random;

import javax.swing.*;
public class FlipACoin {

	public static void displayOutcome()
	{
	

		Random r = new Random();
		int computer = r.nextInt(2) + 1;
		String userPick, computerPick, result = null;
		String userAnswer, computerAnswer = null;
		int answerInt,  winnings = 0, lose, moneyBet = 0;
		String money = JOptionPane.showInputDialog(null, "How much money are you willing to bet?");
		moneyBet = Integer.parseInt(money);
		userAnswer = JOptionPane.showInputDialog(null, "Choose \n1 for Heads\n2 for Tails");
		answerInt = Integer.parseInt(userAnswer);

		if(answerInt == 1)
		{
			userAnswer = "Heads";
			if(computer == 1)
			{
				computerAnswer = "Heads";
				result = "You Win Hahahahaha!";
				winnings = moneyBet + 25;
			}
			else if(computer  == 2)
			{
				computerAnswer = "Tails";
				result = "You lose, oh no!";
				winnings = moneyBet - 25;
			}
		}
		if(answerInt == 2)
		{
			userAnswer = "Tails";
			if(computer == 2)
			{
				computerAnswer = "Tails";
				result = "You Win Hahahahaha!";
				winnings = moneyBet + 25;
			}
			else if(computer  == 1)
			{
				computerAnswer = "Heads";
				result = "You lose, oh no!";
				winnings = moneyBet - 25;
			}
		}

		JOptionPane.showMessageDialog(null,  "You picked " + userAnswer + ". The coin landed on " + computerAnswer + ". " + result + " Your winnings or loss is $" + winnings + ".");
	
	
	}
}
