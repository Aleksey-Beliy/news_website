package edu.web.news.controller.command.impl;

import java.io.IOException;
import java.util.List;
import edu.web.news.bean.News;
import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.NewsLogic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GoToStartPage implements Command {
	private final NewsLogic logic = LogicProvider.getInstance().getNewsLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		List<News> mainNews;

		try {
			mainNews = logic.getNews();
			request.setAttribute("mainNews", mainNews);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/start_page.jsp");
			dispatcher.forward(request, response);
		} catch (LogicException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/start_page.jsp");
			dispatcher.forward(request, response);
		}
	}

}
