package com.kiwoom.demo.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder @AllArgsConstructor @NoArgsConstructor
/* Event class에서 실제로 사용해야하는 필드만을 위해 Dto 객체를 만듬. 
 * Event 객체와 중복으로 관리해야하는필드들도 있지만, validation 체크를 위해서라도 필요 함. */
public class EventDto {
	
	private String name;
	@NotEmpty	// null과 "" 둘다 허용 하지 않음. (" "는 허용)
	private String description;
	@NotNull
	private LocalDateTime beginEnrollmentDateTime;
	@NotNull
	private LocalDateTime closeEnrollmentDateTime;
	@NotNull
	private LocalDateTime beginEventDateTime;
	@NotNull
	private LocalDateTime endEventDateTime;
	private String location;	// (optional) 이게 없으면 온라인 모임
	@Min(0)
	private int basePrice;	// (optional)
	@Min(0)
	private int maxPrice;	// (optional)
	@Min(0)
	private int limitOfEnrollment;	// (optional)
	
}
