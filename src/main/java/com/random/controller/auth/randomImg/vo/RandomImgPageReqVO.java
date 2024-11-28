package com.random.controller.auth.randomImg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.perfree.commons.common.PageParam;
import java.util.*;
import jakarta.validation.constraints.*;
import lombok.*;


/**
* @description 随机图 分页ReqVO
* @author Perfree
**/
@Schema(description = "随机图分页ReqVO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RandomImgPageReqVO extends PageParam {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "是否开启0:开启,1关闭")
    private Integer status;
}
