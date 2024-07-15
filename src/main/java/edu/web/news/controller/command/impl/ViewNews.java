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

public class ViewNews implements Command {

	private final NewsLogic logic = LogicProvider.getInstance().getNewsLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int articleId = Integer.parseInt(request.getParameter("id"));
		try {
			News article = logic.findNewsById(articleId);
			if (article != null) {
				request.setAttribute("article", article);
				request.getRequestDispatcher("WEB-INF/jsp/viewNews.jsp").forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "News not found");
			}
		} catch (NumberFormatException | LogicException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving news");
		}
	}
}
