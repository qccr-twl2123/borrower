package com.trj.jk.web.service.escrow;


import com.trj.jk.web.model.dto.EscrowConfirmDTO;
import com.trj.jk.web.model.dto.EscrowOpenDTO;

/**
 * 银行存管服务类
 * @author -ming-
 *
 */
public interface IEscrowService {

	/**
	 * 存管开户信息获取
	 * @param uid
	 * @return
	 */
	public EscrowOpenDTO escrowOpen(Integer uid, byte clientType);
	
	/**
	 * 存管开户回调
	 * @param escrowConfirmDTO
	 */
	public void escrowConfirm(EscrowConfirmDTO escrowConfirmDTO);

}
