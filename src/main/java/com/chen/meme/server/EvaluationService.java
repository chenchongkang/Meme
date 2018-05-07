package com.chen.meme.server;

import com.chen.meme.model.Entityevaluation;
import com.chen.meme.model.Entityuser;
import com.chen.meme.repository.EntityevaluationRepository;
import com.chen.meme.repository.EntityuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EntityevaluationRepository entityevaluationRepository;

}
