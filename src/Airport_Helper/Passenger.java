package Airport_Helper;

import java.io.*;
import java.util.Scanner;


public class Passenger {
	
	public static int dataFields = 6;
	protected static String username;
	protected String password;
	protected String firstName;
	protected String surName;
	protected String safetyQuestion;
	protected String answer;
	public static String fileName = "databasePassengers.txt";
	static Scanner userInput = new Scanner(System.in);
	
	Passenger()
	{
		
	}
	
	Passenger(String passengerUsername, String passengerPassword, String passengerFirstName, String passengerSurName, String passengerSafetyQuestion, String passengerAnswer)
	{
		username = passengerUsername;
		password = passengerPassword;
		firstName = passengerFirstName;
		surName = passengerSurName;
		safetyQuestion = passengerSafetyQuestion;
		answer = passengerAnswer;
		
	}
	
	public static void printWelcomeMessage()
	{
		System.out.println("Welcome to the Airport Helper©.");
		System.out.println("This unique tool will help you with finding what you need on the airport.");
		System.out.println("This program has several functionalities:");
		System.out.println("");

	}
	
	public static void beforeLoginMenu()
	{
		try
		{
			int userChoice;
			System.out.println("Do you want to login or create a new account?");
			System.out.println("Press 1 to login.");
			System.out.println("Press 2 to create a new account.");
			userChoice = Integer.parseInt(userInput.nextLine());
			
			if (userChoice == 1)
			{
				loginMenu();
			}
			else if (userChoice == 2)
			{
				Passenger p = new Passenger();
				p.createAccount();
			}
			else
			{
				System.out.println("Choose option 1 or 2 next time.");
				beforeLoginMenu();
			}
		}	
		catch (NumberFormatException e)																			//If the user input can't be parsed, this method will call itself again until the user enters an integer
		{
			System.out.println("That is not a valid input. Please enter an integer.");	
			beforeLoginMenu();
		}
	}
	
	public void createAccount()
	{						
		
		Passenger[] passengers = new Passenger[1];									
		
		for (int i = 0; i < passengers.length; i++)																 
		{
			String passengerUserName = setUsername();												
			String passengerPassword = setPassword();									
			String passengerFirstName = setFirstName();
			String passengerSurName = setSurName();
			String passengerSafetyQuestion = setSafetyQuestion();
			String passengerAnswer = setAnswer(passengerSafetyQuestion);
			
			passengers[i] = new Passenger(passengerUserName, passengerPassword, passengerFirstName, passengerSurName, passengerSafetyQuestion, passengerAnswer);		
			addNewPassenger(passengers[i]);										
		}
		
	}

	public String setUsername()
	{
		System.out.println("What do you want your username to be?");
		String newUsername = userInput.nextLine();
		
        try {

            String sCurrentLine;				        					
            String[] uCurrent =  new String[dataFields];
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            while((sCurrentLine = bufferedReader.readLine()) != null) 		
            {
                uCurrent = sCurrentLine.split(",");							
                
    			if (newUsername.equals(uCurrent[0]))	
    			{
    				System.out.println("The username '" + newUsername + "' is already taken. Please enter a different username.");
    				newUsername = userInput.nextLine();
    			}

            }   

            bufferedReader.close(); 

        	}

        catch(FileNotFoundException ex) 								//Exception handling
        {
            System.out.println("Unable to open file '" + fileName + "'");
            System.out.println("This is normal if the file hasn't been created yet.");
            System.out.println("Otherwise, '" + fileName + "' is in the wrong place.");
            
        }
        catch(IOException ex) 
        {
        	System.out.println("The program was unable to read '" + fileName + "'. Please contact the developer.");                  
        }
		
        return newUsername;
	}
	
	public String setPassword()
	{
		System.out.println("What do you want your password to be?");
		String newPassword = userInput.nextLine();
		System.out.println("Enter your password again.");
		String newPasswordCheck = userInput.nextLine();
		
		if (!newPassword.equals(newPasswordCheck))
		{
			System.out.println("The first password you entered does not match the second password.");
			System.out.println("Please try again.");
			setPassword();
		}
		
		return newPassword;
	}
	
	public String setFirstName()
	{
		System.out.println("What is your first name?");
		String newFirstName = userInput.nextLine();
		return newFirstName;
	}
	
	public String setSurName()
	{
		System.out.println("What is your surname?");
		String newSurName = userInput.nextLine();
		return newSurName;
	}
	
