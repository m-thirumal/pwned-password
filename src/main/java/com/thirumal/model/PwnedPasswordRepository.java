/**
 * 
 */
package com.thirumal.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.thirumal.exception.BadRequestException;
import com.thirumal.exception.ResourceNotFoundException;

/**
 * @author Thirumal
 *
 */
@Repository
public class PwnedPasswordRepository {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected Environment environment;
    


	private static final String PK                   = "pwned_password_id";
	
	private static final String CREATE               = "INSERT INTO public.pwned_password(hash, prevalence)	VALUES (?, ?)";
	private static final String GET                  = "SELECT * FROM public.pwned_password;";
	private static final String GETBY_HASH           = GET + "WHERE hash = ?";
	
	public Long save(PwnedPassword pwnedPassword) {
		KeyHolder holder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> setPreparedStatement(pwnedPassword, con.prepareStatement(CREATE,
                    new String[] { PK })), holder);
            return Optional.ofNullable(holder.getKey())
                    .orElseThrow(()->new ResourceNotFoundException("Not able to generate primary key")).longValue();
        } catch (DataIntegrityViolationException e) {
           logger.error("Login user name insert exception: {}", e.getMessage());
           throw new BadRequestException("Login user name is not added, Contact admin");
        }       
	}

	private PreparedStatement setPreparedStatement(PwnedPassword contact, PreparedStatement ps) throws SQLException {
		if(contact.getHash() == null) {
            ps.setObject(1, null);
        } else {
            ps.setString(1, contact.getHash());
        }
		if(contact.getPrevalence() == null) {
            ps.setObject(2, null);
        } else {
            ps.setInt(2, contact.getPrevalence());
        }
	
		return ps;
	}
	
	public int[] saveAll(List<PwnedPassword> pwnedPasswords) {
		try {
			return jdbcTemplate.batchUpdate(CREATE, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					setPreparedStatement(pwnedPasswords.get(i), ps);
				}
				@Override
				public int getBatchSize() {
					return pwnedPasswords.size();
				}
			});
		} catch (DataIntegrityViolationException e) {
			logger.debug("Exception during batch insertion of contact {}", e.getMessage());
		}
		throw new BadRequestException("PwnedPasswords batch insert is failed");		
	}

	
	public PwnedPassword findById(Long id) {
		logger.debug("Finding password by Id {}", id);
		return jdbcTemplate.queryForObject(GET, pwnedPasswordRowMapper, id);
	}


	public PwnedPassword findByhash(String hash) {
		logger.debug("Finding contact by loginId {}", hash);
		try {
			return jdbcTemplate.queryForObject(GETBY_HASH, pwnedPasswordRowMapper, hash);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	

	RowMapper<PwnedPassword> pwnedPasswordRowMapper = (rs, rowNum) -> {

		PwnedPassword pwnedPassword = new PwnedPassword();

		pwnedPassword.setPwnedPasswordId(rs.getObject(PK) != null ? rs.getLong(PK) : null);

		pwnedPassword.setHash(rs.getObject("hash") != null ? rs.getString("hash") : null);
		
		pwnedPassword.setPrevalence(rs.getObject("prevalence") != null ? rs.getInt("prevalence") : null);
	
		return pwnedPassword;
	};
	


}
