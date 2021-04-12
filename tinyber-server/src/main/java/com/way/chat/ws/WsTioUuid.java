package com.tiny.chat.ws;

import org.tio.core.intf.TioUuid;
import org.tio.utils.hutool.Snowflake;

import java.util.concurrent.ThreadLocalRandom;

public class WsTioUuid implements TioUuid {

	private Snowflake snowflake;

	public WsTioUuid() {
		snowflake = new Snowflake(ThreadLocalRandom.current().nextInt(1, 30), ThreadLocalRandom.current().nextInt(1, 30));
	}

	public WsTioUuid(long workerId, long datacenterId) {
		snowflake = new Snowflake(workerId, datacenterId);
	}

	/**
	 * @return
	 * @author tanyaowu
	 */
	@Override
	public String uuid() {
		return snowflake.nextId() + "";
	}
}
