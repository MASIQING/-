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
 * SceneStart 为程序的启动点，首先加载MainControl.fxml配置文件以生成菜单页面.
 * 
 * 
 * **/


public class SceneStart extends Application{
  
    
	public static void main(String...strings ) {
		Application.launch(SceneStart.class,strings);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//加载布局信息配置文件(MainControl.fxml)
		Parent main = FXMLLoader.load(getClass().getResource("StartPane.fxml"));
		
		//Scene相当于画布 是场景中的根节点
		Scene scene = new Scene(main);
		
		//Stage就是窗口，primaryStage是程序启动后的主窗口，由框架转入
		//将Scene场景绑定到主窗口中
	    primaryStage.setScene(scene);
		//设置窗口名称
		primaryStage.setTitle("Test vision 0.1");
		primaryStage.setResizable(false);
		//显示窗口
		primaryStage.show();
	
	}
	
}

