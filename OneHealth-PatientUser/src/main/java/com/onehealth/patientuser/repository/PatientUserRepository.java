package com.onehealth.patientuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onehealth.patientuser.entity.PatientUser;

public interface PatientUserRepository extends JpaRepository<PatientUser, Long> {
	
	
}