package com.onehealth.patientuser.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onehealth.patientuser.entity.PatientUser;
import com.onehealth.patientuser.exception.DatabaseException;
import com.onehealth.patientuser.exception.RecordNotFoundException;
import com.onehealth.patientuser.repository.PatientUserRepository;
import com.onehealth.patientuser.service.PatientUserService;

import java.util.List;

@Service
public class PatientUserServiceImpl implements PatientUserService {
	
	@Autowired
    private PatientUserRepository patientUserRepository;

    

    @Override
    public List<PatientUser> getAllUsers() {
        return patientUserRepository.findAll();
    }

    @Override
    public PatientUser getUserById(Long userId) throws RecordNotFoundException {
        return patientUserRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("User not found with userId: " + userId));
    }

    @Override
    public PatientUser createUser(PatientUser user) throws DatabaseException {
        
            return patientUserRepository.save(user);
        
//            throw new DatabaseException("Failed to save user to the database: " + e.getMessage());
        
    }

    @Override
    public PatientUser updateUser(Long userId, PatientUser user) throws RecordNotFoundException {
        PatientUser existingUser = patientUserRepository.findById(userId).orElseThrow(() -> new RecordNotFoundException("User not found with userId so not upadte: " + userId));

        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmailId(user.getEmailId());

            return patientUserRepository.save(existingUser);
        }

        return null;
    }

    @Override
    public void deleteUser(Long userId) throws RecordNotFoundException {
        if (!patientUserRepository.existsById(userId)) {
            throw new RecordNotFoundException("User not found with userId so cant delete: " + userId);
        }

        patientUserRepository.deleteById(userId);
    }
}

