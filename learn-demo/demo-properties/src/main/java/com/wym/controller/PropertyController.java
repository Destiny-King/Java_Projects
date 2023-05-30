package com.wym.controller;

import cn.hutool.core.lang.Dict;
import com.wym.property.ApplicationProperty;
import com.wym.property.DeveloperProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
	//final修饰类表明该类不能被继承,需要初始化
	private final ApplicationProperty applicationProperty;
	private final DeveloperProperty developerProperty;

	@Autowired
	public PropertyController(ApplicationProperty applicationProperty, DeveloperProperty developerProperty) {
		this.applicationProperty = applicationProperty;
		this.developerProperty = developerProperty;
	}

	@GetMapping("/property")
	public Dict index() {
		return Dict.create().set("applicationProperty", applicationProperty)
				.set("developerProperty", developerProperty);
	}
}
