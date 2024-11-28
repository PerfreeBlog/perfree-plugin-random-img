package com.random.controller.common.ranomImg;

import com.random.mapper.AttachLibraryItemsMapper;
import com.random.mapper.RandomImgMapper;
import com.random.model.AttachLibraryItems;
import com.random.model.RandomImg;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class RandomImgCommonController {

    @Resource
    private RandomImgMapper randomImgMapper;

    private final AttachLibraryItemsMapper attachLibraryItemsMapper;

    public RandomImgCommonController(AttachLibraryItemsMapper attachLibraryItemsMapper) {
        this.attachLibraryItemsMapper = attachLibraryItemsMapper;
    }

    @GetMapping("/randomImg/{slug}")
    public String randomImg(@PathVariable String slug) {
        RandomImg randomImg = randomImgMapper.queryBySlug(slug);
        if (null == randomImg || !randomImg.getStatus().equals(0)) {
            return  "redirect:/404";
        }
        List<AttachLibraryItems> attachLibraryItemsList = attachLibraryItemsMapper.queryByAttachLibraryId(randomImg.getAttachLibraryId());
        if (attachLibraryItemsList == null || attachLibraryItemsList.isEmpty()) {
            return  "redirect:/404";
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(attachLibraryItemsList.size());
        randomImg.setVisitCount(randomImg.getVisitCount() + 1);
        randomImgMapper.updateById(randomImg);
        return "redirect:" + attachLibraryItemsList.get(randomIndex).getUrl();
    }
}
