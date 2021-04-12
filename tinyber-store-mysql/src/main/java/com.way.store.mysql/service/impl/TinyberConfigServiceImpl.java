package com.maersk.common.dao.service.impl;

import com.tiny.store.mysql.entity.TinyberConfig;
import com.maersk.common.dao.mapper.TinyberConfigMapper;
import com.maersk.common.dao.service.ITinyberConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author yangjun
 * @since 2021-03-28
 */
@Service
public class TinyberConfigServiceImpl extends ServiceImpl<TinyberConfigMapper, TinyberConfig> implements ITinyberConfigService {

}
