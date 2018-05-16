package com.chen.meme.repository;

import com.chen.meme.model.Entityuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntityuserRepository extends JpaRepository<Entityuser,Integer>{

    public List<Entityuser>findByUserID(Integer userid);
    //通过用户昵称查询
//    @Query(value = "select * from Entityuser u where u.userName= :username");
    public List<Entityuser> findByUserName(String username);
    public Entityuser findAllByUserName(String username);
    public List<Entityuser> findByUserNameAndPassword(String username,String password);
    //找回密码
    public List<Entityuser>findByUserNameAndAddressAndPhonenumber(String username,String address,String phone);

//    @Query(value = "select * from Entityuser u where u.userName= :username");
    public List<Entityuser> findByUserNameLikeOrAddressLikeOrPhonenumberLikeOrQqLike(String username,String address,String phone,String qq);
}
