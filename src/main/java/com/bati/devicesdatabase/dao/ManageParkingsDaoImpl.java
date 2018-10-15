package com.bati.devicesdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bati.devicesdatabase.domain.ParkingObject;

@Repository(value = "manageParkingsDao")
public class ManageParkingsDaoImpl implements ManageParkingsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	@Transactional
	public int createNewParkingObject(ParkingObject parkingObject) throws Exception {
		final String sql = "insert into objects(name, address, contact, description)"
				+ "values(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
				ps.setString(1, parkingObject.getName());
				ps.setString(2, parkingObject.getAddress());
				ps.setString(3, parkingObject.getContact());
				ps.setString(4, parkingObject.getDescription());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public ParkingObject getParkingObject(int parkingObjectId) {
		final String sql = "select * from objects where id = :parkingObjectId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("parkingObjectId", parkingObjectId);
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new RowMapper<ParkingObject>() {

			@Override
			public ParkingObject mapRow(ResultSet rs, int rowNum) throws SQLException {
				ParkingObject pa = new ParkingObject();
				pa.setId(rs.getInt("id"));
				pa.setName(rs.getString("name"));
				pa.setAddress(rs.getString("address"));
				pa.setContact(rs.getString("contact"));
				pa.setDescription(rs.getString("description"));
				return pa;
			}
			
		});
	}

	@Override
	public List<ParkingObject> getAllParkingObjects() {
		final String sql = "select * from objects";
		return jdbcTemplate.query(sql, new RowMapper<ParkingObject>() {

			@Override
			public ParkingObject mapRow(ResultSet rs, int rowNum) throws SQLException {
				ParkingObject pa = new ParkingObject();
				pa.setId(rs.getInt("id"));
				pa.setName(rs.getString("name"));
				pa.setAddress(rs.getString("address"));
				pa.setContact(rs.getString("contact"));
				pa.setDescription(rs.getString("description"));
				return pa;
			}
			
		});
	}

	@Override
	public void deleteParkingObject(int parkingObject) {
		final String sql = "delete from objects where id = :parkingObject";
		SqlParameterSource namedParameters = new MapSqlParameterSource("parkingObject", parkingObject);
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public void updateParkingObject(ParkingObject parkingObject) {
		jdbcTemplate.update("update objects set name = ? where id = ?", parkingObject.getName(), parkingObject.getId());
		jdbcTemplate.update("update objects set address = ? where id = ?", parkingObject.getAddress(), parkingObject.getId());
		jdbcTemplate.update("update objects set contact = ? where id = ?", parkingObject.getContact(), parkingObject.getId());
		jdbcTemplate.update("update objects set description = ? where id = ?", parkingObject.getDescription(), parkingObject.getId());
	}

}
