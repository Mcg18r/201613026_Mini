package Styles;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class button {
	private String darkBlue ="-fx-background-color: \r\n"+
			" #090a0c,linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),linear-gradient(#20262b, #191d22)," + 
			"radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\r\n"+
			"-fx-background-radius: 5,4,3,5;" + 
			"-fx-background-insets: 0,1,2,0;" + 
			"-fx-text-fill: white;" + 
			"-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );" + 
			"-fx-font-family: \"Arial\";" + 
			"-fx-text-fill: linear-gradient(white, #d0d0d0);" + 
			"-fx-font-size: 22px;\r\n"+
			"-fx-padding: 10 20 10 20;\r\n"+
			"hover:-fx-background-color= #101010;\r\n";
	
	private String dHover =
			"-fx-background-radius: 5,4,3,5;\r\n"+ 
			"-fx-background-insets: 0,1,2,0;\r\n"+
			"-fx-text-fill: white;\r\n"+ 
			"-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\r\n"+
			"-fx-font-family: \"Arial\";\r\n"+ 
			"-fx-text-fill: linear-gradient(black, #d0d0d0);\r\n"+ 
			"-fx-font-size: 30px;\r\n"+ 
			"-fx-font-weight: bold;\r\n"+ 
			"-fx-padding: 10 20 10 20;\r\n"+
			"hover:-fx-background-color= #101010;";
	
	public  button()
	{
		
	}
	
	public  Button btnSet (Button btnType, String btnName,String btnMessage)
	{
		btnType = new Button(btnName);
		btnType.setPrefHeight(100);

		btnType.setTooltip(new Tooltip(btnMessage));
		btnType.setPrefWidth(Double.MAX_VALUE);
		btnType.setStyle(darkBlue);
		setHover(btnType);
		setHoverOff(btnType);
		
		return btnType;
	}
	public  Button btnSet (Button btnType, String btnName)
	{
		btnType = new Button(btnName);
		btnType.setPrefHeight(100);
		btnType.setPrefWidth(Double.MAX_VALUE);
		btnType.setStyle(darkBlue);
		setHover(btnType);
		setHoverOff(btnType);
		
		return btnType;
	}
	public void setHover(Button btnType)
	{
		
		
		btnType.setOnMouseEntered(e->{
			
			btnType.setStyle(dHover);
		});
	}
	public void setHoverOff(Button btnType)
		{		
			btnType.setOnMouseExited(e->
			{
				
				btnType.setStyle(darkBlue);
			});
		}
		
	

}
