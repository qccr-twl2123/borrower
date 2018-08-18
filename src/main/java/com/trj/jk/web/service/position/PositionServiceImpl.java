package com.trj.jk.web.service.position;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trj.jk.web.domain.MobileLocationInterval;
import com.trj.jk.web.domain.entity.ErrorMessageConstant;
import com.trj.jk.web.domain.exception.CheckException;
import com.trj.jk.web.mapper.MobileLocationIntervalMapper;
import com.trj.jk.web.service.IThreadTaskService;
import com.trj.jk.web.service.position.IPositionService;
import com.trj.jk.web.task.async.ChangeAdressTask;

@Service
@Transactional
public class PositionServiceImpl implements IPositionService{

	@Resource
	private MobileLocationIntervalMapper mobileLocationIntervalMapper;
	
	@Resource
	private IThreadTaskService threadTaskService;
	
	@Override
	public Integer position(MobileLocationInterval mobileLocationInterval) {
		if(mobileLocationInterval.getLoanApplyId()==null){
			throw new CheckException(ErrorMessageConstant.ERR_LOAN_APPLY_ID_ERROR);
		}
		mobileLocationIntervalMapper.insertSelective(mobileLocationInterval);
		threadTaskService.asyncExecute(new ChangeAdressTask(mobileLocationInterval));
		return mobileLocationInterval.getId();
	}

}
