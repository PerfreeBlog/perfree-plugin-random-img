package com.random.model;

import com.baomidou.mybatisplus.annotation.*;
import com.perfree.base.BaseModel;
import lombok.*;
import java.util.*;

import java.io.Serial;
import java.io.Serializable;

/**
* @description 随机图
* @author Perfree
*/
@Getter
@Setter
@TableName("p_random_img")
public class RandomImg extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
    * 主键
    */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 备注
    */
    private String remark;

    /**
    * 附件库id
    */
    private Integer attachLibraryId;

    /**
    * 是否开启0:开启,1关闭
    */
    private Integer status;

    /**
    * 访问次数
    */
    private Integer visitCount;

    private String slug;
}
