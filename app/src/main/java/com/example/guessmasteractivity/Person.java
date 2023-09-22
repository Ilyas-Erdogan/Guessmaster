
package com.example.guessmasteractivity;

public class Person extends Entity {
	// Instance variables
	private String gender;
	
	// Primary Constructor
	public Person(String name, Date birthDate, String gender, double difficulty) {
		super(name, birthDate, difficulty);
		this.gender = gender;
	}
	
	// Copy Constructor
	public Person(Person copyPerson) {
		/**
		Creates (copies) new person based on given person in parameter. 
		@param copyPerson	person object to copy
		 */
		super(copyPerson);
		this.gender = copyPerson.getGender();
	}
	
	// Accessor (Getter)
	public String getGender() {
		/**
		 Returns gender of entity.
		 @return entity gender
		 */
		return this.gender;
	}
	
	// Overridden string method
	public String toString() {
		/**
		 Returns gender information of entity.
		 @return person information
		 */
		return super.toString() + "Gender: " + this.getGender() + "\n";
	}
	
	// Abstract method implementation
	public  String entityType() {
		/**
		 Tells user that entity is a person.
		 @return string "This entity is a person!"
		 */
		return "This entity is a person!";
	}

	public Person clone() {
		/**
		 Returns new person without privacy leaks.
		 @return new (cloned) person
		 */
		return new Person(this);
	}
}
