package com.dch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dch.model.Topics;

public interface TopicsDao extends JpaRepository<Topics, Long>{}
