package com.kiwoom.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {	// JpaRepository 인터페이스를 상속받게 되면 자동으로 IoC 컨테이너에 해당 빈이 자동으로 등록 된다.
	
}
