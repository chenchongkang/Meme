package com.chen.meme.controller;

import com.chen.meme.model.Entityevaluation;
import com.chen.meme.repository.EntityevaluationRepository;
import com.chen.meme.server.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvaluationController {
    @Autowired
 private EntityevaluationRepository entityevaluationRepository;
    @Autowired
    EvaluationService evaluationService;

    /**
     * 添加一个评价
     */
    @PostMapping(value = "/addevaluation")
    public Object Register(@RequestBody(required =false) Entityevaluation entityevaluation) {
        entityevaluationRepository.save(entityevaluation);
        return "success";
    }

    /**
     * 查询表情包列表
     * @return
     */
    @RequestMapping(value = "/entityevalualist", method = RequestMethod.POST)
    public List<Entityevaluation> data() {
        return entityevaluationRepository.findAll();
    }
//根据表情包ID查询评论列表
@RequestMapping(value = "/evaluamemelist/{memeID}", method = RequestMethod.POST)
public List<Entityevaluation> memedata(@PathVariable("memeID") Integer memeID) {
    return entityevaluationRepository.findAllByMemeID(memeID);
}
//根据用户ID查询表情包列表
@RequestMapping(value = "/evaluauserlist/{userID}", method = RequestMethod.POST)
public List<Entityevaluation> userdata(@PathVariable("userID") Integer userID) {
    return entityevaluationRepository.findAllByUserID(userID);
}
//根据用户ID与表情包ID查询用户对该表情包的评分
    @RequestMapping(value = "/evaluations/{userID}/{memeID}",method = RequestMethod.POST)
    public Entityevaluation evaluationfind(@PathVariable("userID") Integer userID,
                                           @PathVariable("memeID") Integer memeID){
        return entityevaluationRepository.findByUserIDAndMemeID(userID,memeID);
    }

}
