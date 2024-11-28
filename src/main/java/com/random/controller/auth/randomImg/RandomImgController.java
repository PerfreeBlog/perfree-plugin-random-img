package com.random.controller.auth.randomImg;


import com.perfree.commons.common.CommonResult;
import com.perfree.commons.common.PageResult;
import com.perfree.commons.excel.ExcelUtils;
import com.random.controller.auth.randomImg.vo.*;
import com.random.convert.randomImg.RandomImgConvert;
import com.random.model.AttachLibrary;
import com.random.model.RandomImg;
import com.random.service.randomImg.RandomImgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.perfree.commons.common.CommonResult.success;

/**
* @description 随机图 controller
* @author Perfree
**/
@RestController
@Tag(name = "随机图相关接口")
@RequestMapping("api/auth/randomImg")
public class RandomImgController {

    @Resource
    private RandomImgService randomImgService;

    @PostMapping("/page")
    @Operation(summary = "随机图分页列表")
    public CommonResult<PageResult<RandomImgRespVO>> page(@RequestBody RandomImgPageReqVO pageVO) {
        PageResult<RandomImgRespVO> randomImgPageResult = randomImgService.randomImgPage(pageVO);
        return success(randomImgPageResult);
    }

    @PostMapping("/add")
    @Operation(summary = "添加随机图")
    public CommonResult<RandomImgRespVO> add(@RequestBody @Valid RandomImgAddReqVO randomImgAddReqVO) {
        return success(RandomImgConvert.INSTANCE.convertRespVO(randomImgService.add(randomImgAddReqVO)));
    }

    @PostMapping("/update")
    @Operation(summary = "更新随机图")
    public CommonResult<RandomImgRespVO> update(@RequestBody @Valid RandomImgUpdateReqVO randomImgUpdateReqVO) {
        return success(RandomImgConvert.INSTANCE.convertRespVO(randomImgService.update(randomImgUpdateReqVO)));
    }

    @GetMapping("/get")
    @Operation(summary = "根据id获取随机图")
    public CommonResult<RandomImgRespVO> get(@RequestParam(value = "id") Integer id) {
        return success(RandomImgConvert.INSTANCE.convertRespVO(randomImgService.get(id)));
    }

    @DeleteMapping("/del")
    @Operation(summary = "根据id删除随机图")
    public CommonResult<Boolean> del(@RequestParam(value = "id") Integer id) {
        return success(randomImgService.del(id));
    }

    @GetMapping("/listAll")
    @Operation(summary = "获取所有随机图")
    public CommonResult<List<RandomImgRespVO>> listAll() {
        return success(RandomImgConvert.INSTANCE.convertListRespVO(randomImgService.listAll()));
    }

    @GetMapping("/listAllImgAttachLibrary")
    @Operation(summary = "获取所有图片附件库")
    public CommonResult<List<AttachLibrary>> listAllImgAttachLibrary() {
        return success(randomImgService.listAllImgAttachLibrary());
    }

    @PostMapping("/export")
    @Operation(summary = "导出随机图")
    public void export(@RequestBody RandomImgExportReqVO exportReqVO, HttpServletResponse response) {
        List<RandomImg> randomImgList = randomImgService.queryExportData(exportReqVO);
        ExcelUtils.renderExcel(response, RandomImgConvert.INSTANCE.convertToExcelVOList(randomImgList), RandomImgExcelVO.class, "随机图数据","随机图数据.xlsx");
    }
}