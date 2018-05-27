package FrontSide;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import Data.MainMovieData;
import Data.MovieManager;
import Data.WebCrawerTools;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Sub1Control  {
	private MainMovieData moviedata;
	@FXML private AnchorPane Sub1Pane;
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
	@FXML private Button allmovie;
	@FXML private Button love;
	@FXML private Button sciencefiction;
	@FXML private Button comedy;
	@FXML private Button tragdy;
	@FXML private Button action;
	@FXML private Button horrible;
	@FXML private Button suspense;
	@FXML private Button history;
	@FXML private Button music;
	@FXML private Button adventure;
	@FXML private Button child;
	@FXML private Button chinese;
	@FXML private Button europeAndUS;
	@FXML private Button japanAndKorea;
	@FXML private Button other;
	@FXML private Button right;
	@FXML private Button left;
	@FXML private Button nowPlay;
	@FXML private Button backToMenu;
	@FXML private Text movieCountry;
	@FXML private Text movieName; 
	@FXML private Text movieInform;
	
	private int showIndex = 0;
	private PlayerControl playerControl;
	private AnchorPane centrePane;
	private Parent root;
	private String nowMovieUrl = null;
	private String[] showList = new String[5];
	private int x = 1;
	private int y = 2;
	private String language = "CHINESE";
	boolean leftRun = false;
	boolean rightRun = true;
	
	public Sub1Control() {
		Platform.runLater(new Runnable() {
			@Override
			
			public void run() {
				setMovieData(new MainMovieData());
			}
		});
	}
	
	public void setFatherControler(PlayerControl playerControl
			                    ,AnchorPane centrePane
			                    ,Parent root) {
		this.playerControl = playerControl;
		this.centrePane = centrePane;
		this.root = root;
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
				nowPlay.setText(pps.getProperty("Á¢¼´²¥·Å"));
				backToMenu.setText(pps.getProperty("·µ»Ø"));
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
				nowPlay.setText(pps.getProperty("Á¢¼´²¥·Å"));
				backToMenu.setText(pps.getProperty("·µ»Ø"));
			}
			if(nowMovieUrl!=null) showInformSet(nowMovieUrl);
			
		} catch (IOException e) {
			System.out.println("Properties file missing");
			e.printStackTrace();
		}
		

	}
	
	@FXML 
	public void initialize() {
		subAnchorPane2.setVisible(false);
		MovieManager movieManager = new MovieManager();
	}
	
	@FXML
	public void clickView1() {
		System.out.println("Click View 1");
        subAnchorPane1.setVisible(false);
        Image image = imageView1.getImage();
        movieInformImage.setImage(image);
        nowMovieUrl = showList[0]; 
        showInformSet(nowMovieUrl);
	}
	@FXML
	public void clickView2() {
		System.out.println("Click View 2");
		subAnchorPane1.setVisible(false);
		subAnchorPane2.setVisible(true);
		Image image = imageView2.getImage();
	    movieInformImage.setImage(image);
	    nowMovieUrl = showList[1]; 
	    showInformSet(nowMovieUrl);
	}
	@FXML
	public void clickView3() {
		System.out.println("Click View 3");
		subAnchorPane1.setVisible(false);
		subAnchorPane2.setVisible(true);
		Image image = imageView3.getImage();
	    movieInformImage.setImage(image);
	    nowMovieUrl = showList[2]; 
	    showInformSet(nowMovieUrl);
	}
	@FXML
	public void clickView4() {
		System.out.println("Click View 4");
		subAnchorPane1.setVisible(false);
		subAnchorPane2.setVisible(true);
        Image image = imageView4.getImage();
	    movieInformImage.setImage(image);
	    nowMovieUrl = showList[3]; 
	    showInformSet(nowMovieUrl);
	}
	@FXML
	public void clickView5() {
		System.out.println("Click View 5");
		subAnchorPane1.setVisible(false);
		subAnchorPane2.setVisible(true);
	    Image image = imageView5.getImage();
	    movieInformImage.setImage(image);
	    nowMovieUrl = showList[4];  
	    showInformSet(nowMovieUrl);
	}
	
	@FXML 
	public void clickBackToMenu() {
		System.out.println("buckToMenu Clicked");
		subAnchorPane2.setVisible(false);
		nowMovieUrl = null;
		subAnchorPane1.setVisible(true);
	}
	
	@FXML
	public void clickNowPlay() {
		subAnchorPane2.setVisible(false);
		subAnchorPane1.setVisible(true);
	
		MovieManager movieManager = new MovieManager();
		String[][] movieIfm = movieManager.getAllMovieInformation();
		String []movieIfm2 = new String[6];
		
		for(int i = 0;i<movieIfm.length;i++) {
			if(movieIfm[i][2].equals(nowMovieUrl)) {
				movieIfm2 = movieIfm[i];
				break;
			}
		}
		
		playerControl.stop();
		playerControl.setMediaURL("movie\\"+movieIfm2[3]);
		playerControl.mediaPlay();
		this.centrePane.getChildren().addAll(root);
	}
	
	private void showInformSet(String url) {
		
		movieName.setText(null);
		movieInform.setText(null);
		MovieManager movieManager = new MovieManager();
		String[][] movieIfm = movieManager.getAllMovieInformation();
		String []movieIfm2 = new String[6];
		for(int i = 0;i<movieIfm.length;i++) {
			System.out.println(movieIfm[i][2]);
			if(movieIfm[i][2].equals(nowMovieUrl)) {
				movieIfm2 = movieIfm[i];
				break;
			}
		}
		
		String name = movieIfm2[1];
		if(language.equals("CHINESE")) {
			String reg = "[^\\u4e00-\\u9fa5]";
			String finalName = name.replaceAll(reg, "")+"  "+movieIfm2[5];
			movieName.setText(finalName);
			
			WebCrawerTools crawer = new WebCrawerTools();
			String introduction = crawer.PA(name.replaceAll(reg, ""));
			movieInform.setText(introduction);
			movieCountry.setText(movieIfm2[4]);
			
		}else if(language.equals("ENGLISH")){
			String reg = "[^A-z]";
			String finalName = name.replaceAll(reg, "")+"  "+movieIfm2[5];
			movieName.setText(finalName);
			movieCountry.setText(movieIfm2[4]);
		}
	
	}
	
	private void initialClear() {
		imageView1.setImage(null);
		imageView2.setImage(null);
		imageView3.setImage(null);
		imageView4.setImage(null);
		imageView5.setImage(null);
	}

	
	private void rightAction(ArrayList<String> PicUrl) {
		initialClear();
		leftRun = true;
		if(rightRun == true) showIndex++;
		int si2 = showIndex;
		try {
			ShowPic(PicUrl.get(si2-2),1);
			showList[0] = PicUrl.get(si2-2);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si2-1),2);
			showList[1] = PicUrl.get(si2-1);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si2),3);
			showList[2] = PicUrl.get(si2);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si2+1),4);
			showList[3] = PicUrl.get(si2+1);
		}catch(Exception e) {
			rightRun = false;
	    }
		try {
			ShowPic(PicUrl.get(si2+2),5);
			showList[4] = PicUrl.get(si2+2);
		}catch(Exception e) {}
		
	}
		
	private void leftAction(ArrayList<String> PicUrl) {
		initialClear();
		rightRun = true;
		System.out.println(showIndex);
		if(leftRun == true) showIndex--;
		int si2 = showIndex;
		try {
			ShowPic(PicUrl.get(si2-2),1);
			showList[0] = PicUrl.get(si2-2);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si2-1),2);
			showList[1] = PicUrl.get(si2-1);
		}catch(Exception e) {
		    leftRun = false;
		}
		try {
			ShowPic(PicUrl.get(si2),3);
			showList[2] = PicUrl.get(si2);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si2+1),4);
			showList[3] = PicUrl.get(si2+1);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si2+2),5);
			showList[4] = PicUrl.get(si2+2);
		}catch(Exception e) {}
		
		
	}

	private void frontSideControl(ArrayList<String> PicUrl) {
        
		showIndex =  0;
		int si = showIndex;
		ShowPic(PicUrl.get(si),3);
		showList[2] = PicUrl.get(si);
		try {
			ShowPic(PicUrl.get(si+1),4);
			showList[3] = PicUrl.get(si+1);
		}catch(Exception e) {}
		try {
			ShowPic(PicUrl.get(si+2),5);
			showList[4] = PicUrl.get(si+2);
		}catch(Exception e) {}
			
	
		right.setOnAction(oa->{
			rightAction(PicUrl);
	    });
		left.setOnAction(oa->{
			leftAction(PicUrl);
	    });
	}

	public void setMovieData(MainMovieData moviedata) {
		this.moviedata = moviedata;
		ArrayList<String> PicUrl = moviedata.getMovieImageViewList();
		frontSideControl(PicUrl);
		
	}

	public void clickall() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				ArrayList<String> PicUrl =  moviedata.getMovieImageViewList();
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}

	public void clickSciencefiction() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "¿Æ»Ã";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickComedy() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "Ï²¾ç";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickTragedy() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "±¯¾ç";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}

	public void clicklove() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "°®Çé";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				System.out.println(PicUrl);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickAction() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "¶¯×÷";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickHorrible() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "¿Ö²À";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickSuspense() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "ÐüÒÉ";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickHistory() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "ÀúÊ·";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickMusic() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "ÒôÀÖ";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickAdventure() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "Ã°ÏÕ";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}
	
	public void clickChild() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MainMovieData moviedata = new MainMovieData();
				String type = "¶ùÍ¯";
				ArrayList<String> PicUrl = moviedata.getTypeMovieImageViewlist(type);
				initialClear();
				frontSideControl(PicUrl);
			}
		});
	}

	private void ShowPic(String picurl,int id) {
		Image img = new Image(Sub1Control.class.getResourceAsStream("moviePicture\\"+picurl));
		switch(id) {
		case 1:imageView1.setImage(img);break;
		case 2:imageView2.setImage(img);break;
		case 3:imageView3.setImage(img);break;
		case 4:imageView4.setImage(img);break;
		case 5:imageView5.setImage(img);break;
		}
	}


}
