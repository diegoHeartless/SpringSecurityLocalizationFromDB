package com.diegoheartless.tools;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MessageRepository extends JdbcDaoSupport {

    JdbcTemplate jdbcTemplate;

    private String lang = "EN";
    private String delimeter = "_";
    private String tableName = "system_message_const";

    private String sqlquery = "select * from tableName where const = ?";

    public MessageRepository(DataSource dataSource) {
        jdbcTemplate= new JdbcTemplate(dataSource);
    }

    private String getSQLQuery(){
        return sqlquery.replace("tableName", tableName+delimeter+lang);
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getMessageFromDB(String code){
        Map<Integer, String> map = jdbcTemplate.query(getSQLQuery(), new Object[]{code}, new MessageMapper()).get(0);
        return map.values().iterator().next();
    }
    public class MessageMapper implements RowMapper<Map<Integer, String>> {
        public Map<Integer, String> mapRow(ResultSet resultSet, int i) throws SQLException {
            Map<Integer, String> map = new HashMap<>();
            map.put(resultSet.getInt("const"),resultSet.getString("message"));
            return map;

        }
    }
}
