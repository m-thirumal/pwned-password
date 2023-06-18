/**
 * 
 */
package com.thirumal.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.thirumal.model.PwnedPassword;

/**
 * @author Thirumal
 *
 */
@Component
public class InsertEventListener {

	Logger logger = LoggerFactory.getLogger(InsertEventListener.class);

	@EventListener
    public void insert(PwnedPassword pwnedPassword) {
		logger.debug("Intiated insertion.....");
		
    }
}
