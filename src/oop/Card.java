package oop;

public class Card {

	enum Type{
		Spade, Diamond, Heart, Club;
	}
	
	enum Value{
		Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), J(11), Q(12), K(13);
		private int cardvalue;
		private Value(int value){
			this.cardvalue = value;
		}
		public int getValue(){
			return this.cardvalue;
		}
	}
	
	Type card_type;
	Value card_value;
	public Card(Type type,Value value){
		this.card_type = type;
		this.card_value = value;
	}
	
	public Type getType(){
		return this.card_type;
	}
	
	public Value getValue(){
		return this.card_value;
	}
	
	public String showCard(){
		return this.card_type.name()+"-"+ this.card_value;
	}
	
}
