/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.dao;

import java.util.Date;
import java.util.List;

import com.igomall.entity.Statistic;
import com.igomall.entity.Store;

/**
 * Dao - 统计
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface StatisticDao extends BaseDao<Statistic, Long> {

	/**
	 * 判断统计是否存在
	 * 
	 * @param type
	 *            类型
	 * @param store
	 *            店铺
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 统计是否存在
	 */
	boolean exists(Statistic.Type type, Store store, int year, int month, int day);

	/**
	 * 分析
	 * 
	 * @param type
	 *            类型
	 * @param store
	 *            店铺
	 * @param period
	 *            周期
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 统计
	 */
	List<Statistic> analyze(Statistic.Type type, Store store, Statistic.Period period, Date beginDate, Date endDate);

}