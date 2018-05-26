package Data;

import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainMovieData {
	static String[][] movieList = getMovieInformation();// ��ȡ��Ӱ��Ϣ�Ķ�ά����
	private ArrayList<String> MovieData = new ArrayList<String>();
	private ArrayList<String> LoveMovieData = new ArrayList<String>();
	private ArrayList<String> SciencefictionMovieData = new ArrayList<String>();
	private ArrayList<String> ComdedyMovieData = new ArrayList<String>();
	private ArrayList<String> TragedyMovieData = new ArrayList<String>();
	private ArrayList<String> ActionMovieData = new ArrayList<String>();
	private ArrayList<String> HorribleMovieData = new ArrayList<String>();
	private ArrayList<String> SuspenseMovieData = new ArrayList<String>();
	private ArrayList<String> HistoryMovieData = new ArrayList<String>();
	private ArrayList<String> MusicMovieData = new ArrayList<String>();
	private ArrayList<String> AdventureMovieData = new ArrayList<String>();
	private ArrayList<String> ChildMovieData = new ArrayList<String>();

	public static String[][] getMovieInformation() {
		MovieManager mov = new MovieManager();
		return mov.getAllMovieInformation();
	}

	public static String[][] getTypeMovieInformation(String type) {
		MovieManager mov = new MovieManager();
		return mov.getTypeMovieInformation(type);
	}

	// ��Ӱ��Ϣ��ArrayList
	public static ArrayList<String> getMovieListViewlist() {
		// ��ÿһ����Ӱ�ĵ�Ӱ��Ϣת��Ϊһ�����ַ�����Ȼ������ӵ�ArrayList��
		String moInfo = "";
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < movieList.length; i++) {
			for (int j = 0; j < movieList[i].length; j++) {
				if (j != 2 && j != 3)
					moInfo += movieList[i][j] + " ";
			}
			list.add(moInfo);
			moInfo = "";
		}
		return list;
	}

	// ��Ӱ��Ϣ��ArrayList
	public static ArrayList<String> getTypeMovieImageViewlist(String type) {
		// ��ÿһ����Ӱ�ĵ�Ӱ��Ϣת��Ϊһ�����ַ�����Ȼ������ӵ�ArrayList��
		String[][] movieTypeList = getTypeMovieInformation(type);
		String moInfo = "";
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < movieTypeList.length; i++) {
			moInfo += movieTypeList[i][2] + " ";
			list.add(moInfo);
			moInfo = "";
		}
		return list;
	}

	public static ArrayList<String> getMovieImageViewList() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < movieList.length; i++) {
			list.add(movieList[i][2]);
		}
		return list;

	}

	public static ArrayList<String> getMovieUrlList() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < movieList.length; i++) {
			list.add(movieList[i][3]);
		}
		return list;
	}

//	public void setLoveMovieData() {
//		LoveMovieData.addAll(getTypeMovieImageViewlist("����"));
//	}
//
//	public ArrayList<String> getLoveMovieData() {
//		return LoveMovieData;
//	}
//
//	public void setSciencefictionMovieData() {
//		SciencefictionMovieData.addAll(getTypeMovieImageViewlist("�ƻ�"));
//	}
//
//	public ArrayList<String> getSciencefictionMovieData() {
//		return SciencefictionMovieData;
//	}
//
//	public void setComedyMovieData() {
//		ComdedyMovieData.addAll(getTypeMovieImageViewlist("ϲ��"));
//	}
//
//	public ArrayList<String> getComedyMovieData() {
//		return ComdedyMovieData;
//	}
//
//	public void setTragedyMovieData() {
//		TragedyMovieData.addAll(getTypeMovieImageViewlist("����"));
//	}
//
//	public ArrayList<String> getTragedyMovieData() {
//		return TragedyMovieData;
//	}
//
//	public void setHorribleMovieData() {
//		HorribleMovieData.addAll(getTypeMovieImageViewlist("�ֲ�"));
//	}
//
//	public ArrayList<String> getHorribleMovieData() {
//		return HorribleMovieData;
//	}
//
//	public void setActionMovieData() {
//		ActionMovieData.addAll(getTypeMovieImageViewlist("����"));
//	}
//
//	public ArrayList<String> getActionMovieData() {
//		return ActionMovieData;
//	}
//
//	public void setSuspenseMovieData() {
//		SuspenseMovieData.addAll(getTypeMovieImageViewlist("����"));
//	}
//
//	public ArrayList<String> getSuspenseMovieData() {
//		return SuspenseMovieData;
//	}
//
//	public void setHistoryMovieData() {
//		HistoryMovieData.addAll(getTypeMovieImageViewlist("��ʷ"));
//	}
//
//	public ArrayList<String> getHistoryMovieData() {
//		return HistoryMovieData;
//	}
//
//	public void setMusicMovieData() {
//		MusicMovieData.addAll(getTypeMovieImageViewlist("����"));
//	}
//
//	public ArrayList<String> getMusicMovieData() {
//		return MusicMovieData;
//	}
//
//	public void setAdventureMovieData() {
//		AdventureMovieData.addAll(getTypeMovieImageViewlist("ð��"));
//	}
//
//	public ArrayList<String> getAdventureMovieData() {
//		return AdventureMovieData;
//	}
//
//	public void setChildMovieData() {
//		ChildMovieData.addAll(getTypeMovieImageViewlist("��ͯ"));
//	}
//
//	public ArrayList<String> getChildMovieData() {
//		return ChildMovieData;
//	}

	public MainMovieData() {
		MovieData.addAll(getMovieListViewlist());
	}

	public ArrayList<String> getMovieData() {
		return MovieData;
	}

}
