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
public class RandomImgAddReqVO extends RandomImgBaseVO{
}
