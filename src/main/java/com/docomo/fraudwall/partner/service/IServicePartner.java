package com.docomo.fraudwall.partner.service;

import java.util.List;

import com.docomo.fraudwall.domain.PartnerDomain;






public interface IServicePartner {
	void createPartner(PartnerDomain partner );
	
	void deletePartner (String partnerID );
	int updatePartner(PartnerDomain partner );
	List<PartnerDomain> findAll ();
	PartnerDomain findById (String partnerId);
	List<PartnerDomain> findByName ( String partnerName);
}
