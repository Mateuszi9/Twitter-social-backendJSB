package com.mati.twittersocial.repository;

import com.mati.twittersocial.model.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Long> {

    public List<Reels> findByUserId(Integer userId);


}
