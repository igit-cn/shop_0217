
package com.igomall.template.directive;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.igomall.plugin.PromotionPlugin;
import com.igomall.service.PluginService;
import com.igomall.util.FreeMarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 促销插件
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Component
public class PromotionPluginDirective extends BaseDirective {

	/**
	 * "促销插件ID"参数名称
	 */
	private static final String PROMOTION_PLUGIN_ID_PARAMETER_NAME = "promotionPluginId";

	/**
	 * 变量名称
	 */
	private static final String VARIABLE_NAME = "promotionPlugin";

	@Inject
	private PluginService pluginService;

	/**
	 * 执行
	 * 
	 * @param env
	 *            环境变量
	 * @param params
	 *            参数
	 * @param loopVars
	 *            循环变量
	 * @param body
	 *            模板内容
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String promotionPluginId = FreeMarkerUtils.getParameter(PROMOTION_PLUGIN_ID_PARAMETER_NAME, String.class, params);
		PromotionPlugin promotionPlugin = pluginService.getPromotionPlugin(promotionPluginId);
		setLocalVariable(VARIABLE_NAME, promotionPlugin, env, body);
	}

}