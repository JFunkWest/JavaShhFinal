package finalJava;

import javax.swing.JOptionPane;

public class MainMenu extends FlipACoin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String choice;
		int choiceConvert;
		choice = JOptionPane.showInputDialog(null, "Which game are you going to select?\n1: Flip a coin\n2: Tic Tac Toe "
				+ "\n3: Guess the number \n4: Black Jack \n5: Russian Roulette  "); 
		choiceConvert = Integer.parseInt(choice);
		
		if(choiceConvert == 1)
		{
			FlipACoin.displayOutcome();
		}
	}

}
