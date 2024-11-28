package com.random.controller.auth.randomImg.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
* @description 随机图 BaseVO
* @author Perfree
**/
@Data
public class RandomImgBaseVO {


    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "名称不能为空")
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "附件库id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "附件库id不能为空")
    private Integer attachLibraryId;

    @Schema(description = "是否开启0:开启,1关闭", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否开启0:开启,1关闭不能为空")
    private Integer status;

    private String slug;
}
