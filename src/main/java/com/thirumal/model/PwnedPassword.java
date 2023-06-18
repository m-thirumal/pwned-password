/**
 * 
 */
package com.thirumal.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Thirumal
 *
 */
@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder@ToString
public class PwnedPassword implements Serializable {

	private static final long serialVersionUID = 8254282040865699534L;

	private Long pwnedPasswordId;
	private String hash;
	private Integer prevalence;
}
