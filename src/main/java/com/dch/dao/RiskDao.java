package com.chc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chc.model.Risk;

public interface RiskDao extends JpaRepository<Risk, Long>{}
