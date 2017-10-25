package com.chc.service;

import java.util.List;

import com.chc.model.Risk;

public interface RiskService {

	List<Risk> findAll();

	Risk findById(Long id);

	void save(Risk risk);

}
