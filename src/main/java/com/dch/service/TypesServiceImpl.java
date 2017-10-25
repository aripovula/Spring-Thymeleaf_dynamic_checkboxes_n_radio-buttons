package com.dch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dch.dao.TypesDao;
import com.dch.model.Types;

@Service
@Transactional
public class TypesServiceImpl implements TypesService{
	    @Autowired
	    private TypesDao typesDao;

	    @Override
	    public List<Types> findAll() {
	        return typesDao.findAll();
	    }

	    @Override
	    public Types findById(Long id) {
	        return typesDao.getOne(id);
	    }

	    @Override
	    public void save(Types types) {
	    		typesDao.save(types);
	    }
	}

