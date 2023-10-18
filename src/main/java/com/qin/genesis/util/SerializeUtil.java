package com.qin.genesis.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * 序列化/反序列工具
 */
public class SerializeUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SerializeUtil.class);

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

	}

	/**
	 * 将json反序列化为对象
	 *
	 * @param <T>
	 * @param json
	 * @param t
	 * @return
	 *
	 * @author rf62
	 * @date Dec 28, 2019 2:13:32 PM
	 */
	public static <T> T deserialization(String json, Class<T> t) {
		try {
			T t1 = OBJECT_MAPPER.readValue(json, t);
			return t1;
		} catch (Exception e) {
			LOGGER.error("Json convert to {} object exception: {}, json: {}", t.getClass(), ExceptionUtils.getStackTrace(e), json);
			throw new RuntimeException(e);
		}
	}

	/**
	 * JSON反序列化成泛型集合
	 *
	 * @param <T>
	 * @param json
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 *
	 * @author rf62
	 * @date Dec 28, 2019 2:13:44 PM
	 */
	public static <T> T deserialization(String json, Class<?> collectionClass, Class<?>... elementClasses) {
		try {
			JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
			T t1 = OBJECT_MAPPER.readValue(json, javaType);
			return t1;
		} catch (Exception e) {
			LOGGER.error("Json convert to {} object exception: {}, json: {}", collectionClass.getClass(), ExceptionUtils.getStackTrace(e), json);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 序列化
	 *
	 * @param <T>
	 * @param t
	 * @return
	 *
	 * @author rf62
	 * @date Dec 28, 2019 2:13:54 PM
	 */
	public static <T> String serialize(T t) {
		try {
			return OBJECT_MAPPER.writeValueAsString(t);
		} catch (IOException e) {
			LOGGER.error("Object {} to json exception: {}", t.getClass(), ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 *
	 *
	 * @param <T>
	 * @param t
	 * @param features
	 * @return
	 *
	 * @author rf62
	 * @date Dec 28, 2019 2:14:07 PM
	 */
	public static <T> String serialize(T t, SerializationFeature... features) {
		try {
			ObjectWriter writer = null;
			if (features != null && features.length > 0) {
				writer = OBJECT_MAPPER.writer(features[0], Arrays.copyOfRange(features, 1, features.length));
			} else {
				writer = OBJECT_MAPPER.writer();
			}
			return writer.writeValueAsString(t);
		} catch (IOException e) {
			LOGGER.error("Object {} to json exception: {}", t.getClass(), ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
}