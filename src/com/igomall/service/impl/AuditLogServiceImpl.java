/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.igomall.service.impl;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.igomall.dao.AuditLogDao;
import com.igomall.entity.AuditLog;
import com.igomall.service.AuditLogService;

/**
 * Service - 审计日志
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Service
public class AuditLogServiceImpl extends BaseServiceImpl<AuditLog, Long> implements AuditLogService {

	@Inject
	private AuditLogDao auditLogDao;

	@Async
	public void create(AuditLog auditLog) {
		auditLogDao.persist(auditLog);
	}

	public void clear() {
		auditLogDao.removeAll();
	}

}