package Data;

import java.util.HashMap;
import java.util.List;

public interface MovieInformation{
	
    /**
     * @param 
     * 需要提供所有电影的信息。  
	         返回数组String[电影index][0：名称，1：本地文件地址，2：国家，3：类型，4：图片地址，5：预留变量1，6：预留变量2）]
	   
	**/
	public abstract String[][] getAllMovieInformation();
	
	
	/**
	 * @param
	 * 添加新电影
	        返回数组 [0：名称，1：本地文件地址，2：国家，3：类型，4：图片地址，5：预留变量1，6：预留变量2）]
	**/
	public abstract void addNewFilm(String[] newFilmInformation);
	
	
	/**
	 * @param
	 * 删除电影
	 * 输入int： 0（按index删除） 1（按名称删除）
	 *    inform： 要删除电影的index或名称
	**/
	public abstract void deleteFilm(int type,String inform);
	
	
	/**
	 *@param
	 *查找电影
	 *返回一个HashMap Key为电影index（int） value为本地文件地址（String）
	 *输入name为电影名字；如查找不成功则返回null
	 */
	public abstract HashMap searchMovie(String name);
	
	/**
	 * @param
	 * 返回所有电影名称
	 * **/
	public List getAllMovieName();
}
