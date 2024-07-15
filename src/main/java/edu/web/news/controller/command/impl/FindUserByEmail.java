package edu.web.news.controller.command.impl;

import java.io.IOException;

import edu.web.news.bean.User;
import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.UserLogic;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindUserByEmail implements Command {

	private final UserLogic logic = LogicProvider.getInstance().getUserLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		String email = request.getParameter("email");
		try {
			user = logic.findUserByEmail(email);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/assignRole_page.jsp");
			dispatcher.forward(request, response);
		} catch (LogicException e) {
			request.setAttribute("error", "Пользователь не найден!");
			request.getRequestDispatcher("WEB-INF/jsp/assignRole_page.jsp").forward(request, response);
		}

	}

}
