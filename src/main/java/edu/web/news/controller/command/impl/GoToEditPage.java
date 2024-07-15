package edu.web.news.controller.command.impl;

import java.io.IOException;
import edu.web.news.bean.News;
import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.NewsLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditPage implements Command {

	private final NewsLogic logic = LogicProvider.getInstance().getNewsLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int articleId = Integer.parseInt(request.getParameter("id"));
		try {
			News news = logic.findNewsById(articleId);
			request.setAttribute("article", news);
			request.getRequestDispatcher("WEB-INF/jsp/editNews.jsp").forward(request, response);
		} catch (LogicException e) {
		}
	}

}
