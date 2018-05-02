package com.chen.meme.repository;

import com.chen.meme.model.Entitymeme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntitymemeRepository extends JpaRepository<Entitymeme,Integer> {
//   根据ID查询表情包
    public List<Entitymeme> findAllByMemeID(Integer memeid);
//    根据分类查询表情包
    public List<Entitymeme> findByClassis(String classis);
//    根据名字简介作者模糊查询表情包
    public List<Entitymeme> findByMemeNameLikeOrAuthorLikeOrMemeIntroLike(String memename,String author,String memeintro);
//根据路径查询表情包
    public Entitymeme findByMemeSrc(String src);
    public List<Entitymeme> findAllByMemeSrc(String src);
}
