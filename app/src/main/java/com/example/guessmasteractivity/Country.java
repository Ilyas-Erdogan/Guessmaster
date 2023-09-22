package com.example.guessmasteractivity;

public class Country extends Entity {
	private String capital;
	
	// Primary Constructor
	public Country(String name, Date birthDate, String capital, double difficulty) {
		super(name, birthDate, difficulty);
		this.capital = capital;
	}
	
	// Copy Constructor
	public Country(Country copyCountry) {
		/**
		Creates (copies) new country based on given country in parameter. 
		@param copyCountry	country object to copy
		 */
		super(copyCountry);
		this.capital = copyCountry.capital;
	}
	
	// Getter
	public String getCapital() {
		/**
		 Returns the capital of the entity's country.
		 @return country capital
		 */
		return this.capital;
	}
	
	// Overridden string method
	public String toString() {
		/**
		 Returns country information of entity 
		 @return country information
		 */
		return super.toString() + "Capital: " + this.getCapital() + "\n";
	}
	
	// Abstract method implementation
	public  String entityType() {
		/**
		 Tells user that entity is a country.
		 @return string "This entity is a country!"
		 */
		return "This entity is a country!";
	}

	public Country clone() {
		/**
		 Returns new country without privacy leaks.
		 @return new (cloned) country
		 */
		return new Country(this);
	}
}
