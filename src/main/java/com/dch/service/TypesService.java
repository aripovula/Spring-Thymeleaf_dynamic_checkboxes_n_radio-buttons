package com.dch.service;

import java.util.List;

import com.dch.model.Types;

public interface TypesService {

	List<Types> findAll();

	Types findById(Long id);

	void save(Types types);

}
