package FrontSide;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import Model.MovieMenu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

/**
 * 设置界面控制器<br>
 * 改变样式表和语言，进入隐藏模式
 * @version 2018/5/27
 * 
 * @author 马思清
 *
 * **/
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
	private MovieMenu movieMenub;
	private MenuControl menuControl;
	private String language = "CHINESE";
	private String cssName = "DEFAULT";
	
	private String companyName = null;
	private String companyPicture1 = null;
	private String companyPicture2 = null;
	private String companyVideo = null;
	
	private EventHandler<KeyEvent> handler = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			//返回左侧功能栏
			if(key.getCode() == KeyCode.BACK_SPACE) {
				//移动焦点至左侧功能栏并设置css
				menuControl.leftPaneFocus(2);
				key.consume();
			}
			
		}
		
	};
	
	
	/**
	 * 初始化，传入四个界面文档的parent
	 * **/
	public void setRoot(Parent opRoot,
    					     Parent sbRoot,
    					     Parent pcRoot,
    					     Parent mcRoot) {
    	this.mcRoot = mcRoot;
    	this.opRoot = opRoot;
    	this.pcRoot = pcRoot;
    	this.sbRoot = sbRoot;
    }
	/**
	 * 初始化，传入电影选择界面,和左侧功能栏的控制器
	 * **/
	public void setMovieMenu(MovieMenu movieMenub,MenuControl menuControl) {
		this.movieMenub = movieMenub;
		this.menuControl = menuControl;
	}
	
	@FXML
	public void initialize() {
		//加载Properties 配置文件
		Properties userDefault = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream("userDefault.properties");
			userDefault.load(new InputStreamReader(inputStream, "UTF-8"));
			inputStream.close();
			
			companyName = userDefault.getProperty("companyName");
			companyPicture1 = userDefault.getProperty("companyPicture1");
			companyPicture2 = userDefault.getProperty("companyPicture2");
		    companyVideo = userDefault.getProperty("companyVideo");
		    
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
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
	
	private void changeLanguage() {
		selectLanguage.setText("语言 Language");
		if(language.equals("ENGLISH")) {
			movieMenub.changeLanguage("ENGLISH");
			selectCSS.setText("Select Theme");
			selectWhite.setText("Bright");
			selectBlue.setText("Dark Blue");
			selectBlack.setText("Black");
			confirm.setText("Apply");
		}else if(language.equals("CHINESE")) {
			movieMenub.changeLanguage("CHINESE");
			selectCSS.setText("界面主题");
			selectWhite.setText("浅色主题");
			selectBlue.setText("深海蓝");
			selectBlack.setText("暗夜黑");
			confirm.setText("应用");
		}
	}
	
	private void changeCSS() {
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
