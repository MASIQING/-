package Data;

import java.util.HashMap;
import java.util.List;

public interface MovieInformation{
	
    /**
     * @param 
     * ��Ҫ�ṩ���е�Ӱ����Ϣ��  
	         ��������String[��Ӱindex][0�����ƣ�1�������ļ���ַ��2�����ң�3�����ͣ�4��ͼƬ��ַ��5��Ԥ������1��6��Ԥ������2��]
	   
	**/
	public abstract String[][] getAllMovieInformation();
	
	
	/**
	 * @param
	 * ����µ�Ӱ
	        �������� [0�����ƣ�1�������ļ���ַ��2�����ң�3�����ͣ�4��ͼƬ��ַ��5��Ԥ������1��6��Ԥ������2��]
	**/
	public abstract void addNewFilm(String[] newFilmInformation);
	
	
	/**
	 * @param
	 * ɾ����Ӱ
	 * ����int�� 0����indexɾ���� 1��������ɾ����
	 *    inform�� Ҫɾ����Ӱ��index������
	**/
	public abstract void deleteFilm(int type,String inform);
	
	
	/**
	 *@param
	 *���ҵ�Ӱ
	 *����һ��HashMap KeyΪ��Ӱindex��int�� valueΪ�����ļ���ַ��String��
	 *����nameΪ��Ӱ���֣�����Ҳ��ɹ��򷵻�null
	 */
	public abstract HashMap searchMovie(String name);
	
	/**
	 * @param
	 * �������е�Ӱ����
	 * **/
	public List getAllMovieName();
}
