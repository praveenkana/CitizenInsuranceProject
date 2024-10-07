package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nt.entity.PlanEntity;

public interface IDC_PlanRepository extends JpaRepository<PlanEntity, Integer> {
	public String findByCaseNo(Integer caseNo);
}
