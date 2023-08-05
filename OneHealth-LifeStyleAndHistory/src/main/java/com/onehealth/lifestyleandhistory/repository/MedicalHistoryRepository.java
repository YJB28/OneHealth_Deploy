package com.onehealth.lifestyleandhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onehealth.lifestyleandhistory.entity.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

	List<MedicalHistory> findByPatientIdAndUserId(Long patientId, Long userId);

}
