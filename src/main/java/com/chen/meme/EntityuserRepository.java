package com.chen.meme;

import com.chen.meme.model.Entityuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntityuserRepository extends JpaRepository<Entityuser,Integer>{

    //通过用户昵称查询
//    public List<Entityuser>findByUserName(String username);
}
