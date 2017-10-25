package com.dch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dch.dao.RiskDao;
import com.dch.model.Risk;

@Service
@Transactional
public class RiskServiceImpl implements RiskService{
	    @Autowired
	    private RiskDao riskDao;

	    @Override
	    public List<Risk> findAll() {
	        return riskDao.findAll();
	    }

	    @Override
	    public Risk findById(Long id) {
	        return riskDao.getOne(id);
	    }

	    @Override
	    public void save(Risk risk) {
	        riskDao.save(risk);
	    }
	}

