package com.random.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.perfree.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
* @description 附件库数据
* @author Perfree
*/
@Getter
@Setter
@TableName("p_attach_library_items")
public class AttachLibraryItems extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
    * 
    */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 附件库id
    */
    private Integer attachLibraryId;

    /**
    * url
    */
    private String url;


    /**
    * 文件名称
    */
    private String name;

    private String description;
}
