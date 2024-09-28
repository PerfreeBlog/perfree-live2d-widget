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

    private final String OPTION_IDENTIFICATION = "plugin_perfree-live2d-widget";

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
        String model = optionCacheService.getDefaultValue("LIVE2D_WIDGET_MODEL", OPTION_IDENTIFICATION,"//unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json");;
        String position = optionCacheService.getDefaultValue("LIVE2D_WIDGET_POSITION", OPTION_IDENTIFICATION,"left");
        String width = optionCacheService.getDefaultValue("LIVE2D_WIDGET_WIDTH", OPTION_IDENTIFICATION,"150");
        String height = optionCacheService.getDefaultValue("LIVE2D_WIDGET_HEIGHT", OPTION_IDENTIFICATION,"300");
        String hOffset = optionCacheService.getDefaultValue("LIVE2D_WIDGET_HOFFSET", OPTION_IDENTIFICATION,"0");
        String vOffset = optionCacheService.getDefaultValue("LIVE2D_WIDGET_VOFFSET", OPTION_IDENTIFICATION,"0");
        String mobileIsShow = optionCacheService.getDefaultValue("LIVE2D_WIDGET_MOBILE_IS_SHOW", OPTION_IDENTIFICATION,"false");
        js = StrUtil.format(js, model, position, width, height, hOffset, vOffset, mobileIsShow);
        document.body().append(js);
        return document;
    }
}
