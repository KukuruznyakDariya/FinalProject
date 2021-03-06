package com.kukuruznyak.bettingcompany.command.impl.participant;

import com.kukuruznyak.bettingcompany.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command returns a page for creating a participant
 */
public class GetAddParticipantPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(PARTICIPANT);
        return pagesResourceBundle.getString(ADD_PARTICIPANT_PAGE);
    }
}
