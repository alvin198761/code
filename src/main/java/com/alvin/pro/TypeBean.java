package com.alvin.pro;

import lombok.Data;

@Data
public class TypeBean {


	public TypeBean() {
	}

	public TypeBean(String typeName, String db_type, Integer defaultLength) {
		this.typeName = typeName;
		this.db_type = db_type;
		this.defaultLength = defaultLength;
	}

	private String typeName;
	private String db_type;
	private Integer defaultLength;

}
