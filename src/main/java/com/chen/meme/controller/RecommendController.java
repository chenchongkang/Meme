package com.chen.meme.controller;

import com.chen.meme.model.*;
import com.chen.meme.repository.EntityevaluationRepository;
import com.chen.meme.repository.EntitymemeRepository;
import com.chen.meme.repository.EntityuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RecommendController {

    @Autowired
    private EntityevaluationRepository entityevaluationRepository;
    @Autowired
    private EntitymemeRepository entitymemeRepository;

    @RequestMapping(value = "/recommendlists/{userID}", method = RequestMethod.POST)
    public Object recommendlist(@PathVariable("userID") Integer userID){
        List<Entityevaluation> user = entityevaluationRepository.findAllByUserID(userID);
        if(user!=null&&user.size()==0){
            return entitymemeRepository.findAll();
        }
//        目标用户对表情包的评价
        EntityevaluationHelper userHelper = EntityevaluationHelper.build(userID,user);

        List<Entityevaluation> other = new ArrayList<>();
        List<Entityevaluation> otherMeme;
//        目标用户已评价的表情包id
        Set<Integer> set = new HashSet<>();
        for (int i = 0;i<user.size();i++){
            Integer memeID = user.get(i).getMemeID();
            set.add(memeID);
//          目标用户评价过的表情包还有哪些评价item包括用户本身
            otherMeme = entityevaluationRepository.findAllByMemeID(memeID);
            other.addAll(otherMeme);
        }
//          目标用户的相似用户（评价过相同的表情包）
        Set<Integer> userSet = new HashSet<>();
        for(int i=0;i<other.size();i++){
            Integer userId = other.get(i).getUserID();
            if(userSet.contains(userId)){
                other.remove(i);
            }else {
                userSet.add(userId);
            }
        }
//        去除目标用户自身
        userSet.remove(userID);

        List<EntityevaluationHelper> otherHelper = new ArrayList<>();
        EntityevaluationHelper helper;
        for (Integer id:userSet){
            otherMeme = entityevaluationRepository.findAllByUserID(id);
            helper = EntityevaluationHelper.build(id, otherMeme);
            otherHelper.add(helper);
        }
        List<Oujilide> oujilides = new ArrayList<>();
        for(int i=0;i<otherHelper.size();i++){
            EntityevaluationHelper one = otherHelper.get(i);
            Oujilide oujilide = new Oujilide(one.getUserID(),userHelper.similarity(one),i);
            oujilides.add(oujilide);
        }
        //假装排序 取前三相似用户
        int len = oujilides.size();
        int[] maxList = new int[3];
        if(len>=3) {
            if (oujilides.get(0).getOjldjl() > oujilides.get(1).getOjldjl()) {
                if (oujilides.get(0).getOjldjl() > oujilides.get(2).getOjldjl()) {
                    maxList[0] = 0;
                    if (oujilides.get(1).getOjldjl() > oujilides.get(2).getOjldjl()) {
                        maxList[1] = 1;
                        maxList[2] = 2;
                    } else {
                        maxList[1] = 2;
                        maxList[2] = 1;
                    }
                } else {
                    maxList[0] = 2;
                    maxList[1] = 0;
                    maxList[2] = 1;
                }
            } else {
                if (oujilides.get(1).getOjldjl() > oujilides.get(2).getOjldjl()) {
                    maxList[0] = 1;
                    if (oujilides.get(0).getOjldjl() > oujilides.get(2).getOjldjl()) {
                        maxList[1] = 0;
                        maxList[2] = 2;
                    } else {
                        maxList[1] = 2;
                        maxList[2] = 1;
                    }
                } else {
                    maxList[0] = 2;
                    maxList[1] = 1;
                    maxList[2] = 0;
                }
            }
            int max = maxList[0];
            for (int i = 3; i < oujilides.size(); i++) {
                if (oujilides.get(i).getOjldjl() > oujilides.get(max).getOjldjl()) {
                    max = i;
                    myMax(maxList, max);
                }
            }
        }
        Set<Integer> memeSet = new HashSet<>();
        for(int i = 0;i<(len>3?3:len);i++){
            Map<Integer, Double> map = otherHelper.get(oujilides.get(maxList[i]).getIndex()).getEntityevaluation();
            for (Map.Entry<Integer, Double> entry:map.entrySet()){
                if(entry.getValue()==5){
                    memeSet.add(entry.getKey());
                }
            }
        }
//        去除目标用户已评价的表情包
        memeSet.removeAll(set);
        ArrayList<Entitymeme> tuijianList=new ArrayList<Entitymeme>();
        for (Integer memeID:memeSet){
            tuijianList.add( entitymemeRepository.findOne(memeID));
        }
        System.out.println("推荐表情包数：" + tuijianList.size());
        return tuijianList;
    }
    private void myMax(int[] maxList,int max){
        maxList[2] = maxList[1];
        maxList[1] = maxList[0];
        maxList[0] = max;
    }

////    根据目标用户ID生成它的推荐表情包
//    @RequestMapping(value = "/recommendlists/{userID}", method = RequestMethod.POST)
//    public Object recommendlist(@PathVariable("userID") Integer userID){
//        // 定义列表存储用户自己评价过的memeID
//        ArrayList<Integer> myEvalMemeIDList = new ArrayList<Integer>();
//        List<Entityevaluation> evaluationlist = entityevaluationRepository.findAllByUserID(userID);
//        for (Entityevaluation a :evaluationlist){
//            myEvalMemeIDList.add(a.getMemeID());
//        }
//        System.out.println("目标用户评价过的表情包"+myEvalMemeIDList);
//        //如果目标用户没有评价过表情包
//        if (myEvalMemeIDList.size()==0){
//             List<Entitymeme> data =entitymemeRepository.findAll();
//            System.out.println("无法推荐：目标用户评价的表情包个数为"+myEvalMemeIDList);
//            return data;
//        }
//        //存储目标用户与其他用户的欧几里德距离
//        ArrayList<Oujilide> oujilides=new ArrayList<Oujilide>();
//        // 遍历用户表
//        List<Entityuser> allUserlist =entityuserRepository.findAll();
//        for (Entityuser user : allUserlist) {
//            if (user.getUserID()==userID){
//                continue;
//            }
//            // 存储otherUserEvaluaMemeidlist其他用户评价过的表情包
//            ArrayList<Integer> otherUserEvaluaMemeidlist=new ArrayList<Integer>();
//            //其他用户所评价的表情包
//            List<Entityevaluation> otherlist=entityevaluationRepository.findAllByUserID(user.getUserID());
//            for (Entityevaluation evaluation :otherlist){
//                otherUserEvaluaMemeidlist.add(evaluation.getMemeID());
//            }
//            //samememeIDList存储其他用户（otherUserEvaluaMemeidlist）与目标用户的相同表情包ID
//            ArrayList<Integer> samememeIDList=new ArrayList<Integer>();
//            for(Integer a:myEvalMemeIDList){
//                for (Integer b:otherUserEvaluaMemeidlist){
//                    if (a==b){
//                        samememeIDList.add(a);
//                    }
//                }
//            }
//            System.out.println("评价过的相同表情包"+samememeIDList);
//
//            //存储目标用户对相同表情包的评价
//            ArrayList<Double> myEvaluations=new ArrayList<Double>();
//            //存储其他用户对相同表情包的评价
//            ArrayList<Double> otherEvaluations=new ArrayList<Double>();
//            //目标用户对相同表情包的评价 userID，samememeIDList
//           for (Integer samememeID:samememeIDList){
//               Entityevaluation entityevaluation=entityevaluationRepository.findByUserIDAndMemeID(userID,samememeID);
//               myEvaluations.add(entityevaluation.getEvaluations());
//           }
//            System.out.println("目标用户对共同拥有的表情包评价"+myEvaluations);
//            //其他用户对相同表情包的评价 user.getUserID()，samememeIDList
//            for (Integer samememeID:samememeIDList){
//                Entityevaluation entityevaluation=entityevaluationRepository.findByUserIDAndMemeID( user.getUserID(),samememeID);
//                otherEvaluations.add(entityevaluation.getEvaluations());
//            }
//            System.out.println("其他用户对共同拥有的表情包的评价"+otherEvaluations);
//            // 根据欧几里德距离求用户相似度；
//            double distance=0;
//            for (int i=0;i<myEvaluations.size();i++){
//                double x= myEvaluations.get(i);
//                double y = otherEvaluations.get(i);
//                distance =distance+Math.pow((x-y),2);
//            }
//            System.out.println("标准差："+distance);
//            double similarity=0.0;
//            similarity=1/(1+Math.sqrt(distance));
//            System.out.println("欧几里德距离：相似度"+similarity);
//            oujilides.add(new Oujilide(user.getUserID(), similarity));
//            }
////        System.out.println(oujilides.get(0).getOjldjl());
////        System.out.println(oujilides.get(0).getUserID());
////        System.out.println(oujilides.get(1).getOjldjl());
////        System.out.println(oujilides.get(1).getUserID());
////        System.out.println(oujilides.get(2).getOjldjl());
////        System.out.println(oujilides.get(2).getUserID());
//
//        //求相似度前三的相邻用户集合，将用户id与相似度存储在oujilides1中
//        ArrayList<Oujilide>oujilides1=new ArrayList<Oujilide>();
//        int len = oujilides.size();
//        if(len<=0){
//        System.out.println("该用户没有相似用户");
//        }else if(len ==1){
//        System.out.println("该用户只有一个相似用户");
//        }else if(len ==2){
//        System.out.println("该用户只有两个相似用户");
//        }else {
//            int[] maxList = new int[3];
//            if(oujilides.get(0).getOjldjl()>oujilides.get(1).getOjldjl()){
//                if(oujilides.get(0).getOjldjl()>oujilides.get(2).getOjldjl()){
//                    maxList[0] = 0;
//                    if(oujilides.get(1).getOjldjl()>oujilides.get(2).getOjldjl()){
//                        maxList[1] = 1;
//                        maxList[2] = 2;
//                    }else {
//                        maxList[1] = 2;
//                        maxList[2] = 1;
//                    }
//                }else {
//                    maxList[0] = 2;
//                    maxList[1] = 0;
//                    maxList[2] = 1;
//                }
//            }else {
//                if(oujilides.get(1).getOjldjl()>oujilides.get(2).getOjldjl()){
//                    maxList[0] = 1;
//                    if(oujilides.get(0).getOjldjl()>oujilides.get(2).getOjldjl()){
//                        maxList[1] = 0;
//                        maxList[2] = 2;
//                    }else {
//                        maxList[1] = 2;
//                        maxList[2] = 1;
//                    }
//                }else {
//                    maxList[0] = 2;
//                    maxList[1] = 1;
//                    maxList[2] = 0;
//                }
//            }
//            int max = maxList[0];
//            for(int i=3;i<len;i++){
//                if(oujilides.get(i).getOjldjl()>oujilides.get(max).getOjldjl()){
//                    max = i;
//                    myMax(maxList,max);
//                }
//            }
//            for (int i:maxList){
//                oujilides1.add(new Oujilide(oujilides.get(i).getUserID(),oujilides.get(i).getOjldjl()));
//            }
//        }
////        oujilides1.add(new Oujilide(oujilides.get(a).getUserID(),oujilides.get(a).getOjldjl()));
////        if(int i=0;i<oujilides.size()-1;i++)
////        for(int j=0;j<3;j++){
////            int a=0;
////            int b=0;
////           for (int i=0;i<oujilides.size()-1;i++){
////               if (oujilides.get(a).getOjldjl()<=oujilides.get(i+1).getOjldjl()){
////                   b=a;
////                   a=i+1;
////                        for (Oujilide ou1 :oujilides1){
////                          if (oujilides.get(i+1)==ou1){
////                              a=b;}
////               }
////               }
////           }
////           System.out.println("a="+a);
////            oujilides1.add(new Oujilide(oujilides.get(a).getUserID(),oujilides.get(a).getOjldjl()));
//        System.out.println("前三个相似用户及相似度");
////        System.out.println(oujilides1.get(0).getOjldjl());
////        System.out.println(oujilides1.get(0).getUserID());
////        System.out.println(oujilides1.get(1).getOjldjl());
////        System.out.println(oujilides1.get(1).getUserID());
////        System.out.println(oujilides1.get(2).getOjldjl());
////        System.out.println(oujilides1.get(2).getUserID());
//
//        //存储相邻用户喜欢（评价为5分）的表情包ID
//        ArrayList<Otherlike> otherUserLikeMemeidlist=new ArrayList<Otherlike>();
////        ArrayList<Entityevaluation> otherUserEvaluation1=new ArrayList<Entityevaluation>();
//        for (Oujilide oujilide1:oujilides1) {
//            //根据用户ID查询用户评价
//            //TODO
//          List<Entityevaluation> otherUserEvaluation = entityevaluationRepository.findAllByUserID(oujilide1.getUserID());
//          for (Entityevaluation otherUserEvaluation2:otherUserEvaluation )
//              if(otherUserEvaluation2.getEvaluations()==5.0){
//              otherUserLikeMemeidlist.add(new Otherlike(otherUserEvaluation2.getUserID(),otherUserEvaluation2.getMemeID()));
//        }
//        }
//
//        System.out.println("其他用户评价为5的表情包"+otherUserLikeMemeidlist.size());
//
//        //存储相邻用户喜欢（评价为5分）的表情包ID且目标用户没有的表情包
//        ArrayList<Otherlike> otherUserLikeAndMyNotMemeidlist=new ArrayList<Otherlike>();
//        for (Otherlike otherUserLikeMemeidlist1:otherUserLikeMemeidlist){
//            int i;
//            for(i=0;i<myEvalMemeIDList.size();i++){
//                if (myEvalMemeIDList.get(i)==otherUserLikeMemeidlist1.getOtherlisememeID()){
//                    break;
//                }
//            }
//            if(i>=myEvalMemeIDList.size()){
//                otherUserLikeAndMyNotMemeidlist.add(new Otherlike(otherUserLikeMemeidlist1.getOtheruserID(),
//                        otherUserLikeMemeidlist1.getOtherlisememeID()));
//        }
//        }
//
//        System.out.println("评价为5且目标用户没有的表情包"+otherUserLikeAndMyNotMemeidlist.size());
//
//        //排除重复推荐(A、B、C)相似用户同时推荐表情包a
//        ArrayList<Otherlike>NotrepeatMemeidlist=new ArrayList<Otherlike>();
//        NotrepeatMemeidlist.add(new Otherlike(otherUserLikeAndMyNotMemeidlist.get(0).getOtheruserID(),
//                otherUserLikeAndMyNotMemeidlist.get(0).getOtherlisememeID()));
//        for (int i=0;i<otherUserLikeAndMyNotMemeidlist.size()-1;i++) {
//            int k=0;
//            for (int j = 0; j <otherUserLikeAndMyNotMemeidlist.size() - 1; j++) {
//                if (otherUserLikeAndMyNotMemeidlist.get(i).getOtherlisememeID()
//                        == otherUserLikeAndMyNotMemeidlist.get(j + 1).getOtherlisememeID()) {
//                    break;
//                }
//                k=j;
//            }
//                if (k+1>= otherUserLikeAndMyNotMemeidlist.size() - 1) {
//                    NotrepeatMemeidlist.add(new Otherlike(otherUserLikeAndMyNotMemeidlist.get(i + 1).getOtheruserID(),
//                            otherUserLikeAndMyNotMemeidlist.get(i + 1).getOtherlisememeID()));
//
//            }
//        }
//
//        System.out.println("取出重复后的表情包个数"+NotrepeatMemeidlist.size());
//        //存储推荐给目标用户的表情包
//        ArrayList<Entitymeme> tuijianList=new ArrayList<Entitymeme>();
//        int k=0;
//        if (NotrepeatMemeidlist.size()>20){
//                k=20;}
//            else {
//                k=NotrepeatMemeidlist.size();}
//        for (int i=0;i<k;i++) {
////            Entitymeme memeFindOne = entitymemeRepository.findOne(otherUserLikeMemeidlist.get(i).getOtherlisememeID());
//            tuijianList.add( entitymemeRepository.findOne(NotrepeatMemeidlist.get(i).getOtherlisememeID()));
//        }
//        System.out.println("k的值"+k);
//        System.out.println("推荐的表情包数"+tuijianList.size());
//        System.out.println(tuijianList.get(0).getMemeID());
//        return tuijianList;
//    }


}
