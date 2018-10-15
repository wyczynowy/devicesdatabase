package com.bati.devicesdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bati.devicesdatabase.dao.ManageAuthoritiesDao;
import com.bati.devicesdatabase.domain.Authority;

@Service(value = "manageAuthoritiesService")
public class ManageAuthoritiesServiceImpl implements ManageAuthoritiesService {

	@Autowired
	@Qualifier(value = "manageAuthoritiesDao")
	ManageAuthoritiesDao manageAuthoritiesDao;
	
	@Override
	public int createAuthority(Authority authority) throws Exception {
		return manageAuthoritiesDao.createAuthority(authority);
	}

	@Override
	public Authority getAuthority(int id) {
		return manageAuthoritiesDao.getAuthority(id);
	}

	@Override
	public List<Authority> getAllAuthorities() {
		return manageAuthoritiesDao.getAllAuthorities();
	}

	@Override
	public void deleteAuthority(int id) {
		manageAuthoritiesDao.deleteAuthority(id);
	}

	@Override
	public void updateAuthority(Authority authority) {
		manageAuthoritiesDao.updateAuthority(authority);
	}

}
