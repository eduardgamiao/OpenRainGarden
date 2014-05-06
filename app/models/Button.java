package models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

public class Button extends Model {
	private static final long serialVersionUID = 1L;
	  
	@Id
	private Long id;
	private String blockNumber;
	private String title;
	private String href;
	
	@ManyToOne
	private IndexContent block;
	
	public Button(String b , String t, String h){
		blockNumber = b;
		title = t;
		href = h;
	}
	public Long getId() {
	    return id;
	}
    public void setId(Long id) {
	    this.id = id;
	}
	public void setNumber(String t){
		blockNumber = t;
	}
	public void setTitle(String t){
		title = t;
	}
	public void setHref(String h){
		title = h;
	}
	public String getNumber(){
		
		return blockNumber;
	}
	public String getTitle(){
		
		return title;
	}
	public String getHref(){
		return href;
	}
	/**
	   * The EBean ORM finder method for database queries on ID.
	   * @return The finder method for header.
	*/
	public static Finder<Long, Button> find() {
	    return new Finder<Long, Button>(Long.class, Button.class);
	}
}
