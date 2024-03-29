package com.wym.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * mp代码生成器
 */

public class CodeGenerator {
	public static void main(String[] args) {
		generate();
	}

	private static void generate() {
		FastAutoGenerator.create("jdbc:mysql://localhost:3306/demo?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "root")
				.globalConfig(builder -> {
					builder.author("wym") // 设置作者
							.enableSwagger() // 开启 swagger 模式
							.fileOverride() // 覆盖已生成文件
							.outputDir("management-demo/src/main/java/"); // 指定输出目录
				})
				.packageConfig(builder -> {
					builder.parent("com.wym.springboot") // 设置父包名
							.moduleName(null) // 设置父包模块名
							.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "management-demo/springboot/src/main/resources/mapper/")); // 设置mapperXml生成路径
				})
				.strategyConfig(builder -> {
					builder.addInclude("sys_user") // 设置需要生成的表名
							.addTablePrefix("t_", "sys_"); // 设置过滤表前缀
				})
				//.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}
}
