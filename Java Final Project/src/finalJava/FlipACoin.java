package finalJava;
import java.util.*;
import javax.swing.*;
public class FlipACoin {
	 public static void main(String[] args)
	    {
	        String input1,
	        input2;
	        int choice1,
	        choice2;
	        
	        input1 =
	        		JOptionPane.showInputDialog("How much money are you willing to bet?");
	        choice1 = Integer.parseInt(input1);
	        
	        input2 =
	            JOptionPane.showInputDialog("Do you think the coin will be\n 1 for heads\n or\n 2 for tails?");
	        choice2 = Integer.parseInt(input2);
	        
	        coinToss(choice1, choice2);
	        
	        JOptionPane.showMessageDialog(null, "Thank you and come again!");
	        
	    }
	    
	    public static void coinToss(int number)
	    {
	        int coinFace,
	            coinChoice,
	            result,
	            wagerResults;
	    
	        Random tossMachine = new Random(1);
	        
	        if (coinChoice == 1) {
	        	
	        }
	        
	        else if (coinChoice == 2) {
	        	
	        }
	        
	        JOptionPane.showMessageDialog(null, "The coin landed on " + 
	                                      coinFace + ", your guess was " + coinChoice + ". You " + result + "$" + wagerResults + ". Thank you for playing.");
	        
	    }
	}