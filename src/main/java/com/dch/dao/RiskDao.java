package com.dch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dch.model.Risk;

public interface RiskDao extends JpaRepository<Risk, Long>{}
