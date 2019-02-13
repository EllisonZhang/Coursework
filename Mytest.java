package application;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

public class Mytest {
	private  MyCrawler testCrawler;
	ArrayList<Page> pages;
	static List<Object> datas;
	CrawlConfig config;
	static int numberOfCrawlers = 1; 
    static CrawlController controller;

	@BeforeClass
	public static void setup () throws Exception {
	        String crawlStorageFolder = "data/";
	        // @SE(M) Glasgow: you do not need to change this to detect the bugs

	        CrawlConfig config = new CrawlConfig();
	        config.setCrawlStorageFolder(crawlStorageFolder); 

	        config.setIncludeBinaryContentInCrawling(false); //SE(M) Glasgow, you do not need to change this to observe the bugs
	 
	        config.setMaxDepthOfCrawling(-1); // SE(M) Glasgow, you do not need to change this to observe the bugs

	        config.setMaxOutgoingLinksToFollow(5000);	        
	        /*
	         * Instantiate the controller for this crawl.
	         */
	        PageFetcher pageFetcher = new PageFetcher(config);                             
	        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
	        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
	        controller = new CrawlController(config, pageFetcher, robotstxtServer);
	        controller.addSeed("http://www.dcs.gla.ac.uk/~bjorn/sem20172018/ae2public/Machine_learning.html");
		    controller.start(MyCrawler.class, numberOfCrawlers); 
		    datas = controller.getCrawlersLocalData();   
		
	}      
	
	@Test
	public void testGeyOutGoingURL() {
		           
	    @SuppressWarnings("unchecked")
		ArrayList<Page> pages = (ArrayList<Page>) datas.get(0);
	    Page testPage = pages.get(0);
	    HtmlParseData htmlParseData = (HtmlParseData) testPage.getParseData();
	    Set<WebURL> links = htmlParseData.getOutgoingUrls();
	    
	    // the Machine_learning page should have 6 outgoing URL
		assertTrue(links.size()==6); 
		assertTrue(links.size()==4);  
	}
	
	@Test
	public void testVistPrivatePage() {

		ArrayList<Page> pages = (ArrayList<Page>) datas.get(0);
		for (Page page:pages) {
			assertNotEquals(page.getWebURL().toString(),"http://www.dcs.gla.ac.uk/~bjorn/sem20172018/ae2private/IDA.html");
		}
	}
	
	@Test
	public void testTextContentOutPut() {
		String text = null;
		for (Object data: datas) {            	
        	try {
            	@SuppressWarnings("unchecked")
				ArrayList<Page> pages = (ArrayList<Page>) data;   	
            	for (Page page: pages) {    
            		 if (page.getParseData() instanceof TextParseData) {
            			 TextParseData textParseData = (TextParseData) page.getParseData();
            			 text = textParseData.getTextContent();	
            		 }         		
            	}	            	
        	} catch (ClassCastException e) {
            	
            }	     	
        }   
		
		String testData = "hot,0,5,2.3,6;\r\n" + 
				"cold,10,5,2,2;\r\n" + 
				"cold,1,5,2.4,88;\r\n" + 
				"cold,0,15,2,2;\r\n" + 
				"hot,2,5,2,42;\r\n" + 
				"hot,0,5,22,12;\r\n" + 
				"cold,3,2,2,2;";
		String testData2 = "hot,1,5,2.3,6;\r\n" + 
				"cold,11,5,2,2;\r\n" + 
				"cold,1,5,2.4,88;\r\n" + 
				"cold,1,15,2,2;\r\n" + 
				"hot,2,5,2,42;\r\n" + 
				"hot,1,5,22,12;\r\n" + 
				"cold,3,2,2,2;" ;
		
		assertEquals(testData2,text);
		assertEquals(testData,text);
	}
	
	@Test
	public void testHtmlContentOutPut() {
		String text = null;
		String html = null;
		boolean test1 = false;
		boolean test2 = false;
		char[] testChars = null;
		for (Object data: datas) {            	
        	try {
            	@SuppressWarnings("unchecked")
				ArrayList<Page> pages = (ArrayList<Page>) data;   	
            	for (Page page: pages) {    
            		 if (page.getParseData() instanceof HtmlParseData) {
            			 if( page.getWebURL().toString().equals("http://www.dcs.gla.ac.uk/~bjorn/sem20172018/ae2public/Deep_learning.html")){
            				 HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
    	                     text = htmlParseData.getText();	
    	                     html = htmlParseData.getHtml(); 
            			 }
            			
            		 }         		
            	}	            	
        	} catch (ClassCastException e) {
            	
            }	     	
        }   
	
		testChars = html.toCharArray();
		for (char testChar:testChars) {
			if(testChar=='D') {
				test1 = true;
			}
			if(testChar=='d') {
				test2 = true;
			}
		}
	
		assertTrue(test1);
		assertTrue(test2); 
	}
}
