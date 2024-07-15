package edu.web.news.logic;

import java.util.List;

import edu.web.news.bean.News;

public interface NewsLogic {

	 boolean addNews(News news) throws LogicException;
	
	 void deleteNews(int id) throws LogicException;
	
	 void editNews(News news, int id) throws LogicException;
	 
	 News findNewsById(int id) throws LogicException;
	
	 List<News> getNews() throws LogicException;
	 
	 List<News> getNewsByCategory(String category) throws LogicException;
 }
