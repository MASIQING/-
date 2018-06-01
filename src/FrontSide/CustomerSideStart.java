package FrontSide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerSideStart extends Application{

	public static void main(String...strings ) {
		Application.launch(CustomerSideStart.class,strings);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//���ز�����Ϣ�����ļ�(MainControl.fxml)
		Parent main = FXMLLoader.load(getClass().getResource("CustomerSet.fxml"));
		
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
