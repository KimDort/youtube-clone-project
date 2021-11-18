package com.tube.clone.config.message.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tube.clone.config.message.domain.MessageDomain;

@Repository
public interface MessageRepositories extends JpaRepository<MessageDomain, Integer>{
	public MessageDomain findByMessageCodeNameAndMessageCodeLocale(String name, String locale);
	
	public MessageDomain findByMessageCodeName(MessageDomain domain);
}
