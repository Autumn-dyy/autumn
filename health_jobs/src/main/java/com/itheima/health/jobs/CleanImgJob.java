package com.itheima.health.jobs;

import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CleanImgJob {
    private static final Logger log= LoggerFactory.getLogger(CleanImgJob.class);
    @Reference
    private SetmealService setmealService;
    //定时清理7牛里的图片，也可设置成日期，或者间隔时间
    @Scheduled(initialDelay = 3000, fixedDelay = 1800000)
    public void cleanImg(){
        log.info("开始执行清理垃圾图片...");
        //调用QiNiuUtils.查询所有图片
        List<String> imgIn7Niu = QiNiuUtils.listFile();
        //｛｝点为辅
        //查询出数据库的所有图片
        List<String> imgInDb=setmealService.findImgs();
        //7牛的图片减掉数据库的图片，imgIn7Niu中剩下的就是要删除的垃圾图片
        imgIn7Niu.remove(imgInDb);
        //把剩下的图片（要删除的垃圾图片）转成数组
        String[] strings = imgIn7Niu.toArray(new String[]{});
        //删除7牛上的这个数组
        QiNiuUtils.removeFiles(strings);


    }
}
