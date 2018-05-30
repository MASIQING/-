package FrontSide;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import Data.MainMovieData;
import Data.WebCrawerTools;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * 控制影片选择界面
 * @version 2018/5/27
 * 
 * @author 马思清
 *
 * **/
public class Sub1Control  {
	@FXML private AnchorPane sub1Pane;
	@FXML private AnchorPane subAnchorPane1;
	@FXML private AnchorPane subAnchorPane2;
	@FXML private ScrollPane scrollPane;
	@FXML private GridPane gridPane;
	@FXML private ImageView movieInformImage;
	@FXML private ImageView imageView1;
	@FXML private ImageView imageView2;
	@FXML private ImageView imageView3;
	@FXML private ImageView imageView4;
	@FXML private ImageView imageView5;
	
	@FXML private Button g00; //全部
	@FXML private Button g03; //爱情
	@FXML private Button g13; //科幻
	@FXML private Button g01; //喜剧
	@FXML private Button g02; //悲剧
	@FXML private Button g04; //动作
	@FXML private Button g05; //恐怖
	@FXML private Button g06; //悬疑
	@FXML private Button g07; //历史
	@FXML private Button g11; //音乐
	@FXML private Button g12; //冒险
	@FXML private Button g10; //儿童
	@FXML private Button g14; //中国
	@FXML private Button g15; //欧美
	@FXML private Button g16; //日韩
	@FXML private Button g17; //其他
	
	@FXML private Button right;
	@FXML private Button left;
	@FXML private Button nowPlay;
	@FXML private Button backToMenu;
	@FXML private Text movieCountry;
	@FXML private Text movieName; 
	@FXML private Text movieYear;
	@FXML private Text movieInform;
	
	private int y = 0;
	private int x = 0;
	private int showIndex = 0;
	private PlayerControl playerControl;
	private AnchorPane centrePane;
	private Parent root;
	private String nowMovieUrl = null;
	private String[] showList = new String[5];
	private String language = "CHINESE";
	boolean leftRun = false;
	boolean rightRun = true;
	private String[][] Types = {{"全部", "喜剧", "悲剧", "爱情", "动作", "恐怖", "悬疑", "历史"},
		       {"儿童", "音乐", "冒险", "科幻", "中国", "欧美", "日韩", "其他"}};	
	public void setFatherControler(PlayerControl playerControl
			                      ,AnchorPane centrePane
			                      ,Parent root) {
		this.playerControl = playerControl;
		this.centrePane = centrePane;
		this.root = root;
	}
	private ArrayList<String> nowPicUrl = MainMovieData.getMovieImageViewList();
	
