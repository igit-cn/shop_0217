/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.service;

import com.igomall.entity.PluginConfig;

/**
 * Service - 插件配置
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface PluginConfigService extends BaseService<PluginConfig, Long> {

	/**
	 * 判断插件ID是否存在
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return 插件ID是否存在
	 */
	boolean pluginIdExists(String pluginId);

	/**
	 * 根据插件ID查找插件配置
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return 插件配置，若不存在则返回null
	 */
	PluginConfig findByPluginId(String pluginId);

	/**
	 * 根据插件ID删除插件配置
	 * 
	 * @param pluginId
	 *            插件ID
	 */
	void deleteByPluginId(String pluginId);

}