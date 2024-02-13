package com.example.jdbc.app;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerDao {
    private  final NamedParameterJdbcTemplate template;


    public CustomerDao(NamedParameterJdbcTemplate template) {
        this.template = template;

    }

    public Long createdCustomer(Customer customer){
        String sql = "INSERT INTO customer (fio, phone, adress) VALUES (:fio, :phone, :adress) RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("adress", customer.getAdress());


        return  template.queryForObject(sql, parameterSource, Long.class);
    }

    public Customer getCustomer(long id){
        String sql = "SELECT * FROM customer WHERE customer.id =:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return  template.queryForObject(sql, parameterSource, (rs, rowNum)->{
            Customer customer = new Customer();
            customer.setId(rs.getLong("id"));
            customer.setFio(rs.getString("fio"));
            customer.setPhone(rs.getString("phone"));
            customer.setAdress(rs.getString("adress"));

            customer.setCreated(rs.getTimestamp("created").toLocalDateTime());
            return  customer;
        });
    }

    public void editCustomer(Customer customer){
        String sql = " UPDATE customer SET fio =:fio, adress=:adress, phone=:phone WHERE id =:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().
                addValue("id", customer.getId())
                 .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("adress", customer.getAdress());
        template.update(sql, sqlParameterSource);
    }

    public void delete(Long id){
        String sql = "DELETE FROM customer WHERE id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        template.update(sql,sqlParameterSource);
    }

}
