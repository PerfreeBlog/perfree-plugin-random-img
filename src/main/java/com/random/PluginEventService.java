package com.random;

import com.perfree.constant.MenuConstant;
import com.perfree.plugin.BasePluginEvent;
import com.perfree.plugin.commons.PluginUtils;
import com.perfree.system.api.menu.MenuApi;
import com.perfree.system.api.menu.dto.MenuDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PluginEventService implements BasePluginEvent {

    @Resource
    private MenuApi menuApi;

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onInstall() {
        // 创建菜单
       /* String pluginId = PluginUtils.getPluginConfig(this.getClass()).getPlugin().getId();
        MenuDTO menuDTO = MenuDTO.builder()
                .moduleName("RandomImg")
                .menuType(MenuConstant.MENU_MENU_TYPE_MENU)
                .type(MenuConstant.MENU_TYPE_ADMIN)
                .pluginId(pluginId)
                .url("/admin/randomImg")
                .icon("fa-solid fa-image")
                .seq(99)
                .target(MenuConstant.MENU_TARGET_SELF)
                .name("随机图管理")
                .componentName("RandomImg")
                .component("/view/RandomImgView")
                .build();
        menuApi.createMenu(menuDTO);*/
    }

    @Override
    public void onUnInstall() {
        String pluginId = PluginUtils.getPluginConfig(this.getClass()).getPlugin().getId();
        // 卸载时删除插件菜单
        menuApi.deleteMenuByPluginId(pluginId);
    }
}
