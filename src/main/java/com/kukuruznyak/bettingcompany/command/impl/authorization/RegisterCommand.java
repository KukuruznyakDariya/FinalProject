package com.kukuruznyak.bettingcompany.command.impl.authorization;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.entity.user.builder.UserBuilder;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {
    private UserService userService = UserService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        try {
            if (userService.getUserByLogin(request.getParameter("login")) != null) {
                throw new ApplicationException("User with login '" + request.getParameter("login") + "' already exist!");
            }
            if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
                throw new ApplicationException("Passwords are not equals!");
            }
            User user = createUser(request);
            if (!userService.isValidUser(user)) {
                throw new ApplicationException("Incorrect user!");
            }
            user = userService.add(user);
            request.getSession().setAttribute("user", user);
            LOGGER.info("New authorization " + user.getLogin() + " joined");
            return pagesResourceBundle.getString("home");
        } catch (ApplicationException e) {
            request.getSession().setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage());
            return pagesResourceBundle.getString("register");
        }
    }


    private User createUser(HttpServletRequest request) {
        return new UserBuilder()
                .buildFirstName(request.getParameter("firstName"))
                .buildLastName(request.getParameter("lastName"))
                .buildLogin(request.getParameter("login"))
                .buildEmail(request.getParameter("email"))
                .buildPassword(request.getParameter("password"))
                .buildUserRole(UserRole.CLIENT)
                .build();
    }
}