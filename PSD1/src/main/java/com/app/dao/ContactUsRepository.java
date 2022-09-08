package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs,Integer> {

}
