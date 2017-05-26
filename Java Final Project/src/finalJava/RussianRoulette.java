package finalJava;

import javax.swing.JOptionPane;

public class RussianRoulette {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int moneyWagered = 0, totalMonies = 500;
		
		final int MIN = 1;
		final int MAX = 6;
		int random;
		int userNumber;
		int guess;
		boolean isMatch;
		String guessString;
		MoneyTotal.displayMyMoney(moneyWagered, totalMonies);
		random = MIN + (int)(Math.random() * MAX);
	userNumber = MIN + (int)(Math.random() * MAX);
		guessString = JOptionPane.showInputDialog(null, "Enter \n1 to pull The Trigger \n2 to chicken out", "random guess",JOptionPane.INFORMATION_MESSAGE);
		
		guess = Integer.parseInt(guessString);
		isMatch = guess == random;
		
		if (userNumber == random)
		{
			JOptionPane.showMessageDialog(null, "You died LOL!");
			MoneyTotal.lostMoney(moneyWagered, totalMonies);
			
		}		
		else {
			JOptionPane.showMessageDialog(null, "You live for today.");
			MoneyTotal.addMoney(moneyWagered, totalMonies);
		}	
	}
}