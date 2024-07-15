package edu.web.news.controller.command.impl;

import java.io.IOException;

import edu.web.news.controller.command.Command;
import edu.web.news.logic.LogicException;
import edu.web.news.logic.LogicProvider;
import edu.web.news.logic.UserLogic;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AssignRole implements Command {

	private final UserLogic logic = LogicProvider.getInstance().getUserLogic();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		try {
			logic.assignRole(email, role);
			response.sendRedirect("Controller?command=go_to_assign_role_page");
		} catch (LogicException e) {
			
		}

	}
}