package com.maersk.common.dao.service.impl;

import com.tiny.store.mysql.entity.TinyberUser;
import com.maersk.common.dao.mapper.TinyberUserMapper;
import com.maersk.common.dao.service.ITinyberUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Service
public class TinyberUserServiceImpl extends ServiceImpl<TinyberUserMapper, TinyberUser> implements ITinyberUserService {

}
