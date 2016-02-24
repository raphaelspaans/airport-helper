package airport;

public class Airport {

	public String name;
	public String location;
	public String shortcut; // the shortcut code for an airport. e.g. AMS for amsterdam
	public String checkin;     // the area where passengers can check in for their flights. Needs to be connected to flight. 
	public int Passengers;         // the program must calculate the amount of passengers - per airport.
	
	Airport (String Airportname, String AirportLocation, String AirportShortCut, String CheckInDesk, int PassengerCount)
	{
		// object airport constructs 
		name = Airportname;
		location = AirportLocation;
		shortcut = AirportShortCut;
		checkin = CheckInDesk;
		Passengers = PassengerCount; 
	}
		

	public static void countUsagePerPassenger (Passenger passenger)   //void for now to not get an error // kan ook wat anders zijn
	{
	
	   name(); 
		
		
		
	}

	public static void name() {
		// from passenger class
		if(passengerAirport.equals(name))   //requires an array of airports. 
		{
			Passengers ++; 
		}
			
		
		
		
		//return count;  
		
		
	}
	

