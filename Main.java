import java.util.*;
public class Main
{
	public static void main( String[] args)
	{
		//Main code here
		//Creates a deck of cards and shuffle it
		System.out.println("*******Welcome to 5 Krazy \uD83D\uDE09 Cards **********\n\nHere are the following rules to win the GAME:\n1. If you have an Ace or 10 with 4 similar ranks\n2. If you have an Ace or 10 with 2 similar ranks and 2 similar ranks\n3. If have 3 similar ranks and 2 similar ranks ");
		
		System.out.println("\n**********Let's make cheesyyy you are not allowed to quit the game because we AIN'T LOSERS.**************\nIf YOU DON'T ENTER THE POSITION FROM 1-6 WHEN WE REQUEST YOU LOSE YOUR TURN SWEETHEART\n\n");
		System.out.println("MAY THE BEST PERSON WIN!!!!!!");
		DeckCards cards = new DeckCards();
		cards.createCards();
		cards.shuffle();
		ArrayList<String> deckCards = cards.getCards();
		ArrayList<String> playedCards = new ArrayList<>();
		//System.out.println(deckCards);
		
		//ASKING FOR PLAYER NAMES
		System.out.println("Player 1 plase enter your name.");
		Scanner myObj= new Scanner(System.in);
		String Player1= myObj.nextLine();
		System.out.println("Player 2 plase enter your name.");
		String Player2= myObj.nextLine();
		
		//Create players and give them cards
		Player player1 = new Player(takeFive(deckCards),Player1);
		Player player2 = new Player(takeFive(deckCards),Player2);
		
		
		
		
		System.out.println();
		 player1.pickCard(deckCards,playedCards);
		boolean  keepOn = true;
		while(keepOn)
		{   
	       
			if(checkWin(player1.getCards())==true)
			{
				System.out.println(player1.getName()+" has won");
				keepOn =false;
			}else
			{
				player2.pickCard(deckCards,playedCards);
				if(checkWin(player2.getCards())==true)
				{
					System.out.println(player2.getName()+" has won");
					keepOn =false;
				}else
				{    
			        player1.pickCard(deckCards,playedCards);
					keepOn = true;
				}
				
			}
		}
		
		
		//System.out.println(player1.checkWin());
		//Todo 2 create two players
		// Start the game 
		// Share cards 
		
		// Player 2 picks and plays (check if the player wins)
		// If not another round begins 
		
	}
	private static ArrayList<String> takeFive(ArrayList<String> deck)
	{
		ArrayList<String> temp = new ArrayList<>();
		for(int i=0; i<5;i++)
		{
			temp.add(deck.remove(0));
		}
		return temp;
	}
	public static boolean checkWin(ArrayList<String> playerCards)
	{ 
	   
	  	ArrayList<String> uniqueCards =  getUnique(playerCards);
		//System.out.println(uniqueCards);
		if(uniqueCards.contains("A")||uniqueCards.contains("10"))
		{
			if(uniqueCards.size()==2) //[A,2,2,2,2]
				return true;
		
			if(uniqueCards.size()==3){
				if(conditionTwo(uniqueCards, formatCards(playerCards)))//[A,2,2,3,3]
				{
					return true;
				}
			}
			
		}else{
			if(conditionThree(uniqueCards,formatCards(playerCards)))
				return true;
		}
		
		return false;
	}
	

	public static boolean conditionTwo(ArrayList<String> uniqueCards, ArrayList<String> playerCards)
	{   String a ="A", b="10";
		ArrayList<String> swappedCards= null;
		
		if(uniqueCards.contains(a)&uniqueCards.contains(b))
		{
			//Check if we have exactly 1 A
			if(Collections.frequency(uniqueCards, a)==1)
			{
				swappedCards=swap(uniqueCards, a);
			}else
			{
				swappedCards= swap(uniqueCards, b);
			}
		}else
		{ //Check if it is an A otherwise swap with a ten.
			if(uniqueCards.contains(a))
			{
				swappedCards= swap(uniqueCards, a);
			}else
			{
				swappedCards= swap(uniqueCards, b);
			}
		}
		
		
		
		int count1=Collections.frequency(playerCards,swappedCards.get(0));
		int count2= Collections.frequency(playerCards,swappedCards.get(1));
		
		if(count1==1&count2==2)
		{
			return true;
		}
		return false;
	}
	
	public static boolean conditionThree(ArrayList<String> uniqueCards,ArrayList<String> playerCards)
	{   
		if(uniqueCards.size()==2 &
		(Collections.frequency(playerCards,uniqueCards.get(0))==3
		||Collections.frequency(playerCards,uniqueCards.get(0))==2))
		{return true;
		}
		return false;
	}
	public static ArrayList<String>  swap(ArrayList<String> check, String str)
	{
		Collections.swap(check,0,check.indexOf(str));
		return check;
	}
	// Returns an array with unique cards e.g [A ,3 ,5]
	public static ArrayList<String> getUnique(ArrayList<String> playerCards)
	{
		//Define your condition of winning 
		ArrayList<String> check= new ArrayList<>();
		//Remove the suits first
		for(String card: playerCards)
		{
			String find ="";
			if(card.length()==3)
			{
				find =card.substring(0,2);
			}else
			{
				find =card.substring(0,1);
			}
			
			//Check if the string contains find
			if(!check.contains(find))
			{
				check.add(find);
			}
		}
		return check;
	}
	public static ArrayList<String> formatCards(ArrayList<String> playerCards)
	{
		ArrayList<String> check= new ArrayList<>();
		//Remove the suits first
		for(String card: playerCards)
		{
			if(card.length()==3)
			{
				check.add(card.substring(0,2));
			}else
			{
				check.add(card.substring(0,1));
			}
			
		}
		return check;
	}
}