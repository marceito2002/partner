package com.docomo.fraudwall.partner.persistence;


import java.util.List;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.docomo.fraudwall.domain.PartnerDomain;







public interface PartnerRespository extends MongoRepository<PartnerDomain, String> {
    @Query("{ 'partnerName' : ?0 }")    
    List<PartnerDomain>  findByPartnerName(String partnerName);
    @DeleteQuery("{ 'partnerName' : ?0 }")    
    List<PartnerDomain>  deleteByPartnerName(String partnerName);
	
}
