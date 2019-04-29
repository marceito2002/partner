package com.docomo.fraudwall.partner;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.docomo.fraudwall.domain.EventDomain;
import com.docomo.fraudwall.partner.controller.PartnerController;
import com.docomo.fraudwall.partner.generated.model.Event;
import com.docomo.fraudwall.partner.generated.model.Partner;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

/**
 * 
 * @author aherreros This test update in test mongodb by default
 */
public class PartnerApplicationTest

{
	@Autowired
	private PartnerController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	/**
	 * Comprobar si esta el controlador en el contexto de spring
	 */
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
		insertar();
	}

	@Test
	public void testCreateNewPartner() throws Exception {

		final ObjectMapper mapper = new ObjectMapper();

		this.mockMvc
				.perform(post("/partner").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(getDatosIn())))
				.andDo(print()).andExpect(status().is2xxSuccessful());

	}

	@Test
	public void testDeleteOnePartner() throws Exception {
		
		this.mockMvc.perform(delete("/partner?partnerName=1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testFindAllPartner() throws Exception {
		insertar();
		this.mockMvc.perform(get("/partner").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
		
	}

	@Test
	public void testFindByIdNotFound() throws Exception {
		this.mockMvc.perform(get("/partner/getById/1981").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
		
	}	

//	@Test
//	public void testFindByIdOk() throws Exception {
//		insertar();
//		this.mockMvc.perform(get("/partner/getById/3").contentType(MediaType.APPLICATION_JSON)).andDo(print())
//				.andExpect(status().isOk());
//		
//	}	
	
	@Test
	public void testFindByNameOk() throws Exception {
		insertar();
		this.mockMvc.perform(get("/partner/getByName/Partner name is 3").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
		
	}		

	@Test
	public void testFindByNameNotFound() throws Exception {
		
		insertar();
		this.mockMvc.perform(get("/partner/getByName/NoExiste").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
		
	}		
	
	private Partner getDatosIn() throws Exception {
		List<Event> lstEvent = new ArrayList<>();
		Partner partn = new Partner();
		partn.setPartnerId("1");
		partn.setPartnerName("Partner name is 1");
		
		Event event = new Event();
		event.setEventName("Click");
		event.setEventId("1");
		lstEvent.add(event);

		partn.setEvents(lstEvent);
		return partn;
	}

	private Partner getDatosInNoDelete() throws Exception {
		Partner partn = new Partner();
		partn.setPartnerId("3");
		partn.setPartnerName("Partner name is 3");
		return partn;
	}

	
	private Partner getDatosInNofound() throws Exception {
		Partner partn = new Partner();
		partn.setPartnerId("1");
		partn.setPartnerName("Partner name is 1");
		return partn;
	}
	

//	@Test
//	public void testUpdateOnePartnerOK() throws Exception {
//		
//		final ObjectMapper mapper = new ObjectMapper();
//
//		this.mockMvc
//				.perform(put("/partner").contentType(MediaType.APPLICATION_JSON)
//						.content(mapper.writeValueAsString(getDatosInNoDelete())))
//				.andDo(print()).andExpect(status().isOk());
//
//	}
	
//	@Test
//	public void testUpdateOnePartnerNotFound() throws Exception {
//		
//		final ObjectMapper mapper = new ObjectMapper();
//
//		this.mockMvc
//				.perform(put("/partner").contentType(MediaType.APPLICATION_JSON)
//						.content(mapper.writeValueAsString(getDatosInNofound())))
//				.andDo(print()).andExpect(status().isNotFound());
//
//		
//	}	
	
	private void insertar() throws Exception {
		controller.createPartner(getDatosInNoDelete());
	}	
}
