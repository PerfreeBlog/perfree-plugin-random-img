package com.random.controller.auth.randomImg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * @description 随机图 AddReqVO
 * @author Perfree
 **/
@Schema(description = "随机图AddReqVO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RandomImgUpdateReqVO extends RandomImgBaseVO{

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键不能为空")
    private Integer id;
}
