package Data;

import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainMovieData {
	static String[][] movieList = getMovieInformation();// ��ȡ��Ӱ��Ϣ�Ķ�ά����
	private ArrayList<String> movieData = new ArrayList<String>();

	public MainMovieData() {
		movieData.addAll(getMovieListViewlist());
	}

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
				if (j != 2 && j != 3) {
					moInfo += movieList[i][j] + " ";
				}
			}
			list.add(moInfo);
			moInfo = "";
		}
		return list;
	}

	// ��Ӱ��Ϣ��ArrayList
	public static ArrayList<String> getTypeMovieImageViewlist(String type) {
		// ��ÿһ����Ӱ�ĵ�Ӱ��Ϣת��Ϊһ�����ַ�����Ȼ������ӵ�ArrayList��
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			String[][] movieTypeList = getTypeMovieInformation(type);
			String moInfo = "";
			for (int i = 0; i < movieTypeList.length; i++) {
				moInfo += movieTypeList[i][2] + " ";
				list.add(moInfo);
				moInfo = "";
			}
		}catch(Exception e) {
			System.out.println("Can't find the type");
			return null;
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

	public static String[] findMovieData(String picUrl) {
		
		String[][]movieIfm = MovieManager.getAllMovieInformation();
		String[] movieIfm2 = new String [6];
		for(int i = 0;i<movieIfm.length;i++) {
			
			if(movieIfm[i][2].equals(picUrl)) {
				movieIfm2 = movieIfm[i];
				return movieIfm2;
			}
		}
		return null;
	}

	public ArrayList<String> getMovieData() {
		return movieData;
	}

}
