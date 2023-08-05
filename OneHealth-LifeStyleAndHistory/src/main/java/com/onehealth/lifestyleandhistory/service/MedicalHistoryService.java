 package com.onehealth.lifestyleandhistory.service;

import java.util.List;

import com.onehealth.lifestyleandhistory.entity.MedicalHistory;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;

public interface MedicalHistoryService {
    List<MedicalHistory> getAllMedicalHistories();
    MedicalHistory getMedicalHistoryByRecordId(Long recordId) throws RecordNotFoundException;
    MedicalHistory createMedicalHistory(MedicalHistory medicalHistory);
    MedicalHistory updateMedicalHistory(Long recordId, MedicalHistory medicalHistory) throws RecordNotFoundException;
    void deleteMedicalHistoryByRecordId(Long recordId) throws RecordNotFoundException;
    void deleteMedicalHistoryByPatientIdAndUserId(Long patientId, Long userId) throws RecordNotFoundException;
}

