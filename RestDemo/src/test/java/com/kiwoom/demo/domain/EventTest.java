package com.kiwoom.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class EventTest {
	
	@Test
	public void createEventByBuilderTest() {
		Event event = Event.builder()
						.name("Event")
						.description("스프링 이벤트")
						.build();
		
		assertThat(event.getName()).isNotEmpty();
	}
	
	@Test
	public void createEventByDefaultConstructor() {
		Event event = new Event();
		event.setName("Event");
		event.setDescription("스프링 이벤트");
		assertThat(event.getName()).isNotEmpty();
	}
}
