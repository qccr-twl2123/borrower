package com.trj.jk.web.mapper;

import com.trj.jk.web.domain.Attachment;
import com.trj.jk.web.domain.AttachmentCriteria;
import com.trj.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachmentMapper extends BaseMapper<Attachment, AttachmentCriteria, Integer> {
}