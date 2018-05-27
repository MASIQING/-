package Data;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawerTools {
	
	public static void main(String[] args) {
		System.out.println(paYingWen("My_Fair_Lady"));
	}
	
	
	public String paZhongWen(String movieName){
		
		String URLString = "https://baike.baidu.com/item/"+movieName;
		String content = "no introduction";
        Document doc = null;
		try {
			doc = Jsoup.connect(URLString).get();// �ٶȰٿ���ַ
			Elements mainTab = doc.select("div.main_tab");
			Element contents = mainTab.select("div.para").first();// ��ȡparaԪ��
			content = contents.text();	
			int c = content.indexOf("[");
			if(c>=0) content = content.substring(0,c)+"��";
			
		} catch (Exception e) {
			content = "��Ǹ�����޸õ�Ӱ�Ľ���";
			//e.printStackTrace();
		}
		return content;
	}
	
	public static String paYingWen(String movieName) {
		if(movieName.equals("Pans_Labyrinth"))movieName = "Pan's_Labyrinth";
		
		String URLString = "https://en.wikipedia.org/wiki/"+movieName;
		String content = "";
		Document doc = null;
		try {
			doc = Jsoup.connect(URLString).get();
			//System.out.println(doc.toString().substring(0, 100));
			Elements class1 = doc.select("div.mw-body");
			Elements class2 = class1.select("div.mw-body-content");
			Element class3 = class2.select("div.mw-parser-output").first();
			Elements texts = class3.getElementsByTag("p");
		    for (Element text : texts) {
		        String test = text.text();
		       
		        int i = test.indexOf(movieName.substring(0,5).replaceAll("[_]", " "));
		        if(i>=0) {
		        	content = test;
		        	break;
		        }
		    }
		    int c = content.indexOf("[");
			if(c>=0) content = content.substring(0,c)+".";
		} catch (IOException e) {
			content = "Sorry, we don't have the introduction of this movie";
			e.printStackTrace();
		}
		return content;
	}
}
