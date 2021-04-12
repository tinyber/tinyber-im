package com.maersk.common.dao.service.impl;

import com.tiny.store.mysql.entity.TinyberGroup;
import com.maersk.common.dao.mapper.TinyberGroupMapper;
import com.maersk.common.dao.service.ITinyberGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 群组表 服务实现类
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Service
public class TinyberGroupServiceImpl extends ServiceImpl<TinyberGroupMapper, TinyberGroup> implements ITinyberGroupService {

}
