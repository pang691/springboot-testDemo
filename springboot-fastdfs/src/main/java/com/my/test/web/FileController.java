package com.my.test.web;

import com.my.test.utils.CommonFileUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FileController {
	
	private final static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private CommonFileUtil fileUtil;
	
	@RequestMapping("/goIndex")
	public String goIndex(){
		logger.info("进入主页面");
		return "/file";
	}
	
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("fileName") MultipartFile file){
		
		String targetFilePath = "E:/opt/uploads/";
		
		if(file.isEmpty()){
			logger.info("this file is empty");
		}
		
		String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		//获取原来文件名称
		String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		if(!fileSuffix.equals(".jpg") || !fileSuffix.equals(".png")){
			logger.info("文件格式不正确");
		}
		//拼装新的文件名
		String targetFileName = targetFilePath + newFileName + fileSuffix;
		//上传文件
		try {
			FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(targetFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/success";
	}
	
	//使用fastdfs进行文件上传
	@RequestMapping("/uploadFileToFast")
	public String uoloadFileToFast(@RequestParam("fileName")MultipartFile file) throws IOException{
		
		if(file.isEmpty()){
			logger.info("文件不存在");
		}
		String path = fileUtil.uploadFile(file);
		System.out.println(path);
		return "success";
	}
	@ApiOperation(value = "下载图片", notes = "下载图片",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileUrl", value = "图片地址", required = false, paramType = "query", dataType = "String")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "下载成功！"),
			@ApiResponse(code = 500, message = "下载失败！")
	})
	@GetMapping("/download")
	public void downloadFile(String fileUrl, HttpServletResponse response) throws IOException {
		byte[] bytes = fileUtil.downloadFile(fileUrl);
		// 这里只是为了整合fastdfs，所以写死了文件格式。需要在上传的时候保存文件名。下载的时候使用对应的格式
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("1.jpg", "UTF-8"));
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
