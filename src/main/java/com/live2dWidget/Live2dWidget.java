package com.live2dWidget;

import cn.hutool.core.util.StrUtil;
import com.perfree.commons.Constants;
import com.perfree.commons.OptionCacheUtil;
import com.perfree.plugin.proxy.HtmlRenderProxy;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Live2dWidget extends HtmlRenderProxy {

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
        String model = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_MODEL", "//unpkg.com/live2d-widget-model-shizuku@1.0.5/assets/shizuku.model.json");;
        String position = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_POSITION", "left");
        String width = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_WIDTH", "150");
        String height = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_HEIGHT", "300");
        String hOffset = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_HOFFSET", "0");
        String vOffset = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_VOFFSET", "0");
        String mobileIsShow = OptionCacheUtil.getDefaultValue("LIVE2D_WIDGET_MOBILE_IS_SHOW", "false");
        js = StrUtil.format(js, model, position, width, height, hOffset, vOffset, mobileIsShow);
        document.body().append(js);
        return document;
    }
}
