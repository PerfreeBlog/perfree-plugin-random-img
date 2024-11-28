package com.random.controller.auth.randomImg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @description 随机图 RespVO
 * @author Perfree
 **/
@Schema(description = "随机图RespVO")
@Data
@EqualsAndHashCode(callSuper = true)
public class RandomImgRespVO extends RandomImgBaseVO{

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "访问次数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer visitCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String attachLibraryName;
}
