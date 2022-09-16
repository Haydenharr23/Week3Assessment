package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hayden Harris - CIS175 
 * DMACC FALL 2022
 * Sep 13, 2022
 */
@Entity
@Table(name="items")
public class ListItem {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="AUTHOR")
	private String author;
	@Column(name="TITLE")
	private String title;
	
	public ListItem(){
		super();
	}
	
	public ListItem(String a, String t) {
		super();
		this.author=a;
		this.title=t;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author=author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title=title;
	}
	
	public String returnItemDetails() {
		return this.author + ": " + this.title;
	}
}
