package FrontSide;


import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class OptionControl {
	@FXML private SplitMenuButton selectCSS;
	@FXML private SplitMenuButton selectLanguage;
	@FXML private AnchorPane optionPane;
	@FXML private Button confirm;
	@FXML private MenuItem selectBlack;
	@FXML private MenuItem selectBlue;
	@FXML private MenuItem selectWhite;
	@FXML private MenuItem setChinese;
	@FXML private MenuItem setEnglish;
	@FXML private Slider lightSlider;
	@FXML private ImageView lightImage;

	private Parent opRoot;
	private Parent sbRoot;
	private Parent pcRoot;
	private Parent mcRoot;
	
	private Sub1Control sbControl;
	private String language = "CHINESE";
	private String cssName = "DEFAULT";
	
	
	
	
	public void setRoot(Parent opRoot,
    					     Parent sbRoot,
    					     Parent pcRoot,
    					     Parent mcRoot) {
    	this.mcRoot = mcRoot;
    	this.opRoot = opRoot;
    	this.pcRoot = pcRoot;
    	this.sbRoot = sbRoot;
    }
	
	public void setControler(Sub1Control sbControl) {
		this.sbControl = sbControl;
	}
	
	
	@FXML public void selectWhite() {
		System.out.println("1");
		if(language.equals("CHINESE")) {
			selectCSS.setText("浅色主题");
		}
		if(language.equals("ENGLISH")) {
			selectCSS.setText("Bright");
		}
		cssName = "CSS White";
	}
	@FXML public void selectBlack() {
		System.out.println("2");
		if(language.equals("CHINESE")) {
			selectCSS.setText("暗夜黑");
		}
		if(language.equals("ENGLISH")) {
			selectCSS.setText("Dark");
		}
		cssName = "CSS Black";
	}
	@FXML public void selectBlue() {
		System.out.println("3");
		if(language.equals("CHINESE"))
			selectCSS.setText("深海蓝");
		if(language.equals("ENGLISH"))
			selectCSS.setText("Dark Blue");
		cssName = "CSS Blue";
	}
	@FXML public void selectChinese() {
		selectLanguage.setText("简体中文");
		language = "CHINESE";
	}
	@FXML public void selectEnglish() {
		selectLanguage.setText("English");
		language = "ENGLISH";
	}
	
	@FXML public void confirmClicked() {
		selectCSS.setText("界面主题");
		System.out.println(cssName+" "+language+" CONFIRM CLICKED");	
		changeCSS();
		changeLanguage();
		
	}
	
	public void changeLanguage() {
		selectLanguage.setText("语言 Language");
		if(language.equals("ENGLISH")) {
			sbControl.changeLanguage("ENGLISH");
			selectCSS.setText("Select Theme");
			selectWhite.setText("Bright");
			selectBlue.setText("Dark Blue");
			selectBlack.setText("Black");
			confirm.setText("Apply");
		}else if(language.equals("CHINESE")) {
			sbControl.changeLanguage("CHINESE");
			selectCSS.setText("界面主题");
			selectWhite.setText("浅色主题");
			selectBlue.setText("深海蓝");
			selectBlack.setText("暗夜黑");
			confirm.setText("应用");
		}
	}
	
	public void changeCSS() {
		 if(!cssName.equals("DEFAULT")) {
		    opRoot.getStylesheets().clear();
		    pcRoot.getStylesheets().clear();
		    sbRoot.getStylesheets().clear();
		    mcRoot.getStylesheets().clear();
		    
			opRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
			pcRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
			sbRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
			mcRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
		}
	 }	
}
