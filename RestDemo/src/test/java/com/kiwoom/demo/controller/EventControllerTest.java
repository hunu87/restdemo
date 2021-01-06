package com.kiwoom.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import com.kiwoom.demo.common.ControllerTest;
import com.kiwoom.demo.domain.EventStatus;

public class EventControllerTest extends ControllerTest {
	
	@Test
	public void create() throws Exception{
		EventDto eventDto = EventDto.builder()
					.name("안녕 이벤트")
					.description("배고프다")
					.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 2, 8, 0))	// 2018-11-02 08:00
					.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 3, 8, 0))
					.beginEventDateTime(LocalDateTime.of(2018, 11, 4, 8, 0))
	                .endEventDateTime(LocalDateTime.of(2018, 11, 5, 8, 0))
	                .basePrice(0)
	                .maxPrice(0)
	                .location("네이버 D2 팩토리 좁았음")
	                .limitOfEnrollment(100)
	                .build();
		
		this.mockMvc.perform(post("/api/events")
					.contentType(MediaType.APPLICATION_JSON)
					.content(this.objectMapper.writeValueAsString(eventDto)))	// Java object => Json(String)으로 serialize
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(header().exists("Location"))
					.andExpect(jsonPath("free").value(false))
					.andExpect(jsonPath("id").exists())
					.andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()));
	}
	
	@Test
	public void createFailTestByEventDtoValid() throws Exception {
		EventDto eventDto = EventDto.builder()
				.name("Valid test")
				.description("test")
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 4, 8, 0))
				.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 5, 8, 0))
				.beginEventDateTime(LocalDateTime.of(2018, 11, 2, 8, 0))
                .endEventDateTime(LocalDateTime.of(2018, 11, 3, 8, 0))
                .basePrice(-1)
                .maxPrice(5000)
                .location("네이버 D2 팩토리")
                .limitOfEnrollment(100)
                .build();
		
		this.mockMvc.perform(post("/api/events")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(eventDto)))	// Java object => Json(String)으로 serialize
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field").hasJsonPath())
				.andExpect(jsonPath("$[0].rejectedValue").hasJsonPath())
				.andExpect(jsonPath("$[0].defaultMessage").hasJsonPath())
				.andExpect(jsonPath("$[0].objectName").hasJsonPath());
	}
	
	@Test
	public void createFailTestByCustomEventDtoValidator() throws Exception {
		EventDto eventDto = EventDto.builder()
				.name("아침에 일어나기 너무 힘듬")
				.description("진짜")
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 5, 8, 0))
				.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 4, 8, 0))
				.beginEventDateTime(LocalDateTime.of(2018, 11, 3, 8, 0))
                .endEventDateTime(LocalDateTime.of(2018, 11, 2, 8, 0))
                .basePrice(1000)
                .maxPrice(5000)
                .location("네이버 D2 팩토리")
                .limitOfEnrollment(100)
                .build();
		
		this.mockMvc.perform(post("/api/events")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(eventDto)))	// Java object => Json(String)으로 serialize
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].field").hasJsonPath())
				.andExpect(jsonPath("$[0].rejectedValue").hasJsonPath())
				.andExpect(jsonPath("$[0].defaultMessage").hasJsonPath())
				.andExpect(jsonPath("$[0].objectName").hasJsonPath());
	}
}
