package com.sist.web.manager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import com.sist.web.entity.*;

@Component
public class NewsSearchManager {

    public static void main(String[] args) {
		NewsSearchManager m=new NewsSearchManager();
		m.newsFind("맛집");
		// http://localhost/news/list/%EB%A7%9B%EC%A7%91
	}
    public List<NewsVO> newsFind(String fd) {
    	List<NewsVO> list=new ArrayList<NewsVO>();
    	
        String clientId = "hvmjIJyrMWe89UrYfmJX"; //애플리케이션 클라이언트 아이디
        String clientSecret = "9kOFMiwrPm"; //애플리케이션 클라이언트 시크릿


        String text = null;
        try {
            text = URLEncoder.encode(fd, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        //String apiURL = "https://openapi.naver.com/v1/search/news.json?display=50&query=" + text;;    // JSON 결과
        String apiURL = "https://openapi.naver.com/v1/search/news.xml?display=50&query="+ text; // XML 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        System.out.println(responseBody);
        
        try
        {
        	Document doc=Jsoup.parse(responseBody);
        	Elements title=doc.select("rss channel item title");
        	Elements desc=doc.select("rss channel item description");
        	//Elements link=doc.select("rss channel item link");
        	
        	for(int i=0;i<title.size();i++)
        	{
        		NewsVO vo=new NewsVO();
        		vo.setTitle(title.get(i).text());
        		vo.setDesc(desc.get(i).text());
        		list.add(vo);
        		//System.out.println(title.get(i).text());
        		//System.out.println(desc.get(i).text());
        		//System.out.println(link.get(i).text());
        		//System.out.println("=================================");
        	}
        }catch(Exception ex) {}
        
        return list;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}