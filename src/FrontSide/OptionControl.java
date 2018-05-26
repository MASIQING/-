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
	@FXML private MenuItem setFrench;
	@FXML private Slider lightSlider;
	@FXML private ImageView lightImage;

	
	private Parent opRoot;
	private Parent sbRoot;
	private Parent pcRoot;
	private Parent mcRoot;
	private String language;
	private String cssName = "DEFAULT";
	
	
	
	
	public void setControler(Parent opRoot,
    					     Parent sbRoot,
    					     Parent pcRoot,
    					     Parent mcRoot) {
    	this.mcRoot = mcRoot;
    	this.opRoot = opRoot;
    	this.pcRoot = pcRoot;
    	this.sbRoot = sbRoot;
    }
	
	
	@FXML public void selectWhite() {
		System.out.println("1");
		selectCSS.setText("浅色主题");
		cssName = "CSS White";
	}
	@FXML public void selectBlack() {
		System.out.println("2");
		selectCSS.setText("暗夜黑");
		cssName = "CSS Black";
	}
	@FXML public void selectBlue() {
		System.out.println("3");
		selectCSS.setText("深海蓝");
		cssName = "CSS Blue";
	}
	@FXML public void selectChinese() {
		selectLanguage.setText("简体中文");
		language = "Chinese";
	}
	@FXML public void selectEnglish() {
		selectLanguage.setText("English");
		language = "English";
	}
	@FXML public void selectFrench() {
		selectLanguage.setText("Français");
		language = "Franch";
	}
	@FXML public void confirmClicked() {
		selectCSS.setText("选择主题");
		System.out.println(cssName+" CONFIRM CLICKED");	
		changeCSS();
		
	}
	
	 public void changeCSS() {
		 if(!cssName.equals("DEFAULT")) {
		    opRoot.getStylesheets().clear();
		    pcRoot.getStylesheets().clear();
		    sbRoot.getStylesheets().clear();
		    mcRoot.getStylesheets().clear();
		    
			opRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
			pcRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
			//pcRoot.getStylesheets().add(getClass().getResource("css\\"+cssName+" Play.css").toExternalForm());
			sbRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
			mcRoot.getStylesheets().addAll(getClass().getResource("css\\"+cssName+".css").toExternalForm());
		}
	 }	
}
