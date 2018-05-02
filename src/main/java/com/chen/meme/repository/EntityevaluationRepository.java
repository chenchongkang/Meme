package com.chen.meme.repository;

import com.chen.meme.model.Entityevaluation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityevaluationRepository extends JpaRepository<Entityevaluation,Integer> {
    //   根据表情包ID查询该表情包评价列表
    public List<Entityevaluation> findAllByMemeID(Integer memeid);
    //   根据用户ID查询该表情包评价列表
    public List<Entityevaluation> findAllByUserID(Integer userid);
    //根据用户ID与表情包ID查询用户对该表情包的评分
    public Entityevaluation findByUserIDAndMemeID(Integer userid,Integer memeid);

}
