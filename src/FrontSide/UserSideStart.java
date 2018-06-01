package FrontSide;
import java.awt.event.WindowAdapter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

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


public class UserSideStart extends Application{
  
    
	public static void main(String...strings ) {
		Application.launch(UserSideStart.class,strings);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//���ز�����Ϣ�����ļ�(MainControl.fxml)
		Parent main = FXMLLoader.load(getClass().getResource("StartPane.fxml"));
		
		//����Properties�����ļ�
		Properties userDefault = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream("userDefault.properties");
			userDefault.load(new InputStreamReader(inputStream, "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		
		
		//Scene�൱�ڻ��� �ǳ����еĸ��ڵ�
		Scene scene = new Scene(main);
		
		//Stage���Ǵ��ڣ�primaryStage�ǳ���������������ڣ��ɿ��ת��
		//��Scene�����󶨵���������
	    primaryStage.setScene(scene);
		//���ô�������
		primaryStage.setTitle(userDefault.getProperty("companyName"));
		primaryStage.setResizable(false);
		//��ʾ����
		primaryStage.show();
	
		System.out.println("picccc"+userDefault.getProperty("companyPicture1"));
	}
	
}

