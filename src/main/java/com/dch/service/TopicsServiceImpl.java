package com.dch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dch.dao.TopicsDao;
import com.dch.model.Topics;

@Service
@Transactional
public class TopicsServiceImpl implements TopicsService{
	    @Autowired
	    private TopicsDao topicsDao;

	    @Override
	    public List<Topics> findAll() {
	        return topicsDao.findAll();
	    }

	    @Override
	    public Topics findById(Long id) {
	        return topicsDao.getOne(id);
	    }

	    @Override
	    public void save(Topics topics) {
	        topicsDao.save(topics);
	    }
	}

