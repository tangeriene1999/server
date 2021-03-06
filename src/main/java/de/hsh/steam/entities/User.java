package de.hsh.steam.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class User
 */
public class User implements Serializable{

	private static final long serialVersionUID = -3545765962123273389L;
	
	// username ist eindeutig; darf nicht geändert werden
	private String username=""; 

	// password darf nicht geändert werden
	private String password=""; 
	
	private ArrayList<Rating> myRatings = new ArrayList<Rating>();

	/**
	 * Constructor
	 * @param name
	 * @param password
	 */
	public User(String name, String password) {
		this.username = name;
		this.password = password;
	}
	
	
	/** 
	 * @param username
	 * @param password
	 * @return Boolean
	 */
	public Boolean login(String username, String password) {
		return this.username == username && this.password == password;
	}
	
	
	/** 
	 * @param series
	 * @param score
	 * @param remark
	 */
	// setzt neues Rating oder modifiziert vorhandenes Rating
	public void rate(Series series, Score score, String remark) {
		for (Rating r: myRatings) {
			if ( r.getRatedSeries().equals(series) ) {
				r.setScore(score);
				r.setRemark(remark);
                                return;
			}
		}
		Rating r = new Rating(score, remark, this, series);
		myRatings.add(r);
	}
	
	
	
	/** 
	 * @param s
	 * @return Rating
	 */
	public Rating ratingOf(Series s) {
		for (Rating r: myRatings) {
			if (r.getRatedSeries() == s)
				return r;
		}
		return null;
	}

	
	/** 
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
	
	
	/** 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	
	/** 
	 * @return String
	 */
	public String toString() {
		return this.username + " " + this.password;
	}
	
	
	/** 
	 * @param o
	 * @return boolean
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		User u = (User) o;
		return this.username == u.username;
	}
}
