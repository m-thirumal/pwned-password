/**
 * 
 */
package com.thirumal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.thirumal.model.PwnedPassword;
import com.thirumal.model.PwnedPasswordRepository;

/**
 * @author Thirumal
 *
 */
@Service
public class PwnedPasswordService {

	Logger logger = LoggerFactory.getLogger(PwnedPasswordService.class);
	
	@Autowired
	private PwnedPasswordRepository pwnedPasswordRepository;
	
	@Autowired
	protected ApplicationEventPublisher applicationEventPublisher;
	
	public boolean insert() {
		logger.debug("Initiating Insertion to database... ");
		applicationEventPublisher.publishEvent(new PwnedPassword());
		return true;
	}

	public List<PwnedPassword> isPwned(PwnedPassword pwnedPassword) {
		logger.debug("Pwned password {}", pwnedPassword);
		return pwnedPasswordRepository.findByhash(pwnedPassword.getHash());
	}

	
}
