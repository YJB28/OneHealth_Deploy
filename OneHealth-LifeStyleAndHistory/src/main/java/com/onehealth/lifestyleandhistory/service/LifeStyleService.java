 package com.onehealth.lifestyleandhistory.service;

import java.util.List;

import com.onehealth.lifestyleandhistory.entity.LifeStyle;
import com.onehealth.lifestyleandhistory.exception.RecordNotFoundException;

public interface LifeStyleService {
	
	List<LifeStyle> getAllLifeStyles();
	LifeStyle getLifestyleBylId(Long lId) throws RecordNotFoundException;
	LifeStyle createLifestyle(LifeStyle lifestyle);
	LifeStyle updateLifestyle(Long sNo, LifeStyle lifestyle) throws RecordNotFoundException;
    void deleteLifestyle(Long lId) throws RecordNotFoundException;
    
}
