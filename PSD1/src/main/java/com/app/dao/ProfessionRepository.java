package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Profession;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession,Integer>{

	public Profession findByProfessionName(String profName);
}
