package com.onehealth.lifestyleandhistory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;
import com.onehealth.lifestyleandhistory.repository.LifeStyleRepository;
import com.onehealth.lifestyleandhistory.service.LifeStyleService;




@Service
public class LifeStyleServiceImpl implements LifeStyleService {
	
	@Autowired
    private LifeStyleRepository lifeStyleRepository;



    @Override
    public List<LifeStyle> getAllLifeStyles() {
        return lifeStyleRepository.findAll();
    }

    @Override
    public LifeStyle getLifestyleBylId(Long lId) throws RecordNotFoundException {
        return lifeStyleRepository.findById(lId)
                .orElseThrow(() -> new RecordNotFoundException("Lifestyle not found with sNo: " + lId));
    }

    @Override
    public LifeStyle createLifestyle(LifeStyle lifestyle) {
        return lifeStyleRepository.save(lifestyle);
    }

    @Override
    public LifeStyle updateLifestyle(Long lId, LifeStyle lifestyle) throws RecordNotFoundException {
    	LifeStyle existingLifestyle = lifeStyleRepository.findById(lId)
                .orElseThrow(() -> new RecordNotFoundException("Lifestyle not found with sNo: " + lId));

    	  existingLifestyle.setSmoke(lifestyle.getSmoke());
          existingLifestyle.setAlcohol(lifestyle.getAlcohol());
          existingLifestyle.setExercise(lifestyle.getExercise());
          existingLifestyle.setFoodPreferences(lifestyle.getFoodPreferences());
          existingLifestyle.setOccupation(lifestyle.getOccupation());
          existingLifestyle.setPatientId(lifestyle.getPatientId());
          existingLifestyle.setUserId(lifestyle.getUserId());

          return lifeStyleRepository.save(existingLifestyle);
    }

    @Override
    public void deleteLifestyle(Long lId) throws RecordNotFoundException {
        if (!lifeStyleRepository .existsById(lId)) {
            throw new RecordNotFoundException("Lifestyle not found with sNo: " + lId);
        }
        lifeStyleRepository.deleteById(lId);
    }
}

