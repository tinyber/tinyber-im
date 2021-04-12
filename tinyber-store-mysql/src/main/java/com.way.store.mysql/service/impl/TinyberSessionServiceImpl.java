package com.maersk.common.dao.service.impl;

import com.tiny.store.mysql.entity.TinyberSession;
import com.maersk.common.dao.mapper.TinyberSessionMapper;
import com.maersk.common.dao.service.ITinyberSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 在线设备表 服务实现类
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Service
public class TinyberSessionServiceImpl extends ServiceImpl<TinyberSessionMapper, TinyberSession> implements ITinyberSessionService {

}
