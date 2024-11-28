package com.random.service.randomImg;

import com.baomidou.mybatisplus.extension.service.IService;
import com.perfree.commons.common.PageResult;
import com.random.controller.auth.randomImg.vo.*;
import com.random.model.AttachLibrary;
import com.random.model.RandomImg;

import java.util.List;

/**
 * @description 随机图 Service
 * @author Perfree
 **/
public interface RandomImgService extends IService<RandomImg> {

    /**
     * 随机图分页
     * @param pageVO pageVO
     * @return PageResult<RandomImg>
     */
    PageResult<RandomImgRespVO> randomImgPage(RandomImgPageReqVO pageVO);

    /**
     * 添加随机图
     * @param addReqVO addReqVO
     * @return RandomImg
     */
    RandomImg add(RandomImgAddReqVO addReqVO);

    /**
     * 更新随机图
     * @param updateReqVO updateReqVO
     * @return RandomImg
     */
    RandomImg update(RandomImgUpdateReqVO updateReqVO);

    /**
     * 根据id获取随机图信息
     * @param id id
     * @return RandomImg
     */
    RandomImg get(Integer id);

    /**
     * 根据id删除随机图
     * @param id id
     * @return Boolean
     */
    Boolean del(Integer id);

    /**
     * 获取所有随机图
     * @return List<RandomImg>
     */
    List<RandomImg> listAll();

    /**
     * 查询要导出的数据
     * @param exportReqVO exportReqVO
     * @return List<RandomImg>
     */
    List<RandomImg> queryExportData(RandomImgExportReqVO exportReqVO);

    List<AttachLibrary> listAllImgAttachLibrary();

}