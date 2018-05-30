package FrontSide;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.layout.*;

public class StartPaneControl {

	@FXML AnchorPane anchorPane;
	
	@FXML 
	public void initialize() {
		
		System.out.println("STARTPANE initialize");
		
		 try {
	        	FXMLLoader fxmlLoader = new FXMLLoader();
	 	        fxmlLoader.setLocation(getClass().getResource("MenuControl.fxml"));//�õ�FXML�ĵ�
	 	        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());//����BiilderFactory ����Ҳ��֪����ɶ��
				Parent mcRoot = (javafx.scene.Parent) fxmlLoader.load(
						       getClass().getResource("MenuControl.fxml").openStream());
				MenuControl mcControl=(MenuControl)fxmlLoader.getController();
				anchorPane.getChildren().addAll(mcRoot);
				mcRoot.getStylesheets().addAll(getClass().getResource("css\\CSS White.css").toExternalForm());
				mcControl.setParent(mcRoot);
				mcControl.setMenuControl(mcControl);
				mcControl.diliverControler();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
