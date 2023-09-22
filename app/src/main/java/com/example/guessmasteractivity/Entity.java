package com.example.guessmasteractivity;

public abstract class Entity {
	private String name;
	private Date born;
	private double difficulty;
	
	// Constructor
	public Entity(String name, Date birthDate, double difficulty) {
		this.name = name;
		this.born = new Date(birthDate); //no privacy leak
		this.difficulty = difficulty;
	}
	
	// Copy constructor
	public Entity(Entity copyEntity) {
		/**
		Creates (copies) new entity based on given entity in parameter. 
		@param copyEntity	entity object to copy
		 */
		this.name = copyEntity.name;
		this.born = new Date(copyEntity.born); //no privacy leak
		this.difficulty = copyEntity.difficulty;
	}
	
	// Accessors (Getters)
	public String getName() {
		/**
		 Returns name of the current entity.
		 @return entity name
		 */
		return this.name;
	}

	public Date getBorn() {
		/**
		 Returns the birth date of the current entity.
		 @return entity date
		 */
		return new Date(this.born); //privacy leak
	}
	
	public int getAwardedTicketNumber() {
		/**
		 Returns the number of tickets to award based on entity difficulty.
		 @return points to award (integer)
		 */
		return (int)(this.difficulty * 100);
	}
	
	// Overridden toStirng() method
	public String toString() {
		return "Name: " + name + "\n" + "Born at: " + born.toString() + "\n";
	}
	
	// Abstract method headings
	public abstract String entityType();
	public abstract Entity clone();
	
	// Messages
	public String welcomeMessage() {
		/**
		 Returns a welcome message based on current entity type.
		 @return welcome message
		 */
		return "Welcome! Let's start the game! " + this.entityType();
	}
	
	public String closingMessage() {
		/**
		 Returns a closing message based with full details of current entity.
		 @return closing message
		 */
		return "Congratulations! The detailed information of the entity you guess is:\n" + this.toString();
	}
}
