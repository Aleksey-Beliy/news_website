package edu.web.news.controller.command;

import java.util.HashMap;
import java.util.Map;

import edu.web.news.controller.command.impl.AddNews;
import edu.web.news.controller.command.impl.AssignRole;
import edu.web.news.controller.command.impl.ChangeLocale;
import edu.web.news.controller.command.impl.DeleteNews;
import edu.web.news.controller.command.impl.DoAuth;
import edu.web.news.controller.command.impl.DoRegistration;
import edu.web.news.controller.command.impl.GetNewsByCategory;
import edu.web.news.controller.command.impl.GoToAdminPage;
import edu.web.news.controller.command.impl.GoToAssignRolePage;
import edu.web.news.controller.command.impl.GoToAuthenticationPage;
import edu.web.news.controller.command.impl.GoToEditPage;
import edu.web.news.controller.command.impl.GoToRegistrationPage;
import edu.web.news.controller.command.impl.GoToStartPage;
import edu.web.news.controller.command.impl.LogOut;
import edu.web.news.controller.command.impl.NoSuchCommand;
import edu.web.news.controller.command.impl.EditNews;
import edu.web.news.controller.command.impl.FindUserByEmail;
import edu.web.news.controller.command.impl.ViewNews;

public class CommandProvider {

	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GO_TO_START_PAGE, new GoToStartPage());
		commands.put(CommandName.GO_TO_AUTHENTICATION_PAGE, new GoToAuthenticationPage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
		commands.put(CommandName.GO_TO_EDIT_PAGE, new GoToEditPage());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.GO_TO_ASSIGN_ROLE_PAGE, new GoToAssignRolePage());
		commands.put(CommandName.DO_AUTH, new DoAuth());
		commands.put(CommandName.ASSIGN_ROLE, new AssignRole());

		commands.put(CommandName.FIND_USER_BY_EMAIL, new FindUserByEmail());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.DELETE_NEWS, new DeleteNews());
		commands.put(CommandName.EDIT_NEWS, new EditNews());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
		commands.put(CommandName.VIEW_NEWS, new ViewNews());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.LOGOUT, new LogOut());
		commands.put(CommandName.GET_NEWS_BY_CATEGORY, new GetNewsByCategory());

	}

	public Command takeCommand(String userCommand) {
		CommandName commandName;
		Command command;

		try {
			commandName = CommandName.valueOf(userCommand.toUpperCase());
			command = commands.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;
	}
}
