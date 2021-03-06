/*
* Copyright 2017 AMSTECH INCORPORATION PRIVATE LIMITED.
* (www.amstechinc.com)
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*     http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */

package com.amstech.demo.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RestResponse implements Map<String, Object> {
	public static final String KEY_STATUS = "status";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_DATA = "data";
	public static final String KEY_TOTAL_RECORDS = "totalRecords";
	public static final String KEY_TOTAL_PAGES = "totalPages";
	public static final String KEY_PAGE_NUMBER = "pageNo";
	public static final String KEY_ROW_COUNT = "rowCount";

	public static final String STATUS_ERROR = "error";
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAIL = "fail";

	private Map<String, Object> response = new LinkedHashMap<>();

	private RestResponse() {
	}

	public static RestResponse build() {
		return new RestResponse();
	}

	public RestResponse withTotalRecords(long value) {
		return add(KEY_TOTAL_RECORDS, value);
	}

	public RestResponse add(String name, Object value) {
		this.response.put(name, value);
		return this;
	}

	/**
	 * Sets message key for this response.
	 *
	 * @param message
	 *            the message
	 * @return the rest response
	 */
	public RestResponse withMessage(String message) {
		return add(KEY_MESSAGE, message);
	}

	/**
	 * Sets this response as error, setting the status and message key.
	 *
	 * @param error
	 *            message
	 * @return the rest response
	 */
	public RestResponse withError(String error) {
		return add(KEY_STATUS, STATUS_ERROR).add(KEY_MESSAGE, error);
	}

	/**
	 * Sets this response as success, setting the status and message key.
	 *
	 * @param message
	 *            the message
	 * @return the rest response
	 */
	public RestResponse withSuccess(String message) {
		return add(KEY_STATUS, STATUS_SUCCESS).add(KEY_MESSAGE, message);
	}

	/**
	 * Sets this response as success.
	 *
	 * @return the rest response
	 */
	public RestResponse withSuccess() {
		return add(KEY_STATUS, STATUS_SUCCESS);
	}

	/**
	 * Sets this response as fail.
	 *
	 * @return the rest response
	 */
	public RestResponse withFail() {
		return add(KEY_STATUS, STATUS_FAIL);
	}

	/**
	 * Creates a success response with data.
	 *
	 * @param message
	 *            the message
	 * @param data
	 *            the data
	 * @return the rest response
	 */
	public RestResponse withSuccess(String message, Object data) {
		return withSuccess(message, KEY_DATA, data);
	}

	/**
	 * Creates a success response with custom data.
	 *
	 * @param message
	 *            the message
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @return the rest response
	 */
	public RestResponse withSuccess(String message, String name, Object value) {
		return withSuccess(message).add(name, value);
	}

	/**
	 * Sets object as 'data' value.
	 *
	 * @param data
	 *            the data
	 * @return the rest response
	 */
	public RestResponse withData(Object data) {
		return add(KEY_DATA, data);
	}

	/**
	 * Creates a success response with custom data
	 *
	 * @param message
	 * @param name
	 * @param value
	 * @return
	 */
	public RestResponse withData(Long totalPages, Long totalSize, Integer pageNumber, String key, Object value) {
		if (!response.containsKey(KEY_DATA)) {
			add(KEY_TOTAL_RECORDS, totalSize).add(KEY_TOTAL_PAGES, totalPages).add(KEY_PAGE_NUMBER, pageNumber)
					// .add(KEY_DATA, new LinkedHashMap<String, Object>());
					.add(KEY_DATA, value);
		}
		return this;
	}

	/**
	 * With data.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the rest response
	 */
	@SuppressWarnings("unchecked")
	public RestResponse withData(String key, Object value) {
		if (!response.containsKey(KEY_DATA)) {
			add(KEY_DATA, new LinkedHashMap<String, Object>());
		}
		((Map<String, Object>) response.get(KEY_DATA)).put(key, value);
		return this;
	}

	/**
	 * Factory method for a success response.
	 *
	 * @return the rest response
	 */
	public static RestResponse success() {
		return new RestResponse().withSuccess();
	}

	/**
	 * Factory method for a fail response.
	 *
	 * @return the rest response
	 */
	public static RestResponse fail() {
		return new RestResponse().withFail();
	}

	/**
	 * Factory method for a success response.
	 *
	 * @param message
	 *            the message
	 * @return the rest response
	 */
	public static RestResponse success(String message) {
		return new RestResponse().withSuccess(message);
	}

	/**
	 * Factory method for a success response.
	 *
	 * @param message
	 *            the message
	 * @param data
	 *            the data
	 * @return the rest response
	 */
	public static RestResponse success(String message, Object data) {
		return RestResponse.success(message, KEY_DATA, data);
	}

	/**
	 * Success2.
	 *
	 * @param message
	 *            the message
	 * @param data
	 *            the data
	 * @return the rest response
	 */
	public static RestResponse success2(String message, Object data) {
		throw new IllegalStateException();
	}

	/**
	 * Success2.
	 *
	 * @return the rest response
	 */
	public static RestResponse success2() {
		throw new IllegalStateException();
	}

	/**
	 * Factory method for success.
	 *
	 * @param message
	 *            the message
	 * @param name
	 *            the name
	 * @param data
	 *            the data
	 * @return the rest response
	 */
	public static RestResponse success(String message, String name, Object data) {
		return new RestResponse().withSuccess(message).add(name, data);
	}

	/**
	 * Factory method for error response.
	 *
	 * @param errorMessage
	 *            the error message
	 * @return the rest response
	 */
	public static RestResponse error(String errorMessage) {
		return new RestResponse().withError(errorMessage);
	}

	/**
	 * Factory method for error response using the exception message.
	 *
	 * @param e
	 *            the exception
	 * @return the rest response
	 */
	public static RestResponse error(Exception e) {
		return new RestResponse().withError(e.getMessage());
	}

	/**
	 * Factory method for missing params errors.
	 *
	 * @param param
	 *            the param
	 * @return the rest response
	 */
	public static RestResponse missingParam(String param) {
		return new RestResponse().withError("Param " + param + " is missing");
	}

	@Override
	public int size() {
		return response.size();
	}

	@Override
	public boolean isEmpty() {
		return response.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return response.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return response.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return response.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return response.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return response.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		response.putAll(m);
	}

	@Override
	public void clear() {
		response.clear();

	}

	@Override
	public Set<String> keySet() {
		return response.keySet();
	}

	@Override
	public Collection<Object> values() {

		return response.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return response.entrySet();
	}

}
