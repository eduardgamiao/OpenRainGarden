package models;

public class Button {
	private String blockNumber;
	private String title;
	private String href;
	public Button(String b , String t, String h){
		blockNumber = b;
		title = t;
		href = h;
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
}
