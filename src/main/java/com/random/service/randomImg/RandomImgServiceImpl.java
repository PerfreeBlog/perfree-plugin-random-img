package com.random.service.randomImg;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.perfree.commons.common.PageResult;
import com.perfree.commons.exception.ServiceException;
import com.perfree.commons.utils.MyBatisUtils;
import com.perfree.commons.utils.SortingFieldUtils;
import com.random.controller.auth.randomImg.vo.*;
import com.random.convert.randomImg.RandomImgConvert;
import com.random.mapper.AttachLibraryMapper;
import com.random.mapper.RandomImgMapper;
import com.random.model.AttachLibrary;
import com.random.model.RandomImg;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 随机图 ServiceImpl
 * @author Perfree
 **/
@Service
public class RandomImgServiceImpl extends ServiceImpl<RandomImgMapper, RandomImg> implements RandomImgService {

    @Resource
    private RandomImgMapper randomImgMapper;

    private final AttachLibraryMapper attachLibraryMapper;

    public RandomImgServiceImpl(AttachLibraryMapper attachLibraryMapper) {
        this.attachLibraryMapper = attachLibraryMapper;
    }


    @Override
    public PageResult<RandomImgRespVO> randomImgPage(RandomImgPageReqVO pageVO) {
        IPage<RandomImgRespVO> page = MyBatisUtils.buildPage(pageVO, pageVO.getSortingFields());
        IPage<RandomImgRespVO> randomImgIPage = randomImgMapper.randomImgIPage(page, pageVO);
        return new PageResult<>(randomImgIPage.getRecords(), randomImgIPage.getTotal());
    }

    @Override
    @Transactional
    public RandomImg add(RandomImgAddReqVO randomImgAddReqVO) {
        RandomImg randomImg = RandomImgConvert.INSTANCE.convertAddReqVO(randomImgAddReqVO);
        RandomImg queryRandomImg = randomImgMapper.queryBySlug(randomImgAddReqVO.getSlug());
        if (null != queryRandomImg) {
            throw new ServiceException(100001, "访问标识不能重复");
        }
        randomImgMapper.insert(randomImg);
        return randomImg;
    }

    @Override
    @Transactional
    public RandomImg update(RandomImgUpdateReqVO randomImgUpdateReqVO) {
        RandomImg randomImg = RandomImgConvert.INSTANCE.convertUpdateReqVO(randomImgUpdateReqVO);
        RandomImg queryRandomImg = randomImgMapper.queryBySlug(randomImgUpdateReqVO.getSlug());
        if (null != queryRandomImg && !randomImg.getId().equals(queryRandomImg.getId())) {
            throw new ServiceException(100001, "访问标识不能重复");
        }
        randomImgMapper.updateById(randomImg);
        return randomImg;
    }

    @Override
    public RandomImg get(Integer id) {
        return randomImgMapper.selectById(id);
    }

    @Override
    @Transactional
    public Boolean del(Integer id) {
        randomImgMapper.deleteById(id);
        return true;
    }

    @Override
    public List<RandomImg> listAll() {
        return randomImgMapper.listAll();
    }

    @Override
    public List<RandomImg> queryExportData(RandomImgExportReqVO exportReqVO) {
        return randomImgMapper.queryExportData(exportReqVO);
    }

    @Override
    public List<AttachLibrary> listAllImgAttachLibrary() {
        return attachLibraryMapper.listAllImgAttachLibrary();
    }
}
