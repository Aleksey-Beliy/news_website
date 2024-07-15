package edu.web.news.controller.command.impl;

import java.io.IOException;
import java.util.Locale;

import edu.web.news.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentUrl = request.getHeader("Referer");
		if (currentUrl == null || currentUrl.isEmpty()) {
			currentUrl = "Controller?command=go_to_start_page";
		}

		String language = request.getParameter("lang");
		if (language != null && !language.isEmpty()) {
			Locale locale = new Locale(language);
			request.getSession().setAttribute("locale", locale);
		}

		response.sendRedirect(currentUrl);
	}
}
