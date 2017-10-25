package com.dch.service;

import java.util.List;

import com.dch.model.Topics;

public interface TopicsService {

	List<Topics> findAll();

	Topics findById(Long id);

	void save(Topics topics);

}