	private EventHandler gridPaneHandler = new EventHandler<KeyEvent>() {


		@Override
		public void handle(KeyEvent keygrid) {
			// TODO Auto-generated method stub
			
			if(keygrid.getCode() == KeyCode.LEFT){
				if(x<=7&&x>-1) {
					x--;
				}
				keygrid.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(getClass().getResource("css\\CSS gridPaneButton.css").toExternalForm());
					}
				}
			}
			if(keygrid.getCode() == KeyCode.RIGHT){
				if(x<7&&x>=-1) {
					x++;
				}
				keygrid.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(getClass().getResource("css\\CSS gridPaneButton.css").toExternalForm());
					}
				}
			}
		    if(keygrid.getCode() == KeyCode.UP){
		    	if(y==1) {
		    		y--;
		    	}
				keygrid.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(getClass().getResource("css\\CSS gridPaneButton.css").toExternalForm());
					}
				}
			}
		    if(keygrid.getCode() == KeyCode.DOWN){
		    	if(y==0) {
		    		y++;
		    	}
				keygrid.consume();
				String buttonId = "g"+y+""+x;
				System.out.println(buttonId);
				for(int i = 0 ;i < 16 ; i ++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.getStylesheets().clear();
					if(but.getId().equals(buttonId)) {
						but.getStylesheets().add(getClass().getResource("css\\CSS gridPaneButton.css").toExternalForm());
					}
				}
			}
			//这里切换不同类型电影
		    if(keygrid.getCode() == KeyCode.ENTER){
		    	
				if(y == 0 && x == 0){
					clickall();
					keygrid.consume();
					subAnchorPane1.requestFocus();
					System.out.println("all movie");
				}
				else{
					selectTypeAction(Types[y][x]);
					keygrid.consume();
					subAnchorPane1.requestFocus();
					System.out.println("type movie");
				}
			}
			//如果横坐标大于1，想当与光标移到subAnchorPane1上，可以继续左右移动电影海报操作
			if(y > 1){
				subAnchorPane1.requestFocus();
				System.out.println("subAnchorPane1 is focused?" + subAnchorPane1.isFocused());
				subAnchorPane1.removeEventHandler(KeyEvent.KEY_PRESSED, gridPaneHandler);
				y = 0;
				x = 0;
				keygrid.consume();
			}
		}  
	};
	
	
    
    private EventHandler anchorPaneHandler = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			// TODO Auto-generated method stub
			if(key.getCode() == KeyCode.RIGHT){
				rightAction(nowPicUrl);
				key.consume();
				System.out.println("right1");
			}
			if(key.getCode() == KeyCode.LEFT){
				leftAction(nowPicUrl);
				key.consume();
				System.out.println("left1");
			}
			//进入电影信息界面
			if(key.getCode() == KeyCode.ENTER){
				clickView3();
				key.consume();
				System.out.println("enter infomation");
			}
			//向上相当于光标移到gridPane上，我现在还没有进行css的工作，所以看上去没变化
			if(key.getCode() == KeyCode.UP){
				gridPane.requestFocus();
				System.out.println("gridPane is focused?" + gridPane.isFocused());
				gridPane.addEventHandler(KeyEvent.KEY_PRESSED, gridPaneHandler);
				key.consume();
			
			}
		}
    	
    };	
    
    private EventHandler informationHandler	= new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent key) {
			// TODO Auto-generated method stub
			//返回
			if(key.getCode() == KeyCode.BACK_SPACE){
				clickBackToMenu();
				key.consume();
			}
			//播放
			if(key.getCode() == KeyCode.ENTER){
				clickNowPlay();
				key.consume();
			}
		}
	
    };
    
    
    @FXML 
	public void initialize() {
		subAnchorPane2.setVisible(false);
	    nowPicUrl = MainMovieData.getMovieImageViewList();
		frontSideControl(nowPicUrl);
		
		//左右切换电影
		subAnchorPane1.addEventHandler(KeyEvent.KEY_PRESSED, anchorPaneHandler);
		//电影信息界面
		subAnchorPane2.addEventHandler(KeyEvent.KEY_PRESSED, informationHandler);
	}
	
	private void showInformSet(String url) {
		
		movieName.setText(null);
		movieInform.setText(null);
		
		String[] movieIfm = MainMovieData.findMovieData(nowMovieUrl);
		String name = movieIfm[1];
		
		if(language.equals("CHINESE")) {
			int i = name.indexOf("&");
			int j = name.substring(i).indexOf("/");
			int k = name.substring(i).indexOf("-");
			System.out.println("i "+i+" j "+j+" k "+k);
			String showName = name.substring(i);
			if(j>=0) {
				showName = name.substring(i+1, j+i);
			}else {
				showName = name.substring(i+1,k+i);
			}
			movieName.setText(showName);
			movieYear.setText(movieIfm[5]);
			
			String searchName = name.substring(i+1);
			searchName = name.substring(i+1,k+i);
			String introduction = WebCrawerTools.paZhongWen(searchName);
			System.out.println("SearchName  "+searchName);
			movieInform.setText(introduction);
			
			String reg = "[^\\u4e00-\\u9fa5 |]";
			String reg2 = "[|]";
			String countryName = movieIfm[4].replaceAll(reg, "").replaceAll(reg2, " ");
			movieCountry.setText(countryName);
			
			
		}else if(language.equals("ENGLISH")){
			int i = name.indexOf("$");
			int j = name.substring(i).indexOf("(");
			int k = name.substring(i).indexOf("-");
			
			String showName = name.substring(i);
			if(j>=0) {
				showName = name.substring(i+1, j+i);
			}else {
				showName = name.substring(i+1,k+i);
			}
			movieName.setText(showName.replaceAll("[^A-z ]", "")
					  .replaceAll("[_]", " "));
			movieYear.setText(movieIfm[5]);
			
			String searchName = name.substring(i+1);
			searchName = name.substring(i+1,k+i);
			System.out.println("SearchName  "+searchName);
			String introduction = WebCrawerTools.paYingWen(searchName);
			movieInform.setText(introduction);
			
			String reg = "[^A-z |]";
			String reg2 = "[|]";
			String countryName = movieIfm[4].replaceAll(reg, "").replaceAll(reg2, " ");
			movieCountry.setText(countryName);
			
			
		}
	
	}
	
	private void initialClear() {
		imageView1.setImage(null);
		imageView2.setImage(null);
		imageView3.setImage(null);
		imageView4.setImage(null);
		imageView5.setImage(null);
	}

	public void changeLanguage(String language) {
		System.out.println("Change language to "+language);
		this.language = language;
		Properties pps = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("Language.properties");
			pps.load(new InputStreamReader(inputStream, "UTF-8"));
			
			if(language.equals("ENGLISH")) {
				
				for(int i = 0;i<gridPane.getChildren().size();i++) {
					Button but = (Button)gridPane.getChildren().get(i);
					but.setText(pps.getProperty(but.getText()));
				}
				nowPlay.setText(pps.getProperty("立即播放"));
				backToMenu.setText(pps.getProperty("返回"));
			}else if(language.equals("CHINESE")) {
				for(int i = 0;i<gridPane.getChildren().size();i++) {
					Button but = (Button)gridPane.getChildren().get(i);
	
					Set<Entry<Object, Object>> entries = pps.entrySet();
					for (Entry entry : entries) {
						if (but.getText().equals(entry.getValue())) {
							but.setText(entry.getKey().toString());break;
						}  
					}
				}
				nowPlay.setText(pps.getProperty("立即播放"));
				backToMenu.setText(pps.getProperty("返回"));
			}
			if(nowMovieUrl!=null) {
				showInformSet(nowMovieUrl);
			}
			
		} catch (IOException e) {
			System.out.println("Properties file missing");
			e.printStackTrace();
		}
		

	}
	
	private void rightAction(ArrayList<String> picUrl) {
		initialClear();
		leftRun = true;
		if(rightRun == true) {
			showIndex++;
		}
		int si2 = showIndex;
		try {
			ShowPic(picUrl.get(si2-2),1);
			showList[0] = picUrl.get(si2-2);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si2-1),2);
			showList[1] = picUrl.get(si2-1);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si2),3);
			showList[2] = picUrl.get(si2);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si2+1),4);
			showList[3] = picUrl.get(si2+1);
		}catch(Exception e) {
			rightRun = false;
	    }
		try {
			ShowPic(picUrl.get(si2+2),5);
			showList[4] = picUrl.get(si2+2);
		}catch(Exception e) {}
		
	}
		
	private void leftAction(ArrayList<String> picUrl) {
		initialClear();
		rightRun = true;
		if(leftRun == true) {
			showIndex--;
		}
		int si2 = showIndex;
		try {
			ShowPic(picUrl.get(si2-2),1);
			showList[0] = picUrl.get(si2-2);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si2-1),2);
			showList[1] = picUrl.get(si2-1);
		}catch(Exception e) {
		    leftRun = false;
		}
		try {
			ShowPic(picUrl.get(si2),3);
			showList[2] = picUrl.get(si2);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si2+1),4);
			showList[3] = picUrl.get(si2+1);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si2+2),5);
			showList[4] = picUrl.get(si2+2);
		}catch(Exception e) {}
		
		
	}

	private void frontSideControl(ArrayList<String> picUrl) {
		nowPicUrl = picUrl;
		showList = new String[5];
		showIndex =  0;
		int si = showIndex;
		ShowPic(picUrl.get(si),3);
		showList[2] = picUrl.get(si);
		try {
			ShowPic(picUrl.get(si+1),4);
			showList[3] = picUrl.get(si+1);
		}catch(Exception e) {}
		try {
			ShowPic(picUrl.get(si+2),5);
			showList[4] = picUrl.get(si+2);
		}catch(Exception e) {}
		//鼠标控制海报左右移动，因为在frontSideControl里，所以这里很正常
		right.setOnAction(oa->{
			rightAction(picUrl);
		});
		left.setOnAction(oa->{
			leftAction(picUrl);
		});
		
	}

	private void selectTypeAction(String type) {
		ArrayList<String> picUrl = MainMovieData.getTypeMovieImageViewlist(type);
		initialClear();
		frontSideControl(picUrl);
	}
    
	private void viewOperation(int id) {
        subAnchorPane1.setVisible(false);
        subAnchorPane2.setVisible(true);
        Image image = null;
        switch(id) {
        default :;
        case 1: image = imageView1.getImage();break;
        case 2: image = imageView2.getImage();break;
        case 3: image = imageView3.getImage();break;
        case 4: image = imageView4.getImage();break;
        case 5: image = imageView5.getImage();break;
        }
        movieInformImage.setImage(image);
        nowMovieUrl = showList[id-1].trim(); 
        showInformSet(nowMovieUrl);
    
	}
	
	private void ShowPic(String picurl,int id) {
		Image img = new Image(Sub1Control.class.getResourceAsStream("moviePicture\\"+picurl));
		switch(id) {
		default: ;
		case 1:imageView1.setImage(img);break;
		case 2:imageView2.setImage(img);break;
		case 3:imageView3.setImage(img);break;
		case 4:imageView4.setImage(img);break;
		case 5:imageView5.setImage(img);break;
		}
	}

	
	@FXML 
	public void clickBackToMenu() {
		subAnchorPane2.setVisible(false);
		nowMovieUrl = null;
		subAnchorPane1.setVisible(true);
	}
	
	@FXML
	public void clickNowPlay() {
		subAnchorPane2.setVisible(false);
		subAnchorPane1.setVisible(true);
	
		String[] movieIfm = MainMovieData.findMovieData(nowMovieUrl);
		
		playerControl.stop();
		playerControl.setMediaURL("movie\\"+movieIfm[3]);
		playerControl.mediaPlay();
		this.centrePane.getChildren().addAll(root);
	}
	
	@FXML
	public void clickall() {
		ArrayList<String> picUrl = MainMovieData.getMovieImageViewList();
		initialClear();
		frontSideControl(picUrl);
	}
    
	@FXML
	public void clickView1() {viewOperation(1);}
	@FXML
	public void clickView2() {viewOperation(2);}
	@FXML
	public void clickView3() {viewOperation(3);}
	@FXML
	public void clickView4() {viewOperation(4);}
	@FXML
	public void clickView5() {viewOperation(5);}
	@FXML
	public void clickSciencefiction() {selectTypeAction("科幻");}
	@FXML
	public void clickComedy() {selectTypeAction("喜剧");}
	@FXML
	public void clickTragedy() {selectTypeAction("悲剧");}
	@FXML
	public void clicklove() {selectTypeAction("爱情");}
	@FXML
	public void clickAction() {selectTypeAction("动作");}	
	@FXML
	public void clickHorrible() {selectTypeAction("恐怖");}	
	@FXML
	public void clickSuspense() {selectTypeAction("悬疑");}	
	@FXML
	public void clickHistory() {selectTypeAction("历史");}	
	@FXML
	public void clickMusic() {selectTypeAction("音乐");}	
	@FXML
	public void clickAdventure() {selectTypeAction("冒险");}	
	@FXML
	public void clickChild() {selectTypeAction("儿童");}
	@FXML
	public void clickChinese() {selectTypeAction("中国");}
	@FXML
	public void clickEUUS() {selectTypeAction("欧美");}
	@FXML
	public void clickJPKA() {selectTypeAction("日韩");}
	@FXML
	public void clickOther() {selectTypeAction("其他");}
}