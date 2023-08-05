package com.onehealth.patientuser.service;

import java.util.List;

import com.onehealth.patientuser.entity.PatientUser;
import com.onehealth.patientuser.exception.DatabaseException;
import com.onehealth.patientuser.exception.RecordNotFoundException;

public interface PatientUserService {

	List<PatientUser> getAllUsers();

	PatientUser getUserById(Long userId) throws RecordNotFoundException;

	PatientUser createUser(PatientUser user) throws DatabaseException;

	PatientUser updateUser(Long userId, PatientUser user) throws RecordNotFoundException;

	void deleteUser(Long userId) throws RecordNotFoundException;
}
