package com.kukuruznyak.bettingcompany.dao.impl.jdbc.mysql;

import com.kukuruznyak.bettingcompany.dao.ClientDao;
import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import com.kukuruznyak.bettingcompany.entity.user.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {
    private static MySqlClientDaoImpl instance;

    public static MySqlClientDaoImpl getInstance() {
        if (instance == null) {
            instance = new MySqlClientDaoImpl();
        }
        return instance;
    }

    private MySqlClientDaoImpl() {
        super(Client.class.getSimpleName());
    }

    @Override
    protected Client fillModel(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Client model) throws SQLException {

    }
}
