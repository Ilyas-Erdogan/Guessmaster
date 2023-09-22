package com.example.guessmasteractivity;

public class Politician extends Person {
	// Instance variables
	private String party;
	
	// Primary Constructor
	public Politician(String name, Date birthDate, String gender, String party, double difficulty) {
		super(name, birthDate, gender, difficulty);
		this.party = party;
	}
	
	// Copy Constructor
	public Politician(Politician copyPolitician) {
		/**
		Creates (copies) new politician based on given politician in parameter. 
		@param copyPolitician	politician object to copy
		*/
		super(copyPolitician);
		this.party = copyPolitician.party;
	}
	
	// Accessor (Getter)
	public String getParty() {
		/**
		 Returns party information of entity.
		 @return politician's party
		 */
		return this.party;
	}
	
	// Overridden string method
	public String toString() {
		/**
		 Returns party information of politician.
		 @return party information
		 */
		return super.toString() + "Party: " + this.getParty() + "\n";
	}
	
	// Abstract method implementation
	public  String entityType() {
		/**
		 Tells user that entity is a politician.
		 @return string "This entity is a politician!"
		 */
		return "This entity is a politician!";
	}

	public Politician clone() {
		/**
		 Returns new politician without privacy leaks.
		 @return new (cloned) politician
		 */
		return new Politician(this);
	}
}
