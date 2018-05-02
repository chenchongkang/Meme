package com.chen.meme.repository;

import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entitypicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntitypictureRepository extends JpaRepository<Entitypicture,Integer> {
}
