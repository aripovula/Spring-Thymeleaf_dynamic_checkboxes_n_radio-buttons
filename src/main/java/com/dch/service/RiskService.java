package com.dch.service;

import java.util.List;

import com.dch.model.Risk;

public interface RiskService {

	List<Risk> findAll();

	Risk findById(Long id);

	void save(Risk risk);

}
