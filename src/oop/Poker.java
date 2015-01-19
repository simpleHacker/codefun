package oop;

import oop.Card.Type;
import oop.Card.Value;


public class Poker {
	
	private static final int Len = 52;
	private Card[] cards = new Card[Len];
	public Poker(){
		int i =0, j =0;
		for(Type c: Type.values()){	
			for(Value v: Value.values()){
				this.cards[i*13+j] = new Card(c,v);
				j++;
			}
			i++;
		}
	}
	
	public Card[] getCard(){
		return cards;
	}
	
	public Card[] shuffle(Card[] cards){
		int[] a = new int[Len];
// also can run those two loops multiple times		
		for(int i=0;i<Len;++i){
			a[i] =(int) Math.random()*Len;
			for(int j=0;j<i;++j){
				if(a[j] == a[i]){ // if duplicate, restart again
					--i;
					break;
				}
			}
		}
		Card temp;
		
		for(int i=0;i<Len;++i){
			temp = cards[i];
			cards[i] = cards[a[i]];
			cards[a[i]] = temp;
		}
		return cards;
	}
	
	public String[] dealCard(Card[] cards, int num){
		String[] person = new String[num];
		for(int i=0;i<num;++i)
			person[i] = "";
		for(int i=0;i<Len;i+=num){
			for(int j=0;j<num && i+j<Len;++j){
				person[j] += cards[i+j].showCard()+";";
			}
		}
		return person;
	}
	
	public void printDeal(String[] persons){
		for(int i=0;i<persons.length;++i){
			System.out.println("person "+i+"'s cards: "+persons[i]);
		}
	}
	
}
