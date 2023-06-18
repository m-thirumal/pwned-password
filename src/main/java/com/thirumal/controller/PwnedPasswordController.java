/**
 * 
 */
package com.thirumal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thirumal.service.PwnedPasswordService;

/**
 * @author Thirumal
 *
 */
@RestController
public class PwnedPasswordController {

	Logger logger = LoggerFactory.getLogger(PwnedPasswordController.class);
	
	@Autowired
	private PwnedPasswordService pwnedPasswordService;
	
	@PostMapping("")
	public boolean insert() {
		return pwnedPasswordService.insert();
	}
}
