/**
 * 
 */
package com.thirumal.listener;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.thirumal.model.PwnedPassword;
import com.thirumal.model.PwnedPasswordRepository;

/**
 * @author Thirumal
 *
 */
@Component
public class InsertEventListener {

	Logger logger = LoggerFactory.getLogger(InsertEventListener.class);

	@Autowired
	private PwnedPasswordRepository pwnedPasswordRepository;
	
	@EventListener
    public void insert(PwnedPassword pwnedPassword) {
		logger.debug("Intiated insertion.....");
		List<PwnedPassword> pwnedPasswords = new ArrayList<>();
		for (int i = 0 ; i < 10 ; i++) {
			pwnedPasswords.add(PwnedPassword.builder().hash("s").prevalence(i).build());
		}
		pwnedPasswordRepository.saveAll(pwnedPasswords);
		logger.debug("Insertion is completed......");
    }
}
