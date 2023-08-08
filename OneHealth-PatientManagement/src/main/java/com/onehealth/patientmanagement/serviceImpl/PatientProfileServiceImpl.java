package com.onehealth.patientmanagement.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;
import com.onehealth.patientmanagement.repository.PatientProfileRepository;
import com.onehealth.patientmanagement.service.PatientProfileService;

@Service
/**
 * Implementation of the PatientProfileService interface.
 * This class provides methods for interacting with patient profiles in the database.
 */
public class PatientProfileServiceImpl implements PatientProfileService {

    private static final Logger logger = LoggerFactory.getLogger(PatientProfileServiceImpl.class);

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    @Override
    public List<PatientProfile> getAllPatientsByUserId(long userId) throws ProfileNotFoundException {
        logger.info("Fetching all patient profiles for user ID: {}", userId);

        List<PatientProfile> patientProfiles = patientProfileRepository.findByUserId(userId);

        if (patientProfiles.isEmpty()) {
            logger.warn("No patient profiles found for user ID: {}", userId);
            throw new ProfileNotFoundException("No profiles found for user ID: " + userId);
        }

        return patientProfiles;
    }

    @Override
    public PatientProfile getPatientProfileById(long patientId) throws ProfileNotFoundException {
        logger.info("Fetching patient profile by ID: {}", patientId);

        PatientProfile patientProfile = patientProfileRepository.findById(patientId)
                .orElseThrow(() -> {
                    logger.error("Profile not found for ID: {}", patientId);
                    return new ProfileNotFoundException("Profile Does Not Exist");
                });

        return patientProfile;
    }

    @Override
    public PatientProfile createPatientProfile(PatientProfile patient) {
        logger.info("Creating new patient profile");

        return patientProfileRepository.save(patient);
    }

    @Override
    public PatientProfile updatePatientProfile(long patientId, PatientProfile patient) throws ProfileNotFoundException {
        logger.info("Updating patient profile with ID: {}", patientId);

        PatientProfile patientProfile = patientProfileRepository.findById(patientId)
                .orElseThrow(() -> {
                    logger.error("Profile not found for ID: {}", patientId);
                    return new ProfileNotFoundException("Profile Does Not Exist");
                });

        patientProfile.setAddress(patient.getAddress());

        return patientProfile;
    }

    @Override
    public void deletePatientProfile(long patientId) throws ProfileNotFoundException {
        logger.info("Deleting patient profile with ID: {}", patientId);

        if (!patientProfileRepository.existsById(patientId)) {
            logger.error("Profile not found for ID: {}", patientId);
            throw new ProfileNotFoundException("Profile Does Not Exist: " + patientId);
        }

        patientProfileRepository.deleteById(patientId);
    }
}
