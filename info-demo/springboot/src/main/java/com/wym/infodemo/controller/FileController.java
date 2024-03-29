package com.wym.infodemo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.wym.infodemo.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
	@Value("${server.port}")
	private String port;

	private static final String ip = "http://localhost";

	/**
	 * 上传接口
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public Result<?> upload(MultipartFile file) throws IOException {
		String originalFilename = file.getOriginalFilename(); //获取源文件的名称
		//定义文件的唯一标识
		String flag = IdUtil.fastSimpleUUID();
		//获取上传的路径
		String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/" + flag + "_" + originalFilename;
		FileUtil.writeBytes(file.getBytes(), rootFilePath); //把文件写入上传的路径
		return Result.success(ip + ":" + port + "/files/" + flag); //返回结果
	}

	/**
	 * 下载接口
	 *
	 * @param flag
	 * @param response
	 */
	@GetMapping("/{flag}")
	public void getFiles(@PathVariable String flag, HttpServletResponse response) {
		OutputStream os; //新建一个输出流对象
		//定义文件上传的根路径
		String basePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";
		List<String> fileNames = FileUtil.listFileNames(basePath); //获取所有文件名称
		//找到跟参数一致的文件
		String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
		try {
			if (StrUtil.isNotEmpty(fileName)) {
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
				response.setContentType("application/octet-stream");
				byte[] bytes = FileUtil.readBytes(basePath + fileName); // 通过文件的路径读取文件字节流
				os = response.getOutputStream(); //通过输出流返回文件
				os.write(bytes);
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			System.out.println("文件下载失败");
		}
	}
}
