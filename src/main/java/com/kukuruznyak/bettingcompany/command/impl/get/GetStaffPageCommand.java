package com.kukuruznyak.bettingcompany.command.impl.get;

import com.kukuruznyak.bettingcompany.command.Command;
import com.kukuruznyak.bettingcompany.entity.user.User;
import com.kukuruznyak.bettingcompany.entity.user.UserRole;
import com.kukuruznyak.bettingcompany.exception.ApplicationException;
import com.kukuruznyak.bettingcompany.service.factory.ServiceFactory;
import com.kukuruznyak.bettingcompany.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetStaffPageCommand extends Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ApplicationException {
        User authorizedUser = (User) request.getSession().getAttribute("user");
        if (authorizedUser == null || !authorizedUser.getUserRole().equals(UserRole.ADMINISTRATOR)) {
            LOGGER.error("Unexpected request!");
            throw new ApplicationException("Unexpected request!");
        }
        List<User> staff = userService.getStaff();
        request.getSession().setAttribute("users", staff);
        return pagesResourceBundle.getString("userList");
    }
}