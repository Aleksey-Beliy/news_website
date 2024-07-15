package edu.web.news.controller.command.impl;

import java.io.IOException;
import edu.web.news.bean.RegistrationInfo;
import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.UserLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

	private UserLogic logic = LogicProvider.getInstance().getUserLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		try {
			if (logic.registrationUser(new RegistrationInfo(email, name, password))) {
				HttpSession session = request.getSession();
				session.setAttribute("registrationMessage", "Такого пользователь существует!");
				session.setAttribute("messageType", "success");
				response.sendRedirect("Controller?command=go_to_registration_page");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("authMessage", "Регистрация прошла успешно!");
				session.setAttribute("messageType", "success");
				response.sendRedirect("Controller?command=go_to_authentication_page");
			}
		} catch (LogicException e) {
			HttpSession session = request.getSession();
			e.printStackTrace();
			session.setAttribute("registrationMessage", "Что-то не так!");
			session.setAttribute("messageType", "success");
			response.sendRedirect("Controller?command=go_to_registration_page");
		}

	}
}
