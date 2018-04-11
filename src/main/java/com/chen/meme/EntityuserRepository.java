package com.chen.meme;

import com.chen.meme.model.Entityuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntityuserRepository extends JpaRepository<Entityuser,Integer>{

    //通过用户昵称查询
//    @Query(value = "select * from Entityuser u where u.userName= :username");
    public List<Entityuser>findByUserName(String username);
    public List<Entityuser>findByUserNameAndPassword(String username,String password);
}
