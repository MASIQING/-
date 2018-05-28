package FrontSide;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
/**
 * SceneStart Ϊ����������㣬���ȼ���MainControl.fxml�����ļ������ɲ˵�ҳ��.
 * 
 * 
 * **/


public class SceneStart extends Application{
  
    
	public static void main(String...strings ) {
		Application.launch(SceneStart.class,strings);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//���ز�����Ϣ�����ļ�(MainControl.fxml)
		Parent main = FXMLLoader.load(getClass().getResource("StartPane.fxml"));
		
		//Scene�൱�ڻ��� �ǳ����еĸ��ڵ�
		Scene scene = new Scene(main);
		
		//Stage���Ǵ��ڣ�primaryStage�ǳ���������������ڣ��ɿ��ת��
		//��Scene�����󶨵���������
	    primaryStage.setScene(scene);
		//���ô�������
		primaryStage.setTitle("Test vision 0.1");
		primaryStage.setResizable(false);
		//��ʾ����
		primaryStage.show();
	
	}
	
}

