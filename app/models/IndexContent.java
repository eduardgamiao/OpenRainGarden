package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;


@Entity
public class IndexContent extends Model {
	private static final long serialVersionUID = 1L;
	  
	@Id
	private Long id;
	private String serial;
	@Lob
	private String title;
	@Lob
	private String content;
	@Lob
	private String image;
	
	
	
	@OneToMany (mappedBy = "block", cascade = CascadeType.PERSIST)
	private List<Button> buttons = new ArrayList<Button>();
	
	
	public IndexContent(String a1, String b1, String c1, String d1){
		this.serial=a1;
		this.title=b1;
		this.content=c1;
		this.image=d1;

	}
	public void addButtons(){
	
	}
	public Long getId() {
	    return id;
	}
    public void setId(Long id) {
	    this.id = id;
	}
	public String getSerial(){
		return serial;
	}
	public void setSerial(String s){
		this.serial=s;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String s){
		this.title=s;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String s){
		this.content=s;
	}
	public String getPicUrl(){
		return image;
	}
	public void setImage(String s){
		this.image=s;
	}
	public List<Button> getButton(){
		return buttons;
	}
	/**
	   * The EBean ORM finder method for database queries on ID.
	   * @return The finder method for header.
	*/
	public static Finder<Long, IndexContent> find() {
	    return new Finder<Long, IndexContent>(Long.class, IndexContent.class);
	}
}
