package com.mati.twittersocial.repository;

import com.mati.twittersocial.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    public List<Message> findByChatId(Integer chatId);

}
