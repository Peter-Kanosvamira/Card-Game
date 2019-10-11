import java.util.*;
import java.io.*;
public class Player
{ 	
	private ArrayList<String> playerCards;
	private String name;
	public Player(ArrayList<String> playerCards, String name)
	{
		this.playerCards = playerCards;
		this.name = name;
	}
	public void pickCard(ArrayList<String> decCards, ArrayList<String> playedCards)
	{
		// If no played cards pick from dec 
		System.out.println(this.name+" is playing. Your cards : "+this.playerCards );
		if(playedCards.size()==0)
		{
			try
			{
			playCard(decCards,playedCards);
			}
			catch (Exception exc)
			{
				 System.err.println("You lost your turn because you didn't follow THE RULES!!! Next player please \ud83d\ude00");
			}
		}else
		{
			//Ask for the dec to choose from.
			System.out.println("Played cards: "+playedCards);
			System.out.println("\n\tPlease pick the deck you want to select from\n\t0 for Dec\n\t1 for played cards\n");
			
			Scanner scan = new Scanner(System.in);
			int dec = scan.nextInt();
			
			 switch(dec)
			 {
				 case 0:
				 try{
					playCard(decCards,playedCards);
				 }
				 catch (Exception exc)
				 {
					 System.err.println("You lost your turn because you didn't follow THE RULES!!! Next player please \ud83d\ude00");
				 }
					break;
				  case 1:
				  try {
				  System.out.println("Which card (position) form the following cards do you want  to pick?\n" + playedCards);
					Scanner scan1 = new Scanner(System.in);
					int position = scan1.nextInt();
					playCard(playedCards,playedCards,position);
				  }
				  catch (Exception exc)
				  {
					  System.err.println("You lost your turn because you didn't follow THE RULES!!! Next player please \ud83d\ude00");
				  }
					break;
				  default:
				  try{
				   playCard(decCards,playedCards);
				  }
				  catch (Exception exc)
				  {
					System.err.println("You lost your turn because you didn't follow THE RULES!!! Next player please \ud83d\ude00");
				  }
					break;
			 }
		}
		
	}
	public void playCard(ArrayList<String> chosenDec,ArrayList<String> playedCards)throws Exception
	{
		playerCards.add(chosenDec.remove(0));
		System.out.println("Which card (position) form the following cards do you want to remove?\n" + playerCards);
		Scanner scan = new Scanner(System.in);
		int position = scan.nextInt();
		Random rand= new Random();
		int num= rand.nextInt(6);
		//Handle exceptions later
		if(!(position>=1 & position<=6))
		{
			playedCards.add(playerCards.remove(num));
			throw new Exception("Please enter 1-6 only.\n");
		}
		else
			playedCards.add(playerCards.remove(position-1));
	}
	//creatiion of array of played cards
	public void playCard(ArrayList<String> chosenDec,ArrayList<String> playedCards,int position1)throws Exception
	{
		playerCards.add(playedCards.remove(position1-1));
		System.out.println("Which card (position) form the following cards do you want to remove?\n" + playerCards);
		Scanner scan = new Scanner(System.in);
		int position = scan.nextInt();
		Random rand= new Random();
		int num= rand.nextInt(6);
		//Handle exceptions later
		if(!(position>=1 & position<=6))
		{
			playedCards.add(playerCards.remove(num));
			throw new Exception("Please enter 1-6 only.\n");
		}
		else
			playedCards.add(playerCards.remove(position-1));
		//Check if the player has won or not 
		System.out.println("Played cards\n" + playedCards);
	}
	
	public void showCards()
	{
		System.out.println(playerCards);
		System.out.println();
	}
	public ArrayList<String> getCards()
	{
		return this.playerCards;
	}
	
	public String getName()
	{
		return this.name;
	}
	
}