package FrontSide;

import java.io.IOException;

import Model.MovieMenu;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * 控制左侧的菜单选择栏
 * 
 * @version 2018/6/1
 * 
 * @author 马思清
 * 
 * **/
public class MenuControl {
    
    @FXML private AnchorPane mainPane;
	@FXML private AnchorPane centrePane;
	@FXML private AnchorPane leftPane;
	@FXML private Button allMovie;
	@FXML private Button settings;
	@FXML private Button nowPlaying;
	private MenuControl mcControl;
	private PlayerControl playerControl;
	private movieMenuControl sub1Control;
	private OptionControl opControl;
	private Parent mcRoot;
	private Parent opRoot;
	private Parent pcRoot;
	private Parent sbRoot;
	private String originCSS = "css\\CSS White.css";
	private String focusedCss = getClass().getResource("css\\CSS leftPaneButton.css").toExternalForm();
	private int y = 0;
	
	private EventHandler<KeyEvent> leftPaneHandler = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent key) {
			if(key.getCode() == KeyCode.UP) {
				//限制运动范围
				if(y>0) {
					y--;
				}
				showCssInButton(y);
				key.consume();
			}
			if(key.getCode() == KeyCode.DOWN) {
				//限制运动范围
				if(y<2) {
					y++;
				}
				showCssInButton(y);
				key.consume();
			}
			if(key.getCode() == KeyCode.ENTER) {
				enterSubPane();
				key.consume();
			}
			if(key.getCode() == KeyCode.RIGHT) {
            	enterSubPane();
            	key.consume();
			}
		}
	};
	
	
	public void leftPaneFocus(int y) {
		leftPane.requestFocus();
		switch(y) {
			default:{}
			case 0:{
				allMovie.getStylesheets().add(focusedCss);
				break;
			}
			case 1:{
				nowPlaying.getStylesheets().add(focusedCss);
				break;
			}
			case 2:{
				settings.getStylesheets().add(focusedCss);
				break;
			}
		}
		
	}
	
	public void setMenuControl(MenuControl mcControl) {
		this.mcControl = mcControl;
	}
	
	public void setParent(Parent mcRoot) {
	    	this.mcRoot = mcRoot;
	}
	
	@FXML
	public void initialize() {
		
		System.out.println("MENUCONTROL initialize");
		
			try {
				Parent allMovie_SUB = FXMLLoader.load(getClass().getResource("MovieMenu.fxml"));
				centrePane.getChildren().clear();
				centrePane.getChildren().addAll(allMovie_SUB);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		 
		//加载视频播放器
	        try {
	        	System.out.println("playerControl initialize");
	        	
	        	FXMLLoader fxmlLoader = new FXMLLoader();
	 	        fxmlLoader.setLocation(getClass().getResource("MediaPlay.fxml"));
	 	        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
				pcRoot = (javafx.scene.Parent) fxmlLoader.load(
						       getClass().getResource("MediaPlay.fxml").openStream());
				playerControl=(PlayerControl)fxmlLoader.getController();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" Some module of MediaPlay was missing ");
				e.printStackTrace();
			}
	        
	        
	    //加载全部影片界面
	        try {
	        	System.out.println("MovieMenu initialize");
	        	
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("MovieMenu.fxml"));
		        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		        sbRoot = (javafx.scene.Parent) fxmlLoader.load(
		                       getClass().getResource("MovieMenu.fxml").openStream());
		        sub1Control=(movieMenuControl)fxmlLoader.getController();
		      
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" Some module of Sub1 was missing ");
				e.printStackTrace();
			}
	        
	    //加载设置界面
	        try {
	        	System.out.println("optionControl initialize");
	        	
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("OptionControl.fxml"));
		        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		        opRoot = (javafx.scene.Parent) fxmlLoader.load(
		                       getClass().getResource("OptionControl.fxml").openStream());
		        opControl=(OptionControl)fxmlLoader.getController();
		        opControl.setRoot(opRoot, sbRoot, pcRoot,mcRoot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" Some module of OptionControl was missing ");
				e.printStackTrace();
			}

	        opRoot.getStylesheets().add(getClass().getResource(originCSS).toExternalForm());
			pcRoot.getStylesheets().add(getClass().getResource(originCSS).toExternalForm());
			sbRoot.getStylesheets().add(getClass().getResource(originCSS).toExternalForm());
			
			centrePane.getChildren().clear();
		    centrePane.getChildren().addAll(sbRoot);
	
		    leftPane.addEventHandler(KeyEvent.KEY_PRESSED, leftPaneHandler);
	}
	
	public void diliverControler() {
		playerControl.getBackSide().setMenuControl(mcControl);
		sub1Control.backSideStart(playerControl,sub1Control,centrePane, pcRoot,mcControl);
	}
	
	public void startKeyListening() {
		
		System.out.println("mcControl Start Listening");
		
		leftPane.addEventFilter(KeyEvent.KEY_PRESSED, (key) ->{
			
			System.out.println(key.getCode());
			if(key.getCode() == KeyCode.ENTER){
				key.consume();
			}
			
			if(key.getCode() == KeyCode.UP){
				key.consume();
			}
			
			if(key.getCode() == KeyCode.DOWN){
				key.consume();
			}
		});
		
	}
	
	//点击视频播放器
	@FXML
	public void mediaPlay() {
		    centrePane.getChildren().clear();
		    if(playerControl.getBackSide().getStatus() == 0) {
		    	playerControl.setMediaURL("movie\\China Eastern Airlines B777-300ER Safety video (English).mp4");
		    	playerControl.mediaPlay();
		    }
	        centrePane.getChildren().addAll(pcRoot);
	        playerControl.fatherPaneFocus();
	}
	
	
    //点击“全部影片”
	@FXML
	public void clickedAllMovie() {
        	centrePane.getChildren().clear();
        	centrePane.getChildren().addAll(sbRoot);
        	sub1Control.subAnchorPane1Focus();
	}
	

	//点击“个人设置”
	@FXML
	public void clickedSettings() {
			centrePane.getChildren().clear();
			opControl.setRoot(opRoot, sbRoot, pcRoot,mcRoot);
			opControl.setMovieMenu(sub1Control.getBackSide(),mcControl);
			centrePane.getChildren().addAll(opRoot);	
	}
	
   
    
    private void enterSubPane() {
		allMovie.getStylesheets().clear();
		nowPlaying.getStylesheets().clear();
		settings.getStylesheets().clear();
		switch(y) {
			default:;	
			case 0:{
				clickedAllMovie();
				break;
			}
			case 1:{
				mediaPlay();
				break;
			}
			case 2:{
				clickedSettings();
				break;
			}
	    }
	}
	
	private void showCssInButton(int y) {
		allMovie.getStylesheets().clear();
		nowPlaying.getStylesheets().clear();
		settings.getStylesheets().clear();
		switch(y) {
		    default:{}
			case 0:{
				allMovie.getStylesheets().add(focusedCss);
				break;
			}
			case 1:{
				nowPlaying.getStylesheets().add(focusedCss);
				break;
			}
			case 2:{
				settings.getStylesheets().add(focusedCss);
				break;
			}
		}
	}
	
}
