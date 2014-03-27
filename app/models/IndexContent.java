package models;

public class IndexContent {
	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	
	public IndexContent(String a1, String b1, String c1, String d1, String e1){
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
	public String getButton(){
		return e;
	}
	
}
