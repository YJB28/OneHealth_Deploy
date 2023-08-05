package com.onehealth.patientmanagement.service;

import java.util.List;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;

public interface PatientProfileService {

	List<PatientProfile> getAllPatientsByUserId(long userId) throws ProfileNotFoundException;
	PatientProfile getPatientProfileById(long patientId) throws ProfileNotFoundException;
	PatientProfile createPatientProfile(PatientProfile patient);
	PatientProfile updatePatientProfile(long patientId, PatientProfile patient) throws ProfileNotFoundException;
    void deletePatientProfile(long patientId) throws ProfileNotFoundException;
//    void deleteAllPatientProfile(long userId) throws ProfileNotFoundException;
}
