package com.random.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.perfree.commons.common.PageResult;
import com.perfree.commons.mapper.BaseMapperX;
import com.random.controller.auth.randomImg.vo.*;
import com.random.model.RandomImg;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import com.perfree.commons.utils.DateTimeUtils;

import java.util.List;
import java.util.Objects;




/**
* @description 随机图
* @author Perfree
*/
@Mapper
public interface RandomImgMapper extends BaseMapperX<RandomImg> {

    default PageResult<RandomImg> selectPage(RandomImgPageReqVO reqVO) {
        LambdaQueryWrapper<RandomImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(reqVO.getName()), RandomImg::getName, reqVO.getName());
        lambdaQueryWrapper.eq(null != reqVO.getStatus(), RandomImg::getStatus, reqVO.getStatus());
        lambdaQueryWrapper.orderByDesc(RandomImg::getId);
        return selectPage(reqVO, lambdaQueryWrapper);
    }

    default List<RandomImg> listAll() {
        return selectList(new LambdaQueryWrapper<RandomImg>()
            .orderByDesc(RandomImg::getId)
        );
    }

    default List<RandomImg> queryExportData(RandomImgExportReqVO reqVO){
        LambdaQueryWrapper<RandomImg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(reqVO.getName()), RandomImg::getName, reqVO.getName());
        lambdaQueryWrapper.eq(null != reqVO.getStatus(), RandomImg::getStatus, reqVO.getStatus());
        lambdaQueryWrapper.orderByDesc(RandomImg::getId);
        return selectList(lambdaQueryWrapper);
    }

    IPage<RandomImgRespVO> randomImgIPage(IPage<RandomImgRespVO> page, @Param("pageVO") RandomImgPageReqVO pageVO);

    default RandomImg queryBySlug(String slug){
        return selectOne(new LambdaQueryWrapper<RandomImg>()
                .eq(RandomImg::getSlug, slug)
        );
    }

}