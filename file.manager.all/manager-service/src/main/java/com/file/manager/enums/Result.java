package com.file.manager.enums;

public enum Result {
	Result_400("0"), Result_200("1");

	private final String value;

	Result(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
