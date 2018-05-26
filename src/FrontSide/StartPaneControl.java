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
		 try {
	        	FXMLLoader fxmlLoader = new FXMLLoader();
	 	        fxmlLoader.setLocation(getClass().getResource("MenuControl.fxml"));//得到FXML文档
	 	        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());//设置BiilderFactory （我也不知道是啥）
				Parent mcRoot = (javafx.scene.Parent) fxmlLoader.load(
						       getClass().getResource("MenuControl.fxml").openStream());
				MenuControl mcControl=(MenuControl)fxmlLoader.getController();
				anchorPane.getChildren().addAll(mcRoot);
				mcRoot.getStylesheets().addAll(getClass().getResource("css\\CSS White.css").toExternalForm());
				mcControl.setParent(mcRoot);
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
