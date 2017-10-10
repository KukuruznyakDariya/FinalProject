package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ParticipantDao;
import com.kukuruznyak.bettingcompany.dao.TournamentDao;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactory;
import com.kukuruznyak.bettingcompany.dao.factory.DaoFactoryType;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.tournament.Participant;
import com.kukuruznyak.bettingcompany.entity.tournament.Tournament;
import com.kukuruznyak.bettingcompany.entity.tournament.builder.TournamentBuilder;
import com.kukuruznyak.bettingcompany.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MySqlTournamentDaoImpl extends AbstractDaoImpl<Tournament> implements TournamentDao {
    private static MySqlTournamentDaoImpl instance;
    private static ParticipantDao participantDao = DaoFactory.getDaoFactory(DaoFactoryType.MYSQL).getParticipantDao();

    private static final String LINKED_TABLE_QUERY = "ParticipantLinkTournament";
    private static final String ALL_TOURNAMENT_BY_PARTICIPANTS_QUERY = "getTournaments";

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String COUNTRY_COLUMN = "country";
    private static final String START_DATE_COLUMN = "start_date_and_time";
    private static final String WINNER_COLUMN = "winner";

    public static MySqlTournamentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (MySqlTournamentDaoImpl.class) {
                if (instance == null) {
                    instance = new MySqlTournamentDaoImpl();
                }
            }
        }
        return instance;
    }

    private MySqlTournamentDaoImpl() {
        super(Tournament.class.getSimpleName());
    }

    @Override
    public Tournament getById(Long id) throws PersistenceException {
        Tournament tournament = super.getById(id);
        Collection<Participant> participants = participantDao.getParticipantsByTournament(tournament.getId());
        tournament.setParticipants(participants);
        return tournament;
    }

    @Override
    public Collection<Tournament> getAll() throws PersistenceException {
        Collection<Tournament> tournaments = super.getAll();
        for (Tournament tournament : tournaments) {
            tournament.setParticipants(participantDao.getParticipantsByTournament(tournament.getId()));
        }
        return tournaments;
    }

    @Override
    protected Tournament fillModel(ResultSet resultSet) throws PersistenceException {
        try {
            return new TournamentBuilder()
                    .buildId(resultSet.getLong(ID_COLUMN))
                    .buildName(resultSet.getString(NAME_COLUMN))
                    .buildCountry(resultSet.getString(COUNTRY_COLUMN))
                    .buildBeginningDateAndTime(resultSet.getDate(START_DATE_COLUMN))
                    .buildWinner(resultSet.getString(WINNER_COLUMN))
                    .build();
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Tournament tournament) throws PersistenceException {
        try {
            preparedStatement.setString(1, tournament.getName());
            preparedStatement.setString(2, tournament.getCountry());
            preparedStatement.setDate(3, new java.sql.Date(tournament.getBeginningDateAndTime().getTime()));
            preparedStatement.setString(4, tournament.getWinner());
        } catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    @Override
    public void addParticipant(Long participantId, Long tournamentId) throws PersistenceException {
        participantDao.addTournament(participantId, tournamentId);
    }

    @Override
    public void deleteParticipant(Long participantId, Long tournamentId) throws PersistenceException {
        participantDao.deleteTournament(participantId, tournamentId);
    }

    @Override
    public Collection<Tournament> getTournamentsByParticipant(Long id) throws PersistenceException {
        return super.getAllByConstrain(
                QUERIES.getString(LINKED_TABLE_QUERY +DELIMITER + ALL_TOURNAMENT_BY_PARTICIPANTS_QUERY),
                String.valueOf(id));
    }
}