package edu.web.news.controller.command.impl;

import java.io.IOException;

import edu.web.news.bean.AuthInfo;
import edu.web.news.bean.User;
import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.UserLogic;
import edu.web.news.util.TokenGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAuth implements Command {

	private final UserLogic logic = LogicProvider.getInstance().getUserLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		User user;
		try {
			user = logic.checkAuth(new AuthInfo(login, password));
			if (user != null) {
				HttpSession session = (HttpSession) request.getSession(true);
				session.setAttribute("user", user);
				String rememberMe = request.getParameter("remember-me");
				if (rememberMe != null) {
					Cookie cookie = new Cookie("rememberMe", TokenGenerator.generateToken(25));
					cookie.setHttpOnly(true);
					cookie.setSecure(true);
					response.addCookie(cookie);
				}
				response.sendRedirect("Controller?command=go_to_start_page");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("authMessage", "Такого пользователя не существует!");
				session.setAttribute("messageType", "success");
				response.sendRedirect("Controller?command=go_to_authentication_page");
			}
		} catch (LogicException e) {
			HttpSession session = request.getSession();
			session.setAttribute("authMessage", "Что-то пошло не так!");
			session.setAttribute("messageType", "success");
			response.sendRedirect("Controller?command=go_to_authentication_page");
		}
	}
}