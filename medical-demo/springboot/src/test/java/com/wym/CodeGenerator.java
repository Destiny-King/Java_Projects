package com.wym;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC";
		String username = "root";
		String password = "root";
		String moduleName = "sys";
		String mapperLocation = "D:\\Program\\Java\\IDEA\\Java_Project\\medical-demo\\springboot\\src\\main\\resources\\mapper\\" + moduleName;
		String tables = "rm_user,rm_role,rm_menu,rm_user_role,rm_role_menu";

		FastAutoGenerator.create(url, username, password)
				.globalConfig(builder -> {
					builder.author("wym") // 设置作者
							//.enableSwagger() // 开启 swagger 模式
							//.fileOverride() // 覆盖已生成文件
							.outputDir("D:\\Program\\Java\\IDEA\\Java_Project\\medical-demo\\springboot\\src\\main\\java"); // 指定输出目录
				})
				.packageConfig(builder -> {
					builder.parent("com.wym") // 设置父包名
							.moduleName(moduleName) // 设置父包模块名
							.pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
				})
				.strategyConfig(builder -> {
					builder.addInclude(tables) // 设置需要生成的表名
							.addTablePrefix("rm_"); // 设置过滤表前缀
				})
				.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}
}
