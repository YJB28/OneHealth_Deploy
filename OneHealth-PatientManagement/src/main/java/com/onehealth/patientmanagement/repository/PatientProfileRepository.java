package com.onehealth.patientmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onehealth.patientmanagement.entity.PatientProfile;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {

	List<PatientProfile> findByUserId(long userId);

//	List deleteByUserId(List<PatientProfile> patientProfile);

}
