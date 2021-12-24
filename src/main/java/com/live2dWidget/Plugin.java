package com.live2dWidget;

import com.perfree.commons.Constants;
import com.perfree.permission.AdminGroup;
import com.perfree.permission.AdminGroups;
import com.perfree.plugin.BasePlugin;
import org.pf4j.PluginWrapper;


@AdminGroups(groups = {
        @AdminGroup(name = "插件管理", groupId = Constants.ADMIN_MENU_GROUP_PLUGIN)
})
public class Plugin extends BasePlugin {
    public Plugin(PluginWrapper wrapper) {
        super(wrapper);
    }
}
