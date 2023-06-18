/**
 * 
 */
package com.thirumal.listener;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
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
	
	@Async
	@EventListener
    public void insert(PwnedPassword pwnedPassword) {
		logger.debug("Intiated insertion.....");
		try (Stream<String> lines = java.nio.file.Files.lines(Paths.get("/Users/thirumal/Downloads/pwned-passwords-ordered-by-count.txt"))) {
		    lines.forEach(line -> {
		       String[] split = line.split(":");
		       pwnedPasswordRepository.save(PwnedPassword.builder().hash(split[0]).prevalence(Integer.parseInt(split[1])).build());
		    });
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("Insertion is completed......");
    }
	
	
}
