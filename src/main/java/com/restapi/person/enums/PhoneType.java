package com.restapi.person.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {
	HOME("Home"),
	MOBILE("Mobile"),
	COMMERCIAL("Commercial"),
	EMERGENCY("Emergency contact");
	
//	PhoneType(String string) {
//		this.description = string;
//	}

	private final String description;
}
