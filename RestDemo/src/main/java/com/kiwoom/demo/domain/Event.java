package com.kiwoom.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")	// equals : 두 객체의 내용이 같은지를 비교, hashcode : 두 객체가 같은 객체인지를 비교 (여기서는 id 필드로만 비교한다는 의미)
@Builder
@AllArgsConstructor	// 모든 필드 값을 파라미터로 받는 생성자를 만들어 줌
@NoArgsConstructor	// 파라미터가 없는 기본 생성자를 만들어 줌
public class Event {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;
	private LocalDateTime beginEnrollmentDateTime;
	private LocalDateTime closeEnrollmentDateTime;
	private LocalDateTime beginEventDateTime;
	private LocalDateTime endEventDateTime;
	private String location;	// (optional) 이게 없으면 온라인 모임
	private int basePrice;	// (optional)
	private int maxPrice;	// (optional)
	private int limitOfEnrollment;
	private boolean offline;
	private boolean free;
	@Enumerated(EnumType.STRING)	// 자바 enum 타입을 엔티티 클래스의  속성으로 사용
	private EventStatus eventStatus = EventStatus.DRAFT;	// EnumType.ORDINAL : enum 순서 값을 DB에 저장, EnumType.STRING : enum 이름을 DB에 저장
}
