package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDaoImpl implements MemberDao{
	private SqlSessionTemplate sqlSessionTemplate;
	public MemberDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void insert(Member member){
		sqlSessionTemplate.insert("insert", member); 
	}
			
	public void update(Member member){
		sqlSessionTemplate.update("update", member); 
	}
	
	public Member selectByEmail(String email){
		return sqlSessionTemplate.selectOne("selectByEmail", email); 
	}
	
	public List<Member> selectAll(){
		return sqlSessionTemplate.selectList("list"); 
	}
	
	public int count() {
		return sqlSessionTemplate.selectOne("count");
	}
	
	public List<Member> selectByRegdate(Date from, Date to){
		System.out.println(from);
		HashMap<String, Date> map = new HashMap<String, Date>();
		map.put("from", from);
		map.put("to", to);
		return sqlSessionTemplate.selectList("selectByRegdate", map);
	}
	
	public Member selectById(Long id){
		List<Member> results = sqlSessionTemplate.selectList("selectById", id);
		return results.isEmpty() ? null: results.get(0);
	}
	
}
