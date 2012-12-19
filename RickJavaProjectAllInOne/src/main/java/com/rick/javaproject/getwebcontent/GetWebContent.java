package com.rick.javaproject.getwebcontent;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetWebContent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		go2();
	}

	private static void go2(){
		//Below link is for the multiple link page: total 5page
		//String concreateURL = "http://www.yixinhealth.com/%E5%8C%96%E9%AA%8C%E5%92%8C%E6%A3%80%E6%9F%A5/%E5%BF%83%E7%94%B5%E5%9B%BE/";  
		
		//Below link is for multiple title content page: total 3 titles 
		String concreateURL = "http://www.yixinhealth.com/%E5%8C%96%E9%AA%8C%E5%92%8C%E6%A3%80%E6%9F%A5/%E5%BF%83%E7%94%B5%E5%9B%BE/"; 
		Connection c = Jsoup.connect(concreateURL); 
		c.timeout(10000);
		try {
			Document doc = c.get();
			//System.out.println(doc.html());  
			//System.out.println(doc.getElementById("content_area").html());
			//Element eles = doc.getElementById("content_area");
			//System.out.println(eles.html());
			
			//Below code is for the multiple link page
			//Elements eles2 = eles.getElementsByAttributeValue("style", "text-align: center;");
			//System.out.println(eles2.html());
			/*for(Element ele: eles2){
				Elements link = ele.getElementsByTag("a");
				//System.out.println(link.html());
				for (int i=0;i<link.size();i++) {
					System.out.println(link.get(i).attr("href"));
				}
			}*/
			
			
			//System.out.println(eles2.html());
			Element eles = doc.getElementById("content_area");
			Elements eles2 = eles.getElementsByTag("img");
			for(int i=0;i<eles2.size();i++){
				eles2.get(i).parent().attr("style", "text-align:center");
				eles2.get(i).parent().parent().nextElementSibling().attr("style", "text-align:center");
				eles2.get(i).attr("style", "width:50%");
			}
			
			Elements eles3 = eles.getElementsByClass("j-header");
			for (Element ele : eles3) {
				Elements eleHeader = ele.getElementsByTag("h2");
				eleHeader.attr("style", "text-align:center");
			}
			System.out.println(eles.html());
			//Elements eles3 = eles.getElementsByClass("n*j-imageSubtitle");
			//System.out.println(eles3.size());
			/*for(int i=0;i<eles3.size();i++){
				eles3.get(i).attr("style", "text-align:center;width:50%");
			}
			System.out.println(eles.html());*/
			//System.out.println(eles2.get(0).attr("src"));
			/*Element eles = doc.getElementById("content_area");
			Elements eles2 = eles.getElementsByClass("j-header");
			for (Element ele : eles2) {
				Elements eleHeader = ele.getElementsByTag("h2");
				System.out.println("Title---->"+eleHeader.html());  
			}
			Elements elesBody = eles.getElementsByClass("j-text");
			System.out.println("Body---->"+elesBody.html());  */
			//System.out.println(eles2.html());  
            /*List<String> nameList = new ArrayList<String>();  
            for (Element ele : eles) {
            	String text = ele.select("h*").text();
            	System.out.println(text);  
                String text = ele.select("span").first().text();  
                if (text.length() > 1 && text.startsWith("▲")) {  
  
                    if (Integer.parseInt(text.substring(1)) > 30) {  
                        // 在这里.html()和.text()方法获得的内容是一样的  
                        System.out.println(ele.select("a").first().html());  
                        nameList.add(ele.select("a").first().text());  
                    }  
                }  
            }  */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
