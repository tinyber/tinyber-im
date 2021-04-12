package com.tiny.common.util;

import com.tiny.common.packet.Client;
import com.tiny.common.packet.ImSessionContext;
import org.apache.commons.lang3.StringUtils;
import org.tio.core.ChannelContext;

import java.util.ArrayList;
import java.util.List;

public class ImUtils {

	/**
	 * 设置Client对象到ImSessionContext中
	 * @param channelContext
	 * @return
	 * @author: wchao
	 */
	public static Client setClient(ChannelContext channelContext) {
		ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
		Client client = imSessionContext.getClient();
		if (client == null) {
			client = new Client();
			client.setId(channelContext.getId());
			client.setIp(channelContext.getClientNode().getIp());
			client.setPort(channelContext.getClientNode().getPort());
			imSessionContext.setClient(client);
		}
		return client;
	}

	public static String formatRegion(String region) {
		if (StringUtils.isBlank(region)) {
			return "";
		}
		//.split("|");
		String[] arr = StringUtils.split(region, "|");
		List<String> validArr = new ArrayList<>();
		for (String e : arr) {
			if (StringUtils.isNotBlank(e) && !"0".equals(e)) {
				validArr.add(e);
			}
		}
		if (validArr.size() == 0) {
			return "";
		} else if (validArr.size() == 1) {
			return validArr.get(0);
		} else {
			return validArr.get(validArr.size() - 2) + validArr.get(validArr.size() - 1);
		}
	}

	/**
	 * @param args
	 * @author: wchao
	 */
	public static void main(String[] args) {
		String region = "中国|杭州|铁通";
		String xx = formatRegion(region);
		System.out.println(xx);
	}
}
