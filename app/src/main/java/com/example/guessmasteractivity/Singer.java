package com.example.guessmasteractivity;

public class Singer extends Person {
	// Instance variables
	private String debutAlbum;
	private Date debutAlbumReleaseDate;
	
	// Primary Constructor
	public Singer(String name, Date birthDate, String gender, String debutAlbum, Date debutAlbumReleaseDate, double difficulty) {
		super(name, birthDate, gender, difficulty);
		this.debutAlbum = debutAlbum;
		this.debutAlbumReleaseDate = debutAlbumReleaseDate;
	}
	
	// Copy Constructor
	public Singer(Singer copySinger) {
		/**
		Creates (copies) new singer based on given singer in parameter. 
		@param copySinger	singer object to copy
		*/
		super(copySinger);
		this.debutAlbum = copySinger.debutAlbum;
		this.debutAlbumReleaseDate = copySinger.debutAlbumReleaseDate;
	}
	
	// Accessors (Getters)
	public String getDebutAlbum() {
		/**
		 Returns debut album of singer entity
		 @return debut album
		 */
		return this.debutAlbum;
	}
	
	public Date debutAlbumReleaseDate() {
		/**
		 * Returns release date of album from singer entity
		 * @return debut album release date
		 */
		return new Date(debutAlbumReleaseDate); // no privacy leak
	}
	
	// Overridden string method
	public String toString() {
		return super.toString() + "Debut Album: " + this.getDebutAlbum() + "\nRelease Date: " + this.debutAlbumReleaseDate() + "\n";
	}
	
	// Abstract method implementation
	public  String entityType() {
		/**
		 Tells user that entity is a singer.
		 @return string "This entity is a singer!"
		 */
		return "This entity is a singer!";
	}

	public Singer clone() {
		/**
		 Returns new singer without privacy leaks.
		 @return new (cloned) singer
		 */
		return new Singer(this);
	}
}
