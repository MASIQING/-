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
 * ӰƬ�����˳���
 * @version 2018/5/27
 * 
 * @author ��˼��
 * @author ���
 *
 * **/
public class MovieMenu  {
	
	
	private movieMenuControl frontSide; //��Ӧ��ǰ�˶���
	private PlayerControl playerControl; //���������ƶ���
	private AnchorPane centrePane; //�����в����滻����
	private Parent mediaPlayerRoot;//������Parent
	
	private ArrayList<String> nowPicUrlList = MainMovieData.getMovieImageViewList(); //��ǰӦ��ʾ��ͼƬ��ַ����
	private String nowMovieUrl = null; //��ǰӦ��ʾ����Ƶ��ַ
	
	private String[] showList = new String[5]; //��¼��ǰͼƬλ�ú�ͼƬ��ַ��ӳ��
	private String language = "CHINESE"; //��ǰ��������
	
	private boolean leftRun = false; //��Ӱ��������������ˢ��
	private boolean rightRun = true; //��Ӱ����������Ҽ���ˢ��
	private int showIndex = 0;
	
	
	/**��ʼ���ϼ�����**/
    public void setFatherObject(PlayerControl playerControl,movieMenuControl frontSide 
			                       ,AnchorPane centrePane,Parent root) {
		this.playerControl = playerControl; 
		this.frontSide = frontSide;
		this.centrePane = centrePane;
		this.mediaPlayerRoot = root;
	}
	
	/**�ı�����**/
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

    /**��������Ӱ���**/
    public void backToMenu() {
		nowMovieUrl = null;
	}
    
    /**����**/
    public void moviePlay() {
        String[] movieIfm = MainMovieData.findMovieData(nowMovieUrl);
		playerControl.stop();
		playerControl.setMediaURL("movie\\"+movieIfm[3]);
		playerControl.mediaPlay();
		this.centrePane.getChildren().addAll(mediaPlayerRoot);
    }
    
    /**���ݵ�Ӱ������ȡ��Ӱ��Ϣ**/
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
	
	/**�����ƶ�**/
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
		
	/**�����ƶ�**/
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
	/**��ʾ����ͼƬ**/
	public void showPicError() {
		frontSide.showPic("ERROR", 3,true,language);
	}

	/**��ʼ��ӰƬ�����**/
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
	
	/**ѡ��ȫ��ӰƬ��**/
	public void clickAll() {
		frontSide.initialClear();
		nowPicUrlList = MainMovieData.getMovieImageViewList();
		showPicInitial();
	}

	/**ѡ���ض����͵�ӰƬ**/
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
    
	/**��ѡ�е�ͼƬ������Ӱ��Ϣ����Ĵ�ͼ**/
	public void deliverImage(int id) {
        frontSide.deliverImage(id);
        nowMovieUrl = showList[id-1].trim(); 
        frontSide.showInform(getInformList(nowMovieUrl));
    
	}
	

	
}