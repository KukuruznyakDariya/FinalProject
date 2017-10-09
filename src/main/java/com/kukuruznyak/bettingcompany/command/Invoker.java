package com.kukuruznyak.bettingcompany.command;

import com.kukuruznyak.bettingcompany.command.impl.HomeCommand;
import com.kukuruznyak.bettingcompany.command.impl.bet.MoveOutcomeCommand;
import com.kukuruznyak.bettingcompany.command.impl.bet.SetBetCommand;
import com.kukuruznyak.bettingcompany.command.impl.bet.ShowBetsCommand;
import com.kukuruznyak.bettingcompany.command.impl.event.*;
import com.kukuruznyak.bettingcompany.command.impl.get.*;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.CreateParticipantCommand;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.DeleteParticipantCommand;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.EditParticipantCommand;
import com.kukuruznyak.bettingcompany.command.impl.patricipant.ShowParticipantsCommand;
import com.kukuruznyak.bettingcompany.command.impl.tournament.*;
import com.kukuruznyak.bettingcompany.command.impl.user.CreateUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.DeleteUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.EditUserCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.LogoutCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.RegisterCommand;
import com.kukuruznyak.bettingcompany.command.impl.user.authorization.SignInCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class Invoker {
    private static Invoker instance;
    private Map<String, Command> commandMap = new HashMap<>();

    private Invoker() {
        commandMap.put("home", new HomeCommand());
        commandMap.put("login", new GetLoginPageCommand());
        commandMap.put("signIn", new SignInCommand());
        commandMap.put("register", new GetRegisterPageCommand());
        commandMap.put("join", new RegisterCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("profile", new GetProfilePageCommand());
        commandMap.put("editUser", new EditUserCommand());
        commandMap.put("users", new GetStaffPageCommand());
        commandMap.put("deleteUser", new DeleteUserCommand());
        commandMap.put("addUser", new GetAddUserPageCommand());
        commandMap.put("createUser", new CreateUserCommand());

        commandMap.put("betsHistory", new ShowBetsCommand());//TODO
        commandMap.put("moveOutcome", new MoveOutcomeCommand());
        commandMap.put("setBet", new SetBetCommand());

        commandMap.put("events", new ShowEventsCommand());
        commandMap.put("addEvent", new GetAddEventPageCommand());
        commandMap.put("createEvent", new CreateEventCommand());
        commandMap.put("editEvent", new GetEditEventPageCommand());
        commandMap.put("changeStatus", new ChangeEventStatusCommand());
        commandMap.put("applyCoefficient", new ApplyCoefficientCommand());

        commandMap.put("participants", new ShowParticipantsCommand());
        commandMap.put("addParticipant", new GetAddParticipantPageCommand());
        commandMap.put("createParticipant", new CreateParticipantCommand());
        commandMap.put("editParticipant", new GetEditParticipantPageCommand());
        commandMap.put("updateParticipant", new EditParticipantCommand());
        commandMap.put("deleteParticipant", new DeleteParticipantCommand());
        commandMap.put("moveParticipant", new MoveParticipantInTournamentCommand());

        commandMap.put("selectTournament", new SelectTournamentCommand());
        commandMap.put("tournaments", new ShowTournamentsCommand());
        commandMap.put("addTournament", new GetAddTournamentPageCommand());
        commandMap.put("createTournament", new CreateTournamentCommand());
        commandMap.put("editTournament", new GetEditTournamentPageCommand());
        commandMap.put("updateTournament", new EditTournamentCommand());
        commandMap.put("deleteTournament", new DeleteTournamentCommand());
    }

    public static Invoker getInstance() {
        if (instance == null) {
            synchronized (Invoker.class) {
                if (instance == null) {
                    instance = new Invoker();
                }
            }
        }
        return instance;
    }

    public String invoke(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        if (command == null) {
            command = "home";
        }
        return commandMap.get(command).execute(request, response);
    }
}
