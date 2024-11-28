package com.random.controller.auth.randomImg.vo;

import com.alibaba.excel.annotation.*;
import com.alibaba.excel.annotation.format.*;
import com.perfree.commons.excel.*;
import com.perfree.constant.UserConstant;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class RandomImgExcelVO {

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("附件库id")
    private Integer attachLibraryId;

    @ExcelProperty("是否开启0:开启,1关闭")
    private Integer status;

    @ExcelProperty("访问次数")
    private Integer visitCount;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
