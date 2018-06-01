package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


public class CustomerSet {
	
	private Properties userDefault = new Properties();
	private String nowMovieUrl = null;
	private String nowPicUrl = null;
	
	public CustomerSet(){
	    init();
	}
	
	/**初始化业务逻辑，加载配置文件
	 * @throws IOException 
	 **/
	private void init(){
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("userDefault.properties");
			userDefault.load(new InputStreamReader(inputStream, "UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//= movieMenuControl.class.getResourceAsStream("frontSidePicture\\noMovie(CN).jpg");

	public void addNewMovies(String movieName,String movieType,String year) {
		
	}
	
	public void deleteMovies(String movieName) {
		
	}
	
	public void deleteMovies(int id) {
		
	}
	
	public String checkFile(String fileString) {
		int length = fileString.length();
		String name = fileString.substring(length-3, length);
		return name;
	}
	
	public void copyMovieAndPic(String movieUrl,String picUrl,String movieName) {
		if(movieName != null && !"".equals(movieName)) {
		   
		    	String tryMovieUrl = "bin\\FrontSide\\movie\\"+movieName+".mp4";
		    	String tryPicUrl = "bin\\FrontSide\\moviePicture\\"+movieName+".JPG";
				copyFile(tryPicUrl,picUrl);
				copyFile(tryMovieUrl,movieUrl);
				nowMovieUrl = movieName+".mp4";
				nowPicUrl = movieName+".JPG";
			
		}
	}
	
	private void copyFile(String copy,String origin) {
            System.out.println(copy+"  "+origin);
            
            //1.提供读入和写入的文件
            File f1=new File(origin);
            File f2=new File(copy);
            //2.提供相应的流对象
            FileInputStream fis=null;
            FileOutputStream fos=null;
            try{
                fis=new FileInputStream(f1);
                fos=new FileOutputStream(f2);
                //3.实现复制
                byte[]b=new byte[200];
                int len;
                while((len=fis.read(b))!=-1){
                    fos.write(b, 0, len);
                }    
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if(fis!=null){
                    try {
                        //关闭输入流
                        fis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(fos!=null){
                    try {
                        //关闭输出流
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
	    }

	
	/**改变航空公司主题，在下次启动时生效**/
	public void changeAirCompanyTheme(String companyName, String companyPic1,
	    String companyPic2, String companyVideo) {
		
		String newPic1 = "bin\\FrontSide\\frontSidePicture\\"+companyName+"1.jpg";
		String newPic2 = "bin\\FrontSide\\frontSidePicture\\"+companyName+"2.jpg";
		String newVideo = "bin\\FrontSide\\movie\\"+companyName+".mp4";
		
		copyFile(newPic1 , companyPic1);
		copyFile(newPic2 , companyPic2);
		copyFile(newVideo , companyVideo);
		
		newPic1 = companyName+"1.jpg";
		newPic2 = companyName+"2.jpg";
		newVideo = companyName+".mp4";
		
		userDefault.setProperty("companyName", companyName);
		userDefault.setProperty("companyPicture1", newPic1);
		userDefault.setProperty("companyPicture2", newPic2);
		userDefault.setProperty("companyVideo", newVideo);
		
	}
}
