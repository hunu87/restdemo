package com.kiwoom.demo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kiwoom.demo.domain.Event;
import com.kiwoom.demo.domain.EventRepository;

@RestController
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)	// HAL_JSON_VALUE 형태로서 값을 반환
public class EventController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventDtoValidator eventDtoValidator;
	
	@PostMapping
	public ResponseEntity create(@RequestBody @Valid EventDto eventDto, Errors errors) {
		
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors);
			//body에 Errors를 담아 리턴하지만 Java Bean 스펙이 준수되지 않아 Serialize 되지 않음. Custom한 Serializer를 만들어서 Jackson ObjectMapper에 등록 해줘야 한다.
		}
		
		eventDtoValidator.validate(eventDto, errors);	// 디테일한 별도의 추가적인 validate 체크
		if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
		
		Event event = modelMapper.map(eventDto, Event.class);	// EventDto 객체를 Event 클래스 객체로 변환
		Event savedEvent = eventRepository.save(event);
		
		URI uri = WebMvcLinkBuilder.linkTo(EventController.class).slash(savedEvent.getId()).toUri();
		return ResponseEntity.created(uri).body(savedEvent);
		//linkTo로 생성된 uri정보를 created에 넣어주면 HTTP Header에 Location=http://localhost/api/events/1 정보가 들어간다.
		
	}
}
