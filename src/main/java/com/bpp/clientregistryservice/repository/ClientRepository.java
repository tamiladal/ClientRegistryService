/**
 * 
 */
package com.bpp.clientregistryservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bpp.clientregistryservice.domain.Client;

/**
 * @author DELL
 *
 */
@Repository
public interface ClientRepository extends MongoRepository<Client,String>{
	
	Client findByUserName(String userName);

}
