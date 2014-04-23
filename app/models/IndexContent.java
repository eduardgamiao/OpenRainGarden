package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import play.db.ebean.Model;


@Entity
public class IndexContent extends Model {
	private static final long serialVersionUID = 1L;
	  
	@Id
	private Long id;
	private String a;
	@Lob
	private String b;
	@Lob
	private String c;
	@Lob
	private String d;
	
	
	
	@OneToMany (mappedBy = "block", cascade = CascadeType.PERSIST)
	private List<Button> e = new ArrayList<Button>();
	
	
	public IndexContent(String a1, String b1, String c1, String d1, List<Button> e1){
		a=a1;
		b=b1;
		c=c1;
		d=d1;
		e=e1;
	}
	public String getSerial(){
		return a;
	}
	public String getTitle(){
		return b;
	}
	public String getContent(){
		return c;
	}
	public String getPicUrl(){
		return d;
	}
	public List<Button> getButton(){
		return e;
	}
	
}
