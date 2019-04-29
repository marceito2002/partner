package com.docomo.fraudwall.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docomo.fraudwall.domain.PartnerDomain;
import com.docomo.fraudwall.partner.business.IPartnerBusiness;




@Service
public class ServicePartner implements IServicePartner  {

	@Autowired
	private IPartnerBusiness business; 
	
	@Override
	public void createPartner(PartnerDomain partner) {
		business.create(partner);		
	}

	@Override
	public void deletePartner(String partnerID) {
		business.delete(partnerID);		
	}

	@Override
	public int updatePartner(PartnerDomain partner) {
		return business.update(partner);		
	}


	@Override
	public List<PartnerDomain> findAll() {
		return business.findAll();	
	}

	@Override
	public PartnerDomain findById(String partnerId) {
		return business.findById(partnerId);
	}

	@Override
	public List<PartnerDomain> findByName(String partnerName) {
		return business.findByName(partnerName);
	}

}
