package com.onehealth.patientmanagement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;
import com.onehealth.patientmanagement.repository.PatientProfileRepository;
import com.onehealth.patientmanagement.service.PatientProfileService;

@Service
public class PatientProfileServiceImpl implements PatientProfileService {
	
	@Autowired
	private PatientProfileRepository patientProfileRepository;
	
	@Override
	public List<PatientProfile> getAllPatientsByUserId(long userId) throws ProfileNotFoundException {
		// TODO Auto-generated method stub
		return patientProfileRepository.findByUserId(userId);
	}

	@Override
	public PatientProfile getPatientProfileById(long patientId) throws ProfileNotFoundException {
		// TODO Auto-generated method stub
		PatientProfile patientProfile= patientProfileRepository.findById(patientId).orElseThrow(()->new ProfileNotFoundException("Profile Does Not Exist"));
		return patientProfile;
	}

	@Override
	public PatientProfile createPatientProfile(PatientProfile patient) {
		// TODO Auto-generated method stub
		return patientProfileRepository.save(patient);
	}

	@Override
	public PatientProfile updatePatientProfile(long patientId, PatientProfile patient) throws ProfileNotFoundException {
		// TODO Auto-generated method stub
		PatientProfile patientProfile= patientProfileRepository.findById(patientId).orElseThrow(()->new ProfileNotFoundException("Profile Does Not Exist"));
		
		patientProfile.setAddress(patient.getAddress());
		return patientProfile;
	}

	@Override
	public void deletePatientProfile(long patientId) throws ProfileNotFoundException {
		// TODO Auto-generated method stub
		
		PatientProfile patientProfile= patientProfileRepository.findById(patientId).orElseThrow(()->new ProfileNotFoundException("Profile Does Not Exist"+patientId));
		
		patientProfileRepository.deleteById(patientId);
		
	}

//	@Override
//	public void deleteAllPatientProfile(long userId) throws ProfileNotFoundException {
//		// TODO Auto-generated method stub
//		List<PatientProfile> patientProfile= patientProfileRepository.findByUserId(userId);
//		
//		if(patientProfile.isEmpty()) {
//			throw new ProfileNotFoundException("No Patients Present for this User"+userId);
//		}else {
//			
////			patientProfileRepository.deleteByUserId(patientProfile);
//			patientProfileRepository.delete(patientProfile);
//		}
		
		
		
//	}

}
