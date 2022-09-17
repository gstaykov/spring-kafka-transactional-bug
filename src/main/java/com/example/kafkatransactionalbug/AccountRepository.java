package com.example.kafkatransactionalbug;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AccountRepository
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void insert()
    {
        String sql = """
                INSERT INTO account (name) VALUES (:name)
                """;

        SqlParameterSource params = new MapSqlParameterSource().addValue("name", "example-name-" + UUID.randomUUID().toString());

        namedParameterJdbcTemplate.update(sql, params);
    }
}
