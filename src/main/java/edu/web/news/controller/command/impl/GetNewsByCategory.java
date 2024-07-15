package edu.web.news.controller.command.impl;

import java.io.IOException;
import java.util.List;

import edu.web.news.bean.News;
import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.NewsLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetNewsByCategory implements Command {

	private final NewsLogic logic = LogicProvider.getInstance().getNewsLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String category = request.getParameter("category");
		try {
			List<News> newsList = logic.getNewsByCategory(category);
			request.setAttribute("newsList", newsList);
			request.getRequestDispatcher("WEB-INF/jsp/viewNewsByCategory.jsp").forward(request, response);
		} catch (LogicException e) {
			response.sendRedirect("Controller?command=go_to_start_page");
		}

	}
}
