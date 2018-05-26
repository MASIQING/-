package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawerTools {
	private static BufferedReader in;
	
	//movieName 输中文电影名  如PA（“头号玩家”）
	public String PA(String movieName){
		
		String URLString;
		
		URLString = "https://baike.baidu.com/item/"+movieName;
	    System.out.println("URL "+URLString);
			String content = "no introduction";
			String result = "";
			Document doc = null;
			try {
				doc = Jsoup.connect(URLString).get();// 百度百科网址
				Elements mainTab = doc.select("div.main_tab");
				Element contents = mainTab.select("div.para").first();// 获取para元素
				content = contents.toString();
				System.out.println(content);
				int a = 0;
				int b = 0;
				content = content.substring(1);
				
			    do {
					a = content.indexOf(">");
					b = content.indexOf("<");
					if(a>=0&&b>=0) 
					   result = result+content.substring(a+1, b).replaceAll("\r|\n", "");
					   //System.out.println(result);
					content = content.substring(b+1);	
				}while(a>=0&&b>=0);
				
				String reg = "[^\\u4e00-\\u9fa5 ,，”“《》\\.。0-9]";
				//result = result.replaceAll(reg, ""); //开启纯中文模式
				int c = result.indexOf("[");
				if(c>=0)
				result = result.substring(0,c)+"。";
			
			} catch (Exception e) {
				//e.printStackTrace();
			}
			return result;
		
	}
}
