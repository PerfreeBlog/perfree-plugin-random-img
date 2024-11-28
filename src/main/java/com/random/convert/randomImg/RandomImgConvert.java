package com.random.convert.randomImg;

import com.perfree.commons.common.PageResult;
import com.random.controller.auth.randomImg.vo.*;
import com.random.model.RandomImg;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * @description 随机图 Convert
 * @author Perfree
 **/
@Mapper(componentModel = "spring")
public interface RandomImgConvert {
    RandomImgConvert INSTANCE = Mappers.getMapper(RandomImgConvert.class);

    /**
     * model转RespVO
     * @param randomImg randomImg
     * @return RandomImgRespVO
     */
    RandomImgRespVO convertRespVO(RandomImg randomImg);

    /**
     * model PageResult转RespVO PageResult
     * @param randomImgPageResult randomImgPageResult
     * @return PageResult
     */
    PageResult<RandomImgRespVO> convertPageResultVO(PageResult<RandomImg> randomImgPageResult);

    /**
     * AddReqVO转model
     * @param randomImgAddReqVO randomImgAddReqVO
     * @return RandomImg
     */
    RandomImg convertAddReqVO(RandomImgAddReqVO randomImgAddReqVO);

    /**
     * UpdateReqVO转model
     * @param randomImgUpdateReqVO randomImgUpdateReqVO
     * @return RandomImg
     */
    RandomImg convertUpdateReqVO(RandomImgUpdateReqVO randomImgUpdateReqVO);

    /**
     * model List转RespVO List
     * @param list list
     * @return List<RandomImgRespVO>
     */
    List<RandomImgRespVO> convertListRespVO(List<RandomImg> list);

    /**
     * model List转ExcelVO List
     * @param list list
     * @return List<RandomImgExcelVO>
     */
    List<RandomImgExcelVO> convertToExcelVOList(List<RandomImg> list);
}