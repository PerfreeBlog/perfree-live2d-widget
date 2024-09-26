package com.live2dWidget;

import com.perfree.cache.OptionCacheService;
import com.perfree.plugin.proxy.HtmlRenderProxy;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dromara.hutool.core.text.StrUtil;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class Live2dWidget extends HtmlRenderProxy {

    @Resource
    private OptionCacheService optionCacheService;


    @Override
    public Document editFrontDocument(Document document, HttpServletResponse response, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/register") ) {
            return document;
        }
        document.body().append("<script src='//l2dwidget.js.org/lib/L2Dwidget.min.js'></script>");
        String js = "<script> L2Dwidget.init({\n" +
                "        \"model\": {\n" +
                "            \"jsonPath\": \"{}\"\n" +
                "          , \"scale\": 1, \"hHeadPos\":0.5, \"vHeadPos\":0.618 \n" +
                "        },\n" +
                "        \"display\": {\n" +
                "            \"position\": \"{}\",\n" +
                "            \"width\": {},\n" +
                "            \"height\": {},\n" +
                "           \"hOffset\": {},\n" +
                "            \"vOffset\": {}\n" +
                "        },\n" +
                "        \"mobile\": {\n" +
                "            \"show\": {},\n" +
                "            \"scale\": 0.5\n" +
                "        }\n" +
                "    });</script>";
        String model = optionCacheService.getDefaultValue("LIVE2D_WIDGET_MODEL", "//unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json");;
        String position = optionCacheService.getDefaultValue("LIVE2D_WIDGET_POSITION", "left");
        String width = optionCacheService.getDefaultValue("LIVE2D_WIDGET_WIDTH", "150");
        String height = optionCacheService.getDefaultValue("LIVE2D_WIDGET_HEIGHT", "300");
        String hOffset = optionCacheService.getDefaultValue("LIVE2D_WIDGET_HOFFSET", "0");
        String vOffset = optionCacheService.getDefaultValue("LIVE2D_WIDGET_VOFFSET", "0");
        String mobileIsShow = optionCacheService.getDefaultValue("LIVE2D_WIDGET_MOBILE_IS_SHOW", "false");
        js = StrUtil.format(js, model, position, width, height, hOffset, vOffset, mobileIsShow);
        document.body().append(js);
        return document;
    }
}
