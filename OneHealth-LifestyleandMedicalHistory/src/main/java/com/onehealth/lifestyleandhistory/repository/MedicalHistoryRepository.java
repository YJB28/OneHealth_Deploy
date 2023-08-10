package com.onehealth.lifestyleandhistory.repository;

import com.onehealth.lifestyleandhistory.entity.MedicalHistory;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
	Optional<MedicalHistory> findByPatientId(Long patientId);

	List<MedicalHistory> findByPatientIdAndUserId(Long patientId, Long userId);

	boolean existsByPatientId(Long patientId);

	@Modifying
	@Transactional
	void deleteByPatientId(Long patientId);
}