	public String setSafetyQuestion()
	{
		String newSafetyQuestion = null;
		int userChoice;
		
		try
		{

			
			System.out.println("What do you want your safety question to be?");
			System.out.println("1. What is the name of your first pet?");
			System.out.println("2. What is your favourite food?");
			System.out.println("3. What is the name of your favorite professor?");
			System.out.println("Press 1, 2 or 3 to pick a default safety question.");
			System.out.println("Press 0 to enter your own safety question.");
			userChoice = Integer.parseInt(userInput.nextLine());
			
			if (userChoice == 0)
			{
				System.out.println("Please enter your own safety question.");
				newSafetyQuestion = userInput.nextLine();
			}
			else if (userChoice == 1)
			{
				newSafetyQuestion = "What is the name of your first pet?";
			}
			else if (userChoice == 2)
			{
				newSafetyQuestion = "What is your favourite food?";
			}
			else if (userChoice == 3)
			{
				newSafetyQuestion = "What is the name of your favorite professor?";
			}
			else
			{
				System.out.println("Choose option 0, 1, 2 or 3 next time.");
				setSafetyQuestion();
			}
		}
		catch (NumberFormatException e)																			//If the user input can't be parsed, this method will call itself again until the user enters an integer
		{
			System.out.println("That is not a valid input. Please enter an integer.");	
			setSafetyQuestion();
		}
		
		return newSafetyQuestion;
	}

	public String setAnswer(String newSafetyQuestion)
	{
		System.out.println("Please answer your new safety question.");
		System.out.println("You will need this answer if you forget your username or password.");
		System.out.println("Your safety question: " + newSafetyQuestion);
		String newAnswer = userInput.nextLine();
		System.out.println("Your answer is : '" + newAnswer + "'. Make sure to hold on to your new answer.");
		return newAnswer;
	}
	
