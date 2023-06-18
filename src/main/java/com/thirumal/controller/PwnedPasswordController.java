/**
 * 
 */
package com.thirumal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thirumal.model.PwnedPassword;
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
	
	@PostMapping("/insert")
	public boolean insert() {
		return pwnedPasswordService.insert();
	}
	

	@PostMapping("/pwned")
	public List<PwnedPassword> pwned(@RequestBody PwnedPassword pwnedPassword) {
		return pwnedPasswordService.isPwned(pwnedPassword);
	}
}
