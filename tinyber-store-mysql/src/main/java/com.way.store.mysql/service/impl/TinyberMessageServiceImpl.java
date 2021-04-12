package com.maersk.common.dao.service.impl;

import com.tiny.store.mysql.entity.TinyberMessage;
import com.maersk.common.dao.mapper.TinyberMessageMapper;
import com.maersk.common.dao.service.ITinyberMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息记录表 服务实现类
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Service
public class TinyberMessageServiceImpl extends ServiceImpl<TinyberMessageMapper, TinyberMessage> implements ITinyberMessageService {

}
