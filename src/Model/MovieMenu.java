package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import Data.MainMovieData;
import Data.WebCrawerTools;
import FrontSide.PlayerControl;
import FrontSide.movieMenuControl;
import javafx.scene.Parent;

import javafx.scene.layout.AnchorPane;


/**
 * 影片界面后端程序
 * @version 2018/5/27
 * 
 * @author 马思清
 * @author 李晶晶
 *
 * **/
public class MovieMenu  {
	
	
	private movieMenuControl frontSide; //对应的前端对象
	private PlayerControl playerControl; //播放器控制对象
	private AnchorPane centrePane; //界面中部可替换布局
	private Parent mediaPlayerRoot;//播放器Parent
	
	private ArrayList<String> nowPicUrlList = MainMovieData.getMovieImageViewList(); //当前应显示的图片地址数组
	private String nowMovieUrl = null; //当前应显示的视频地址
	
	private String[] showList = new String[5]; //记录当前图片位置和图片地址的映射
	private String language = "CHINESE"; //当前程序语言
	
	private boolean leftRun = false; //电影浏览，可向左继续刷新
	private boolean rightRun = true; //电影浏览，可向右继续刷新
	private int showIndex = 0;
	
	
	/**初始化上级对象**/
    public void setFatherObject(PlayerControl playerControl,movieMenuControl frontSide 
			                       ,AnchorPane centrePane,Parent root) {
		this.playerControl = playerControl; 
		this.frontSide = frontSide;
		this.centrePane = centrePane;
		this.mediaPlayerRoot = root;
	}
	
	/**改变语言**/
    public void changeLanguage(String language) {
    	this.language = language;
    	Properties pps = new Properties();
    	try {
    		FileInputStream inputStream = new FileInputStream("Language.properties");
			pps.load(new InputStreamReader(inputStream, "UTF-8"));
			frontSide.changeLanguage(pps, language);
			if(nowMovieUrl!=null) {
				frontSide.showInform(getInformList(nowMovieUrl));
			}		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
    }

    /**返回至电影浏览**/
    public void backToMenu() {
		nowMovieUrl = null;
	}
    
    /**播放**/
    public void moviePlay() {
        String[] movieIfm = MainMovieData.findMovieData(nowMovieUrl);
		playerControl.stop();
		playerControl.setMediaURL("movie\\"+movieIfm[3]);
		playerControl.mediaPlay();
		this.centrePane.getChildren().addAll(mediaPlayerRoot);
    }
    
    /**根据电影名称爬取电影信息**/
	public String[] getInformList(String url) {
		String [] inform = new String [4];
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
			inform[0] = showName;
			inform[1] = movieIfm[5];
			
			String searchName = name.substring(i+1);
			searchName = name.substring(i+1,k+i);
			String introduction = WebCrawerTools.paZhongWen(searchName);
			System.out.println("SearchName  "+searchName);
			inform[3] = introduction;
			
			String reg = "[^\\u4e00-\\u9fa5 |]";
			String reg2 = "[|]";
			String countryName = movieIfm[4].replaceAll(reg, "").replaceAll(reg2, " ");
			inform[2] = countryName;
			
		
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
			inform[0] = showName.replaceAll("[^A-z ]", "")
					  .replaceAll("[_]", " ");
			inform[1] = movieIfm[5];
			
			String searchName = name.substring(i+1);
			searchName = name.substring(i+1,k+i);
			System.out.println("SearchName  "+searchName);
			String introduction = WebCrawerTools.paYingWen(searchName);
			inform[3] = introduction;
			
			String reg = "[^A-z |]";
			String reg2 = "[|]";
			String countryName = movieIfm[4].replaceAll(reg, "").replaceAll(reg2, " ");
			inform[2] = countryName;
			
		}
		return inform;
	
	}
	
	/**向右移动**/
	public void rightAction() {
		frontSide.initialClear();
		leftRun = true;
		if(rightRun == true) {
			showIndex++;
		}
		int si2 = showIndex;
		try {
			frontSide.showPic(nowPicUrlList.get(si2-2),1,false,null);
			showList[0] = nowPicUrlList.get(si2-2);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si2-1),2,false,null);
			showList[1] = nowPicUrlList.get(si2-1);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si2),3,false,null);
			showList[2] = nowPicUrlList.get(si2);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si2+1),4,false,null);
			showList[3] = nowPicUrlList.get(si2+1);
		}catch(Exception e) {
			rightRun = false;
	    }
		try {
			frontSide.showPic(nowPicUrlList.get(si2+2),5,false,null);
			showList[4] = nowPicUrlList.get(si2+2);
		}catch(Exception e) {}
		
	}
		
	/**向左移动**/
	public void leftAction() {
		frontSide.initialClear();
		rightRun = true;
		if(leftRun == true) {
			showIndex--;
		}
		int si2 = showIndex;
		try {
			frontSide.showPic(nowPicUrlList.get(si2-2),1,false,null);
			showList[0] = nowPicUrlList.get(si2-2);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si2-1),2,false,null);
			showList[1] = nowPicUrlList.get(si2-1);
		}catch(Exception e) {
		    leftRun = false;
		}
		try {
			frontSide.showPic(nowPicUrlList.get(si2),3,false,null);
			showList[2] = nowPicUrlList.get(si2);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si2+1),4,false,null);
			showList[3] = nowPicUrlList.get(si2+1);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si2+2),5,false,null);
			showList[4] = nowPicUrlList.get(si2+2);
		}catch(Exception e) {}
		
		
	}
	/**显示报错图片**/
	public void showPicError() {
		frontSide.showPic("ERROR", 3,true,language);
	}

	/**初始化影片浏览器**/
	public void showPicInitial() {
		showList = new String[5];
		showIndex =  0;
		int si = showIndex;
		System.out.println("1"+frontSide);
		frontSide.showPic(nowPicUrlList.get(si),3,false,null);
		showList[2] = nowPicUrlList.get(si);
		try {
			frontSide.showPic(nowPicUrlList.get(si+1),4,false,null);
			showList[3] = nowPicUrlList.get(si+1);
		}catch(Exception e) {}
		try {
			frontSide.showPic(nowPicUrlList.get(si+2),5,false,null);
			showList[4] = nowPicUrlList.get(si+2);
		}catch(Exception e) {}
	}
	
	/**选择“全部影片”**/
	public void clickAll() {
		frontSide.initialClear();
		nowPicUrlList = MainMovieData.getMovieImageViewList();
		showPicInitial();
	}

	/**选择特定类型的影片**/
	public void selectTypeAction(String type) {
		nowPicUrlList = MainMovieData.getTypeMovieImageViewlist(type);
		System.out.println("NPC    "+nowPicUrlList);
		if(!nowPicUrlList.isEmpty()) {
			frontSide.initialClear();
			showPicInitial();
		}else {
			showPicError();
		}
	}
    
	/**将选中的图片传至电影信息界面的大图**/
	public void deliverImage(int id) {
        frontSide.deliverImage(id);
        nowMovieUrl = showList[id-1].trim(); 
        frontSide.showInform(getInformList(nowMovieUrl));
    
	}
	

	
}