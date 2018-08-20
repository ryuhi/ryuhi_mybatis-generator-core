package org.mybatis.generator.internal.util;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.PluginAggregator;
import org.mybatis.generator.plugins.InsertIgnoreColumnPlugin;

public class SetIgnoreUtil {

	public static List<String> getInsertIgnoreColumns(Context context) {
		List<String> iicList = null;
        PluginAggregator pa = (PluginAggregator) context.getPlugins();
        InsertIgnoreColumnPlugin iicp = null;
        if (pa.isGetInsertIgnoreColumns()) {
        	List<Plugin> paList = pa.getPlugins();
        	if (paList != null && paList.size() > 0) {
        		for (Plugin pl : paList) {
					if (pl instanceof InsertIgnoreColumnPlugin) {
						iicp = (InsertIgnoreColumnPlugin)pl;
						break;
					}
				}
        	}
        	iicList = iicp.getInsertIgnoreColumns();
        }
        return iicList == null? new ArrayList<>() : iicList;
	}
}
