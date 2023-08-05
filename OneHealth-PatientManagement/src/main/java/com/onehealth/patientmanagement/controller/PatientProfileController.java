package com.onehealth.patientmanagement.controller;

import com.onehealth.patientmanagement.entity.PatientProfile;
import com.onehealth.patientmanagement.exception.ProfileNotFoundException;
import com.onehealth.patientmanagement.service.PatientProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patientProfile")
public class PatientProfileController {
	
	@Autowired
    private PatientProfileService patientProfileService;

    @GetMapping
   public String Hello() {
	   
	   return "Hello From Patient";
   }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientProfile> getPatientProfileById(@PathVariable long patientId) throws ProfileNotFoundException{
 
            PatientProfile patientProfile = patientProfileService.getPatientProfileById(patientId);
            return new ResponseEntity<>(patientProfile, HttpStatus.OK);
       
    }

    @GetMapping("/allProfile/{userId}")
    public ResponseEntity<List<PatientProfile>> getAllPatientsByUserId(@PathVariable long userId) throws ProfileNotFoundException {
        
            List<PatientProfile> patientProfiles = patientProfileService.getAllPatientsByUserId(userId);
            return new ResponseEntity<>(patientProfiles, HttpStatus.OK);
       
    }

    @PostMapping("/add")
    public ResponseEntity<PatientProfile> createPatientProfile(@RequestBody PatientProfile patient) {
        PatientProfile createdPatientProfile = patientProfileService.createPatientProfile(patient);
        return new ResponseEntity<>(createdPatientProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientProfile> updatePatientProfile(@PathVariable long patientId,
                                                               @RequestBody PatientProfile patient) throws ProfileNotFoundException {
     
            PatientProfile updatedPatientProfile = patientProfileService.updatePatientProfile(patientId, patient);
            return new ResponseEntity<>(updatedPatientProfile, HttpStatus.OK);
        
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> deletePatientProfile(@PathVariable long patientId) throws ProfileNotFoundException {
        
            patientProfileService.deletePatientProfile(patientId);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        
    }

//    @DeleteMapping("/user/{userId}")
//    public ResponseEntity<Void> deleteAllPatientProfile(@PathVariable long userId) {
//        try {
//            patientProfileService.deleteAllPatientProfile(userId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (ProfileNotFoundException ex) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
