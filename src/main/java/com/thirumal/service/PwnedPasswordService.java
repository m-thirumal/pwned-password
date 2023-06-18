/**
 * 
 */
package com.thirumal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.thirumal.model.PwnedPassword;

/**
 * @author Thirumal
 *
 */
@Service
public class PwnedPasswordService {

	Logger logger = LoggerFactory.getLogger(PwnedPasswordService.class);
	
	@Autowired
	protected ApplicationEventPublisher applicationEventPublisher;
	
	public boolean insert() {
		logger.debug("Initiating Insertion to database... ");
		applicationEventPublisher.publishEvent(new PwnedPassword());
		return true;
	}

	
}
