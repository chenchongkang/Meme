package com.chen.meme.repository;

import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entitypicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntitypictureRepository extends JpaRepository<Entitypicture,Integer> {
    //根据表情包id查询表情包图片
    public List<Entitypicture> findAllByMemeID(Integer memeid);
}
