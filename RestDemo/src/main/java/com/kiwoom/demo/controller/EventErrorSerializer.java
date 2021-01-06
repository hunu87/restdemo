package com.kiwoom.demo.controller;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent 
// @JsonComponenet로 등록한 JsonSerializer는 ObjectMapper에 자동으로 등록된다.
public class EventErrorSerializer extends JsonSerializer<Errors> {
	@Override
	public void serialize(Errors errors, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartArray();
		errors.getFieldErrors().forEach(e -> {
			try {
				jsonGenerator.writeStartObject();
				jsonGenerator.writeStringField("field", e.getField());
				jsonGenerator.writeStringField("objectName", e.getObjectName());
				jsonGenerator.writeStringField("defaultMessage", e.getDefaultMessage());
				
				Object rejectedValue = e.getRejectedValue();
				if(rejectedValue != null) {
					jsonGenerator.writeStringField("rejectedValue", rejectedValue.toString());
				}else {
					jsonGenerator.writeStringField("rejectedValue", "");
				}
				
				jsonGenerator.writeEndObject();
				
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		jsonGenerator.writeEndArray();
	}
}
