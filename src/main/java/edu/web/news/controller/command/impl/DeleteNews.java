package edu.web.news.controller.command.impl;

import java.io.IOException;

import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.NewsLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteNews implements Command {

	private final NewsLogic logic = LogicProvider.getInstance().getNewsLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String articleId = request.getParameter("id");
		if (articleId != null && !articleId.isEmpty()) {
			try {
				int id = Integer.parseInt(articleId);
				logic.deleteNews(id);
				response.sendRedirect("Controller?command=go_to_start_page");
			} catch (LogicException e) {
				response.sendRedirect("Controller?command=go_to_start_page");
			}
		} 
	}
}
