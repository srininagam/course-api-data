package com.srini.courseapidata.topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//public interface TopicRepository extends CrudRepository<Topic, Integer>{

@Repository
public class TopicRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
 
	@Transactional(readOnly=true)
    public List<Topic> getAllTopics(){
	    List<Topic> topicList = jdbcTemplate.query(
	            "SELECT TOPIC_ID, TOPIC_NAME, TOPIC_DESC FROM TOPICS ",new RowMapper<Topic>(){

					@Override
					public Topic mapRow(ResultSet arg0, int arg1) throws SQLException {
						Topic topic = new Topic();
						topic.setId(arg0.getInt("TOPIC_ID"));
						topic.setName(arg0.getString("TOPIC_NAME"));
						topic.setDescription(arg0.getString("TOPIC_DESC"));
						return topic;
					}
	            	
	            });
	    return topicList; 
    }
	
    public Topic addTopic(Topic topic) {
        String sql = "INSERT INTO TOPICS " +
                "(TOPIC_NAME, TOPIC_DESC) VALUES (?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, topic.getName());
                ps.setString(2, topic.getDescription());
                return ps;
            }
        }, holder);
 
        int newTopicId = holder.getKey().intValue();
        topic.setId(newTopicId);
        return topic;
    }


	public int updateTopic(int id, Topic topic) {
		String sql = "UPDATE TOPICS SET TOPIC_NAME=? , TOPIC_DESC= ? WHERE TOPIC_ID=? ";
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, topic.getName());
                ps.setString(2, topic.getDescription());
                ps.setInt(3, id);
                return ps;
            }
        });
		return result;
	}

	public int deleteTopic(int id) {
		String sql = "DELETE FROM TOPICS WHERE TOPIC_ID=? ";
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                return ps;
            }
        });
		return result;
	}
}
