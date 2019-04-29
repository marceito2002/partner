package com.docomo.fraudwall.partner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docomo.fraudwall.domain.PartnerDomain;
import com.docomo.fraudwall.partner.generated.api.PartnerApi;
import com.docomo.fraudwall.partner.generated.model.Partner;
import com.docomo.fraudwall.partner.generated.model.ResponseData;
import com.docomo.fraudwall.partner.service.IServicePartner;
import com.docomo.fraudwall.partner.util.UtilPartner;

@RestController
public class PartnerController implements PartnerApi {

	@Autowired
	private IServicePartner service;

	@Autowired
	private UtilPartner utilPartner;

	private static final Logger logger = LoggerFactory.getLogger(PartnerController.class);

	@Override
	public ResponseEntity<Void> createPartner(@RequestBody @Valid Partner partner) {
		service.createPartner(utilPartner.convertPartnerToPartnerDomain(partner));
		return null;
	}

	@Override
	public ResponseEntity<ResponseData> deletePartner(@RequestParam String partnerName) {
		service.deletePartner(partnerName);
		return null;
	}

	@Override
	public ResponseEntity<Void> updatePartner(@RequestBody @Valid Partner partner) {
		if (service.updatePartner(utilPartner.convertPartnerToPartnerDomain(partner)) == -1) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<Partner>> findAll() {



		List<PartnerDomain> partnerDomain = service.findAll();
		List<Partner> responseData = new ArrayList<>();

		partnerDomain.forEach(k -> responseData.add(utilPartner.convertPartnerDomainToPartner(k)));

		if (responseData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(responseData, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<Partner>> findPartnerByName(String partnerName) {

		List<PartnerDomain> partnerDomain = service.findByName(partnerName);
		List<Partner> responseData = new ArrayList<>();

		partnerDomain.forEach(k -> responseData.add(utilPartner.convertPartnerDomainToPartner(k)));

		if (responseData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(responseData, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Partner> findPartnerById(String partnerId) {
		PartnerDomain partnerDomain = service.findById(partnerId);

		Partner partner = utilPartner.convertPartnerDomainToPartner(partnerDomain);
		if (partner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(partner, HttpStatus.OK);
		}

	}

}
