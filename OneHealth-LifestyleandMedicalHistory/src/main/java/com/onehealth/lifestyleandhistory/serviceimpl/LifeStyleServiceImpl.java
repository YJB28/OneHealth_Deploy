package com.onehealth.lifestyleandhistory.serviceimpl;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.repository.LifeStyleRepository;
import com.onehealth.lifestyleandhistory.service.LifeStyleService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the LifeStyleService interface providing operations for
 * managing lifestyle-related data.
 * Author: Yogesh
 */
@Service
public class LifeStyleServiceImpl implements LifeStyleService {

    @Autowired
    private LifeStyleRepository lifeStyleRepository;

    @Override
    public List<LifeStyle> getAllLifeStyles() {
        return lifeStyleRepository.findAll();
    }

    @Override
    public LifeStyle getLifestyleByPatientId(Long patientId) throws RecordNotFoundException {
        return lifeStyleRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RecordNotFoundException("Lifestyle not found with patientId: " + patientId));
    }

    @Override
    public LifeStyle createLifestyle(LifeStyle lifestyle) {
        return lifeStyleRepository.save(lifestyle);
    }

    @Override
    public LifeStyle updateLifestyleByPatientId(Long patientId, LifeStyle lifestyle) throws RecordNotFoundException {
        LifeStyle existingLifestyle = lifeStyleRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RecordNotFoundException("Lifestyle not found with patientId: " + patientId));

        existingLifestyle.setSmoke(lifestyle.getSmoke());
        existingLifestyle.setAlcohol(lifestyle.getAlcohol());
        existingLifestyle.setExercise(lifestyle.getExercise());
        existingLifestyle.setFoodPreferences(lifestyle.getFoodPreferences());
        existingLifestyle.setOccupation(lifestyle.getOccupation());

        return lifeStyleRepository.save(existingLifestyle);
    }

    @Override
    @Transactional
    public void deleteLifestyleByPatientId(Long patientId) throws RecordNotFoundException {
        if (!lifeStyleRepository.existsByPatientId(patientId)) {
            throw new RecordNotFoundException("Lifestyle not found with patientId: " + patientId);
        }
        lifeStyleRepository.deleteByPatientId(patientId);
    }
}
