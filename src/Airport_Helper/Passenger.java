package Airport_Helper;

public class Passenger {
	
	private String username;
	private String password;
	private String firstName;
	private String surName;

	
	Passenger(String firstname, String surname)
	{
		username = passengerUsername;
		password = passengerPassword;
		firstName = passengerFirstName;
		surName = passengerSurName;
	}
	
	User(String Username, String Password)
	{
		username = Username;
		password = Password;
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
	
	public void setUsername(String pass, String newUsername)
	{
		if (pass.equals(password))
		{
			username = newUsername;
		}
		else
		{
			System.out.println("Enter the right password to modify username.");
		}
	}
	
}
