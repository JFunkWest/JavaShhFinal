package finalJava;

import javax.swing.JOptionPane;

public class RussianRoulette {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		final int MIN = 1;
		final int MAX = 6;
		int random;
		int guess;
		boolean isMatch;
		String guessString;
		int moneyBet;
		random = MIN + (int)(Math.random() * MAX);
		String money = JOptionPane.showInputDialog(null, "How much money are you willing to bet?");
		moneyBet = Integer.parseInt(money);
		guessString = JOptionPane.showInputDialog(null, "Enter \n1 to pull The Trigger \n2 to chicken out", "random guess",JOptionPane.INFORMATION_MESSAGE);
		
		guess = Integer.parseInt(guessString);
		isMatch = guess == random;
		
		JOptionPane.showMessageDialog(null, "Your pulled the trigger " + (random-guess) + " number away from the random number.");
		JOptionPane.showMessageDialog(null, "The number was " + random + ". The result is " + isMatch + ".");
		
		
		
		
	
	}

}
