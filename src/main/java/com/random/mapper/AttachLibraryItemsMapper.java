package com.random.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.perfree.commons.mapper.BaseMapperX;
import com.random.model.AttachLibraryItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @description 附件库数据
* @author Perfree
*/
@Mapper
public interface AttachLibraryItemsMapper extends BaseMapperX<AttachLibraryItems> {

    default List<AttachLibraryItems> queryByAttachLibraryId(Integer attachLibraryId){
        return selectList(new LambdaQueryWrapper<AttachLibraryItems>()
                .eq(AttachLibraryItems::getAttachLibraryId, attachLibraryId)
        );
    }
}