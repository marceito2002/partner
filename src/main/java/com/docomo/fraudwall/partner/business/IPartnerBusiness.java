package com.docomo.fraudwall.partner.business;



import java.util.List;

import com.docomo.fraudwall.domain.PartnerDomain;





public interface IPartnerBusiness {
	void create(PartnerDomain partner );
	void delete (String partnerID );
	int update(PartnerDomain partner );
	List<PartnerDomain> findAll();
	PartnerDomain findById(String partnerId);
	List<PartnerDomain> findByName(String partnerName );
	
}