	public void addNewPassenger(Passenger passengers)
	{
		try
		{		
		PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))); 	
		pr.print(passengers.username);
		pr.print(",");
		pr.print(passengers.password);
		pr.print(",");
		pr.print(passengers.firstName);
		pr.print(",");
		pr.print(passengers.surName);
		pr.print(",");
		pr.print(passengers.safetyQuestion);
		pr.print(",");
		pr.print(passengers.answer);
		pr.println("");
		pr.close();
		}
		catch (IOException e)				//If the file doesn't exist, this message prompts.
		{
			System.out.println("The file doesn't exist");
		}
	}

	public String getUsername(String pass)
	{
		String user;
		if (pass.equals(password))
		{
			user = username;
		}
		else
		{
			user = "Enter correct password to obtain username";
		}
		return user;
	}
	
	public String getPassword(String user)
	{
		String pass;
		if (user.equals(username))
		{
			pass = password;
		}
		else
		{
			pass = "Enter correct username to obtain password";
		}
		return pass;
	}

	public void setUsername(String pass)
	{
		
		try 
		{

            String sCurrentLine;				        					
            String[] uCurrent =  new String[dataFields];
            int count = 0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
			System.out.println("What do you want your new username to be?");
			String newUsername = userInput.nextLine();
			
            while((sCurrentLine = bufferedReader.readLine()) != null) 		
            {
                uCurrent = sCurrentLine.split(",");							
                
    			if (newUsername.equals(uCurrent[0]))	
    			{
    				count++;
    			}

            }   

            bufferedReader.close(); 
			if (count == 0)
			{
				System.out.println("Enter your password to confirm.");
				String password = userInput.nextLine();
				
				if (pass.equals(password))
				{
					System.out.println("Your username has been updated in the system.");
					username = newUsername;
				}
				
				else
				{
					System.out.println("Wrong password. Your username has not been updated.");
				}
			}
			else if (count > 0)
			{
				System.out.println("The username '" + newUsername + "' is already taken.");
        	}
		}
        catch(FileNotFoundException ex) 								//Exception handling
        {
            System.out.println("Unable to open file '" + fileName + "'. Please contact the developer.");                
        }
        catch(IOException ex) 
        {
        	System.out.println("The program was unable to read '" + fileName + "'. Please contact the developer.");                  
        }
        
	}

	public void setPassword(String user, String newPassword)
	{
		if (user.equals(username))
		{
			password = newPassword;
		}
		else
		{
			System.out.println("Enter the right username to modify password.");
		}
		
	}
	
	public void setFirstName(String pass)
	{
		System.out.println("What is your first name?");
		String newFirstName = userInput.nextLine();
		System.out.println("Enter your password to confirm.");
		String password = userInput.nextLine();
		
		if (pass.equals(password))
		{
			System.out.println("Your first name has been updated in the system.");
			firstName = newFirstName;
		}
		
		else
		{
			System.out.println("Wrong password. Your first name has not been updated.");
		}
	}
	
	public void setSurName(String pass)
	{
		System.out.println("What is your surname?");
		String newSurName = userInput.nextLine();
		System.out.println("Enter your password to confirm.");
		String password = userInput.nextLine();
		
		if (pass.equals(password))
		{
			System.out.println("Your surname has been updated in the system.");
			surName = newSurName;
		}
		
		else
		{
			System.out.println("Wrong password. Your surname has not been updated.");
		}
	}

	public static void loginMenu()
	{
		System.out.println("Welcome to the login menu.");
		System.out.println("Please enter your username.");
		String username = userInput.nextLine();
		System.out.println("Please enter your password.");
		String password = userInput.nextLine();
		

        try 
        {

            String sCurrentLine;				        					
            String[] uCurrent =  new String[dataFields];
            int count = 0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            

            while((sCurrentLine = bufferedReader.readLine()) != null) 		
            {
                uCurrent = sCurrentLine.split(",");							
                
    			if (username.equals(uCurrent[0]) && password.equals(uCurrent[1]))	
    			{
    				count++;
    			}
            }   

            bufferedReader.close(); 
        	if (count == 0)
        	{
        		failedLoginMenu();
        	}
        	else if (count > 0)
        	{
        		afterLoginMenu();
        	}
        	
        }
        
        catch(FileNotFoundException ex) 								//Exception handling
        {
            System.out.println("Unable to open file '" + fileName + "'");
            System.out.println("This is normal if the file hasn't been created yet.");
            System.out.println("Otherwise, '" + fileName + "' is in the wrong place.");
            
        }
        catch(IOException ex) 
        {
        	System.out.println("The program was unable to read '" + fileName + "'. Please contact the developer.");                  
        }
	}
	
	public static void failedLoginMenu()
	{
		try
		{
			int userChoice;
			System.out.println("The username/password combination is not correct.");
	        System.out.println("What do you want to do next?");
	        System.out.println("1. Create a new account");
	        System.out.println("2. I forgot my password");
	        System.out.println("Choose 1 or 2.");
	        System.out.println("Press 0 to exit Airport Helper©.");
	        userChoice = Integer.parseInt(userInput.nextLine());
        
			if (userChoice == 0)
			{
				System.exit(0);
			}
			else if (userChoice == 1)
			{
				Passenger p = new Passenger();
				p.createAccount();
			}
			else if (userChoice == 2)
			{
				passwordRetrieval();
			}
			else
			{
				System.out.println("Choose option 0, 1 or 2 next time.");
				failedLoginMenu();
			}
			
		}
		catch (NumberFormatException e)																			//If the user input can't be parsed, this method will call itself again until the user enters an integer
		{
			System.out.println("That is not a valid input. Please enter an integer.");	
			failedLoginMenu();
		}
	}
	
	public static void passwordRetrieval()
	{
		String personalUsername;
		String personalPassword;
		String personalSafetyQuestion;
		String personalAnswer;
		System.out.println("Please enter your username.");
		String username = userInput.nextLine();

		

        try 
        {

            String sCurrentLine;				        					
            String[] uCurrent =  new String[dataFields];
            int count = 0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            

            while((sCurrentLine = bufferedReader.readLine()) != null) 		
            {
                uCurrent = sCurrentLine.split(",");							
                
    			if (username.equals(uCurrent[0]))	
    			{    				
    				count++;
    				System.out.println("Username found.");
    				System.out.println("Your safety question is: " + uCurrent[4]);
    				personalUsername = uCurrent[0];
    				personalPassword = uCurrent[1];
    				personalSafetyQuestion = uCurrent[4];
    				personalAnswer = uCurrent[5];
    				answerSafetyQuestion(personalUsername, personalPassword, personalSafetyQuestion, personalAnswer);
    			}
            }   

            bufferedReader.close(); 
        	if (count == 0)
        	{
				System.out.println("Username not found.");
        	}
        	
        }
        
        catch(FileNotFoundException ex) 								//Exception handling
        {
            System.out.println("Unable to open file '" + fileName + "'");
            System.out.println("This is normal if the file hasn't been created yet.");
            System.out.println("Otherwise, '" + fileName + "' is in the wrong place.");
            
        }
        catch(IOException ex) 
        {
        	System.out.println("The program was unable to read '" + fileName + "'. Please contact the developer.");                  
        }
	}
	
	public static void answerSafetyQuestion(String personalUsername, String personalPassword, String personalSafetyQuestion, String personalAnswer)
	{
		System.out.println("Please answer your safety question : " + personalSafetyQuestion);
		String tempAnswer = userInput.nextLine();
		
		if (tempAnswer.equals(personalAnswer))
		{
			System.out.println("That is the right answer!");
			System.out.println("Username: " + personalUsername);
			System.out.println("Password: " + personalPassword);
		}
		else
		{
			System.out.println("That is not the right answer!");
			System.out.println("Please try again.");
			
		}
	}
	

	
	public static void afterLoginMenu()
	{
		try
		{
			int userChoice;
			System.out.println("You have succesfully logged in!");	
			System.out.println("What do you want to do next?");	
			System.out.println("1. Retrieve information regarding flights, airports and available facilities.");	
			System.out.println("2. View and edit personal account details.");
			System.out.println("Press 1 or 2 to go to the next menu.");
			System.out.println("Press 0 to exit Airport Helper©.");
			userChoice = Integer.parseInt(userInput.nextLine());
			
			if (userChoice == 0)
			{
				System.exit(0);
			}
			else if (userChoice == 1)
			{
				retrieveInformation();
			}
			else if (userChoice == 2)
			{
				editAccount();
			}
			else
			{
				System.out.println("Choose option 1 or 2 next time.");
				afterLoginMenu();
			}
			
		}
		catch (NumberFormatException e)																			//If the user input can't be parsed, this method will call itself again until the user enters an integer
		{
			System.out.println("That is not a valid input. Please enter an integer.");	
			afterLoginMenu();
		}
	}
	
	public static void editAccount()
	{
		try
		{
			int userChoice;
			System.out.println("Enter editAccount");	
			System.out.println("");	
			System.out.println("");	
			userChoice = Integer.parseInt(userInput.nextLine());

			if (userChoice == 0)
			{
				System.exit(0);
			}
		}
		catch (NumberFormatException e)																			//If the user input can't be parsed, this method will call itself again until the user enters an integer
		{
			System.out.println("That is not a valid input. Please enter an integer.");	
			editAccount();
		}
	}
	
	
	public static void retrieveInformation()
	{
		try
		{
			int userChoice;
			System.out.println("What do you want to do next?");	
			System.out.println("1. Retrieve all booked flights");	
			System.out.println("2. View details of a specific flight");	
			userChoice = Integer.parseInt(userInput.nextLine());

			if (userChoice == 0)
			{
				System.exit(0);
			}
			else if (userChoice == 1)
			{
				getFlightNumber(Passenger.username);
			}
			else if (userChoice == 2)
			{
				viewFlightNumber();
			}
			
		}
		catch (NumberFormatException e)																			//If the user input can't be parsed, this method will call itself again until the user enters an integer
		{
			System.out.println("That is not a valid input. Please enter an integer.");	
			afterLoginMenu();
		}
	}
	
	public static void getFlightNumber(String username)
	{
		int count;
		for (int i = 0; i < date.length; i++)
			if (username.equals(date[i].passenger))
			{
				System.out.println(date.flightNumber[i] + ": " + date.date[i]);
				count++;
			}
		
		if (count == 0)
		{
			System.out.println("You currently haven't booked any flights.");
		}
		
		
	}
	
	public static void viewFlightNumber()
	{
		int count;
		System.out.println("Enter your flight number");
		String flightNumber = userInput.nextLine();
		for (int i = 0; i < flightNumber.length; i++)
			if (flightNumber.equals(flightNumber[i].flightNumber))
			{
				System.out.println(flightNumber[i].flightNumber);
				System.out.println(flightNumber[i].flightDeptTime);
				System.out.println(flightNumber[i].flightArrivalTime);
				System.out.println(flightNumber[i].flightTerminal);	
				count++;
			}
		
		if (count == 0)
		{
			System.out.println("The flight number wasn't found in the system.");
		}
		
	}
	
	public static void main(String[] args)
	{
		printWelcomeMessage();
		beforeLoginMenu();
		

	}
}
