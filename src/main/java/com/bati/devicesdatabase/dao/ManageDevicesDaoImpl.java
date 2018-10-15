package com.bati.devicesdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

import com.bati.devicesdatabase.domain.Device;

@Repository(value = "manageDevicesDao")
public class ManageDevicesDaoImpl implements ManageDevicesDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	@Transactional
	public int createNewDevice(Device device) throws Exception {
		final String sql = "insert into devices(serial, devicetype, manufacturername, manufactureddate, testeddate, testername, additionalinfo, parkingobjectid)"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String [] {"id"});
				ps.setInt(1, device.getSerial());
				ps.setString(2, device.getDeviceType());
				ps.setString(3, device.getManufacturerName());
				ps.setString(4, device.getManufacturedDate().toString());
				ps.setString(5, device.getTestedDate().toString());
				ps.setString(6, device.getTesterName());
				ps.setString(7, device.getAdditionalInfo());
				ps.setInt(8, device.getParkingObjectId());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public Device getDevice(int deviceId) {
		final String sql = "select * from devices where id = :deviceId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("deviceId", deviceId);
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new RowMapper<Device>() {
			
			@Override
			public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
				Device d = new Device();
				d.setId(rs.getInt("id"));
				d.setSerial(rs.getInt("serial"));
				d.setDeviceType(rs.getString("devicetype"));
				d.setManufacturedDate(LocalDate.parse(rs.getString("manufactureddate")));
				d.setManufacturerName(rs.getString("manufacturername"));
				d.setTestedDate(LocalDate.parse(rs.getString("testeddate")));
				d.setTesterName(rs.getString("testername"));
				d.setAdditionalInfo(rs.getString("additionalinfo"));
				d.setParkingObjectId(rs.getInt("parkingobjectid"));
				return d;
			}
		});
	}

	@Override
	public List<Device> getAllDevices() {
		final String sql = "select * from devices";
		return jdbcTemplate.query(sql, new RowMapper<Device>() {

			@Override
			public Device mapRow(ResultSet rs, int rowNum) throws SQLException {
				Device d = new Device();
				d.setId(rs.getInt("id"));
				d.setSerial(rs.getInt("serial"));
				d.setDeviceType(rs.getString("devicetype"));
				d.setManufacturedDate(LocalDate.parse(rs.getString("manufactureddate")));
				d.setManufacturerName(rs.getString("manufacturername"));
				d.setTestedDate(LocalDate.parse(rs.getString("testeddate")));
				d.setTesterName(rs.getString("testername"));
				d.setAdditionalInfo(rs.getString("additionalinfo"));
				d.setParkingObjectId(rs.getInt("parkingobjectid"));
				return d;
			}
		});
	}

	@Override
	public void deleteDevice(int deviceId) {
		final String sql = "delete from devices where id = :deviceId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("deviceId", deviceId);
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public void updateDevice(Device device) {
		jdbcTemplate.update("update devices set serial = ? where id = ?", device.getSerial(), device.getId());
		jdbcTemplate.update("update devices set devicetype = ? where id = ?", device.getDeviceType(), device.getId());
		jdbcTemplate.update("update devices set manufacturername = ? where id = ?", device.getManufacturerName(), device.getId());
		jdbcTemplate.update("update devices set manufactureddate = ? where id = ?", device.getManufacturedDate(), device.getId());
		jdbcTemplate.update("update devices set testeddate = ? where id = ?", device.getTestedDate(), device.getId());
		jdbcTemplate.update("update devices set testername = ? where id = ?", device.getTesterName(), device.getId());
		jdbcTemplate.update("update devices set additionalinfo = ? where id = ?", device.getAdditionalInfo(), device.getId());
		jdbcTemplate.update("update devices set parkingobjectid = ? where id = ?", device.getParkingObjectId(), device.getId());
	}

}
