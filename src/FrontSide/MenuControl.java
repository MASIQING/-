package FrontSide;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;

public class MenuControl {
    

	//����ΪJavaFx��Ԫ�ص�fxid
	
	//������ (FXML����)
    @FXML private AnchorPane mainPane;
	@FXML private AnchorPane centrePane;
	@FXML private AnchorPane leftPane;
	@FXML private Button But_AllMovie;
	@FXML private Button But_settings;
	
	private PlayerControl playerControl;
	private Sub1Control sub1Control;
	private OptionControl opControl;
	private Parent mcRoot;
	private Parent opRoot;
	private Parent pcRoot;
	private Parent sbRoot;
	private String originCSS = "css\\CSS White.css";
	
	@FXML
	public void initialize() {
		System.out.println("Initial");
		 
			try {
				Parent allMovie_SUB = FXMLLoader.load(getClass().getResource("Sub1.fxml"));
				centrePane.getChildren().clear();
				centrePane.getChildren().addAll(allMovie_SUB);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
		 
		//������Ƶ������
	        try {
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
	        
	        
	    //����ȫ��ӰƬ����
	        try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("Sub1.fxml"));
		        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		        sbRoot = (javafx.scene.Parent) fxmlLoader.load(
		                       getClass().getResource("Sub1.fxml").openStream());
		        sub1Control=(Sub1Control)fxmlLoader.getController();
		        sub1Control.setFatherControler(playerControl, centrePane, pcRoot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" Some module of Sub1 was missing ");
				e.printStackTrace();
			}
	        
	    //�������ý���
	        try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("OptionControl.fxml"));
		        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		        opRoot = (javafx.scene.Parent) fxmlLoader.load(
		                       getClass().getResource("OptionControl.fxml").openStream());
		        opControl=(OptionControl)fxmlLoader.getController();
		        opControl.setControler(opRoot, sbRoot, pcRoot,mcRoot);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" Some module of OptionControl was missing ");
				e.printStackTrace();
			}

	        opRoot.getStylesheets().add(getClass().getResource(originCSS).toExternalForm());
			pcRoot.getStylesheets().add(getClass().getResource(originCSS).toExternalForm());
			sbRoot.getStylesheets().add(getClass().getResource(originCSS).toExternalForm());
			
			centrePane.getChildren().clear();
			    if(playerControl.getStatus() == 0) {
			    	playerControl.setMediaURL("movie\\China Eastern Airlines B777-300ER Safety video (English).mp4");
			    	playerControl.mediaPlay();
			    }
		    centrePane.getChildren().addAll(pcRoot);
	}
	
	
	//�����Ƶ������
	@FXML
	public void mediaPlay() {
		    centrePane.getChildren().clear();
		    if(playerControl.getStatus() == 0) {
		    	playerControl.setMediaURL("movie\\China Eastern Airlines B777-300ER Safety video (English).mp4");
		    	playerControl.mediaPlay();
		    }
	        centrePane.getChildren().addAll(pcRoot);
	}
	
	
    //�����ȫ��ӰƬ��
	@FXML
	public void clickedAllMovie() {
        	centrePane.getChildren().clear();
        	centrePane.getChildren().addAll(sbRoot);
	}
	
	
	
	//������������á�
	@FXML
	public void clickedSettings() {
			
			centrePane.getChildren().clear();
			opControl.setControler(opRoot, sbRoot, pcRoot,mcRoot);
			centrePane.getChildren().addAll(opRoot);	
			
	
	}
	
    public void setParent(Parent mcRoot) {
    	this.mcRoot = mcRoot;
    }
}
