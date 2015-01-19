package oop;

/**
 * parking lot class OO design
 * @author AI
 *
 *The core object: ParkingLot, Lot, Car, Meter
 *Logic: ParkingLot has number of Lots;
 *each Lot has type, and status, parked with some Car;
 *Also ParkingLot has free one, can be default; 
 *Metered one, need meter to get fare according to parking time, also with checker funtion
 *car has car information, such as types, plate, owner, and so on
 */

class Lot{
	public ParkingType type;
	public Car car;
	public boolean isVacent;
	public int ID;
	
	public long start,end;
	
	public void setOccupy(){
		start = System.currentTimeMillis();
		isVacent = false;
	}
	
	public void setVacent(){
		end = System.currentTimeMillis();
		isVacent = true;
	}
}

class Car{
	String plate;
	ParkingType type;
}

enum ParkingType{
	Regular,
	Handicapped,
	Compact;
}

public class ParkingLot {

	private int N;
	private boolean[] spareLots = new boolean[N];
	private Lot[] lots = new Lot[N];
	
	public ParkingLot(){
		for(int i=0;i<N;++i){
			spareLots[i] = true;
		// set the types for each Lot
			initiate(lots[i]);
		}
	}
	
	public Lot getLot(ParkingType type, Car car){
		check spareLots, and return Lot
	}
	
	public void freeLot(Lot t){
		spareLots[t.ID] = false;
		clear(t);
	}
}

class MeterParkingLot extends ParkingLot{
	private Meter[] meters = new Meter[N];
	
	@Override
	public boolean getLot(ParkingType type, Car car){
		
	}
	
	// return a note to tell time left or over
	public String free(Lot t){
		
	}
}

