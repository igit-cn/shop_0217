
package com.igomall.audit;

/**
 * Audit - 审计者Provider
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
public interface AuditorProvider<T> {

	/**
	 * 获取当前审计者
	 * 
	 * @return 当前审计者
	 */
	T getCurrentAuditor();

}