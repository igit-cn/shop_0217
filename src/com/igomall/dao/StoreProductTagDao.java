/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.dao;

import java.util.List;

import com.igomall.Page;
import com.igomall.Pageable;
import com.igomall.entity.Store;
import com.igomall.entity.StoreProductTag;

/**
 * Dao - 店铺商品标签
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface StoreProductTagDao extends BaseDao<StoreProductTag, Long> {

	/**
	 * 查找店铺商品标签
	 * 
	 * @param store
	 *            店铺
	 * @param isEnabled
	 *            是否启用
	 * @return 店铺商品标签
	 */
	List<StoreProductTag> findList(Store store, Boolean isEnabled);

	/**
	 * 查找店铺商品标签分页
	 * 
	 * @param store
	 *            店铺
	 * @param pageable
	 *            分页
	 * @return 店铺商品标签分页
	 */
	Page<StoreProductTag> findPage(Store store, Pageable pageable);

}