package com.docomo.fraudwall.partner.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.docomo.fraudwall.domain.EventDomain;
import com.docomo.fraudwall.domain.PartnerDomain;
import com.docomo.fraudwall.partner.generated.model.Event;
import com.docomo.fraudwall.partner.generated.model.Partner;

@Component
public class UtilPartner {

	public PartnerDomain convertPartnerToPartnerDomain(Partner partner) {
		return new PartnerDomain(partner.getPartnerId(), new Date(), partner.getPartnerName(), partner.getFraudwallModel(),
		convertEventToEventBussiness(partner.getEvents()));
		//return new PartnerDomain(partner.getPartnerId(), partner.getPartnerName(), partner.getFraudwallModel(),
		//		convertEventToEventBussiness(partner.getEvents()));
	}

	private List<EventDomain> convertEventToEventBussiness(List<Event> lstEvent) {
		List<EventDomain> lstBuss = new ArrayList<>();
		if (lstEvent == null)
			return lstBuss;
		lstEvent.forEach(k -> lstBuss.add(convertEventToEventDomain(k)));
		return lstBuss;
	}

	private EventDomain convertEventToEventDomain(Event event) {
		return new EventDomain(event.getEventName(), event.getEventId());
	}

	private Event convertEventDomainToEvent(EventDomain eventDomain) {
		Event event = new Event();
		event.setEventName(eventDomain.getEventName());
		event.setEventId(eventDomain.getEventId());
		return event;
	}

	private List<Event> convertEventBussinessToEvent(List<EventDomain> lstEventBss) {
		List<Event> lstEvent = new ArrayList<>();
		if (lstEventBss == null)
			return lstEvent;
		lstEventBss.forEach(k -> lstEvent.add(convertEventDomainToEvent(k)));
		return lstEvent;
	}

	public Partner convertPartnerDomainToPartner(PartnerDomain partner) {
		if (partner == null)
			return null;

		Partner part = new Partner();
		part.setPartnerId(partner.getPartnerId());
		part.setPartnerName(partner.getPartnerName());
		part.setFraudwallModel(partner.getFraudwallModel());
		part.setEvents(convertEventBussinessToEvent(partner.getEvents()));
		return part;

	}
}
