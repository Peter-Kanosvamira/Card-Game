import java.util.*;
import java.lang.String;
public class DeckCards
{
	private ArrayList<String> deckCards;
	public DeckCards(){
		deckCards = new ArrayList<String>();
		
	}
	
	public void createCards()
	{
		//String[] shuffledCards= new String[52];
		Random myRand= new Random();
		String[] suits={ "\u2665", "\u2666", "\u2663", "\u2660"};
		String[] ranks={"A","2","3","4","5","6","7","8","9","10","K","J","Q"};
		
		int num=0;
		for (int i=0; i<suits.length;i++)
		{
			
			for(int k=0;k<ranks.length;k++)
			{
				deckCards.add(num,ranks[k]+suits[i]);
				//deck[num]=suits[i]+ranks[k];
				num+=1;
			}
		}
		
	}
	public void shuffle()
	{
		Random myRand= new Random();
		int randNum=0;
		int[] positions= new int[52];
		for (int i=deckCards.size()-1;i>0;i--)
		{
			randNum= myRand.nextInt(deckCards.size());
			Collections.swap(deckCards, i, randNum);
			positions[i]+=randNum;
		}
		if(shannonAntro(positions)>4.5){
			return;
		}else{
		 shuffle();
		}
		System.out.println();
	}
	private double shannonAntro(int[] pos)
	{
		int[] probBins= new int[52];
		double shannon =0.0;
		
		for(int i=0; i<pos.length;i++)
		{
			int posMoved= pos[i]-pos[(i+1)%52];
			if(posMoved<0)
			{
				posMoved= posMoved+52;
				probBins[posMoved]++;
			}
			probBins[posMoved]++;
		}
		for (int k=0;k<probBins.length-1;k++)
		{
			double p= (double)probBins[k]/52.0;
			if(p>0.0)
			{
				shannon-=p*Math.log(p)/Math.log(2);
			}
		}
		return shannon;
	}		
		
	public ArrayList<String> getCards()
	{
		return this.deckCards;
	}
	// Method to remove a card
		
	
}