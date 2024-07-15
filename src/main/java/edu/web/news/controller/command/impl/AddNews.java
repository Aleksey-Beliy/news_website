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


public class AddNews implements Command {

	private final NewsLogic logic = LogicProvider.getInstance().getNewsLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = (request.getParameter("title"));
		String category = (request.getParameter("category"));
		String content = (request.getParameter("content"));
		String imgPath = (request.getParameter("imgPath"));
		try {
			if (logic.addNews(new News(title, category, content, "images/" + imgPath))) {

				request.setAttribute("addMessage", "Новость добавлена");
				request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
			} else {

				request.setAttribute("addMessage", "Новость не добавлена");
				request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
			}

		} catch (LogicException e) {
			request.setAttribute("addMessage", "Что-то пошло не так");
			request.getRequestDispatcher("WEB-INF/jsp/admin.jsp").forward(request, response);
		}

	}
}
