/**
 * 
 */
package com.thirumal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Thirumal
 *
 */
@Service
public class PwnedPasswordService {

	Logger logger = LoggerFactory.getLogger(PwnedPasswordService.class);
	
	public boolean insert() {
		logger.debug("Initiating Insertion to database... ");
		return false;
	}

	
}
