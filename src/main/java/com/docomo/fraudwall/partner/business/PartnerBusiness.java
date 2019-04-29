package com.docomo.fraudwall.partner.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docomo.fraudwall.domain.PartnerDomain;
import com.docomo.fraudwall.partner.persistence.PartnerRespository;

@Service
public class PartnerBusiness implements IPartnerBusiness {

	private static final Logger logger = LoggerFactory.getLogger(PartnerBusiness.class);

	@Autowired
	private PartnerRespository partnerRepo;

	@Override
	public void create(PartnerDomain partner) {
		partnerRepo.save(partner);
	}

	@Override
	public void delete(String partnerID) {
		partnerRepo.deleteByPartnerName(partnerID);
	}

	/**
	 * Return 0 --> OL Return -1 --> no exist
	 */
	@Override
	public int update(PartnerDomain partner) {
		if (partnerRepo.existsById(partner.getPartnerId())) {
			partnerRepo.save(partner);
			return 0;
		}
		logger.info("PartnerBusiness: Partner no exsit {}", partner.getPartnerId());
		return -1;

	}

	@Override
	public List<PartnerDomain> findAll() {
		return partnerRepo.findAll();
	}

	@Override
	public PartnerDomain findById(String partnerId) {
		Optional<PartnerDomain> findById = partnerRepo.findById(partnerId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public List<PartnerDomain> findByName(String partnerName) {
		return partnerRepo.findByPartnerName(partnerName);
	}

}
