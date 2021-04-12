package com.tiny.common.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author wangwei
 */
public class JavaTimeModule extends SimpleModule {

	public JavaTimeModule() {
		super(PackageVersion.VERSION);
		this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateFormat.yyyy_MM_dd_HHmmss));
		this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateFormat.yyyy_MM_dd_HHmmss));
		this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateFormat.yyyy_MM_dd_HHmmss));

		this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateFormat.yyyy_MM_dd_HHmmss));
		this.addSerializer(LocalDate.class, new LocalDateSerializer(DateFormat.yyyy_MM_dd_HHmmss));
		this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateFormat.yyyy_MM_dd_HHmmss));
	}

}
