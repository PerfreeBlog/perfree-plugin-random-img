package com.random.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.perfree.commons.mapper.BaseMapperX;
import com.random.model.AttachLibrary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @description 附件库
* @author Perfree
*/
@Mapper
public interface AttachLibraryMapper extends BaseMapperX<AttachLibrary> {

    default List<AttachLibrary> listAllImgAttachLibrary(){
        return selectList(new LambdaQueryWrapper<AttachLibrary>()
                .eq(AttachLibrary::getType, "img")
                .orderByDesc(AttachLibrary::getCreateTime)
        );
    }
}