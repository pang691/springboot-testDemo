package com.taikang.test.upload;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.taikang.test.upload.bean.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
@RestController
@Api(tags = "文件上传")
@Slf4j
public class UploadController {

    /**
     * 基于用户标识的头像上传
     * @param file 图片
     * @param  用户标识
     * @return
     */
//    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResultVo fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) {
//        ResultVo resultVo = new ResultVo();
//        if (!file.isEmpty()) {
//            if (file.getContentType().contains("image")) {
//                try {
//                    String temp = "images" + File.separator + "upload" + File.separator;
//                    // 获取图片的文件名
//                    String fileName = file.getOriginalFilename();
//                    // 获取图片的扩展名
//                    String extensionName = StringUtils.substringAfter(fileName, ".");
//                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
//                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
//                    // 数据库保存的目录
//                    String datdDirectory = temp.concat(String.valueOf(userId)).concat(File.separator);
//                    // 文件路径
//                    String filePath = webUploadPath.concat(datdDirectory);
//
//                    File dest = new File(filePath, newFileName);
//                    if (!dest.getParentFile().exists()) {
//                        dest.getParentFile().mkdirs();
//                    }
//                    // 判断是否有旧头像，如果有就先删除旧头像，再上传
//                   // SUser userInfo = sUserService.findUserInfo(userId.toString());
//                    if (StringUtils.isNotBlank(userInfo.getUserHead())) {
//                        String oldFilePath = webUploadPath.concat(userInfo.getUserHead());
//                        File oldFile = new File(oldFilePath);
//                        if (oldFile.exists()) {
//                            oldFile.delete();
//                        }
//                    }
//                    // 上传到指定目录
//                    file.transferTo(dest);
//
//                    // 将图片流转换进行BASE64加码
//                    //BASE64Encoder encoder = new BASE64Encoder();
//                    //String data = encoder.encode(file.getBytes());
//
//                    // 将反斜杠转换为正斜杠
////                    String data = datdDirectory.replaceAll("\\\\", "/") + newFileName;
////                    Map<String, Object> resultMap = new HashMap<>();
////                    resultMap.put("file", data);
////                    resultVo.setData(resultMap);
////                    resultVo.setError(1, "上传成功!");
//                } catch (IOException e) {
//                   // resultVo.setError(0, "上传失败!");
//                }
//            } else {
//                //resultVo.setError(0, "上传的文件不是图片类型，请重新上传!");
//            }
//            return resultVo;
//        } else {
//            //resultVo.setError(0, "上传失败，请选择要上传的图片!");
//            return resultVo;
//        }
//    }
//
//}
    @Value("${web.upload-path}")
    private String webUploadPath;

    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "请求token", required = false, paramType = "header", dataType = "String"),
            @ApiImplicitParam(name = "hosCode", value = "医院编码", required = false, paramType = "form", dataType = "String")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })
    public String upload(@ApiParam(value = "医院图片", required = true) MultipartFile file) {
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    String temp = "images" + File.separator + "upload" + File.separator;
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = fileName.substring(fileName.indexOf("."));
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String datdDirectory = temp.concat(String.valueOf(1)).concat(File.separator);
                    // 文件路径
                    String filePath = webUploadPath.concat(datdDirectory);

                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    file.transferTo(dest);
                    return "上传成功";
                }catch (Exception e){
                    return "上传失败";
                }
            }
        }
        return "上传成功";
    }
    //文件下载相关代码
    @GetMapping("/downloadImage")
    @ApiOperation(value = "下载图片", notes = "下载图片", httpMethod = "GET",
    produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageName", value = "图片名", required = false, paramType = "query", dataType = "String")
    })
    public String downloadImage(String imageName, HttpServletResponse response) {
        //String fileName = "123.JPG";
        log.debug("the imageName is : "+imageName);
        String uploadDir = "E:\\images\\upload\\1\\";
        String fileUrl = uploadDir+imageName;
        if (fileUrl != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
           /* String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");*/
            /*File file = new File(realPath, fileName);*/
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/x-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @PostMapping(value = "/uploadFile", consumes = "multipart/*", headers = {"content-type=multipart/form-data","content-type=application/json"})
    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "POST",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户信息", required = false, paramType = "body", dataType = "User"),
            @ApiImplicitParam(name = "files", value = "用户信息", required = false, paramType = "File", dataType = "query")

    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "上传成功！"),
            @ApiResponse(code = 500, message = "上传失败！")
    })

    public String uploadFile(@ApiParam(value = "医院图片", required = true) MultipartFile[] files,@ModelAttribute User user) {
        //可以通过对象接受参数，但是参数是key:value形式，不能为json格式，加@ModelAttribute或者不加都可以，如果用字段名接受，
        //应该可以直接接受，或者用requestparam
        System.out.println(user);
        for(MultipartFile file : files) {
            if (!file.isEmpty()) {
                if (file.getContentType().contains("image")) {
                    try {
                        String temp = "images" + File.separator + "upload" + File.separator;
                        // 获取图片的文件名
                        String fileName = file.getOriginalFilename();//可以覆盖之前上传的一样的文件
                        // 获取图片的扩展名
//                        String extensionName = fileName.substring(fileName.indexOf("."));
//                        // 新的图片文件名 = 获取时间戳+"."图片扩展名
//                        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                        // 数据库保存的目录
                        String datdDirectory = temp.concat(String.valueOf(1)).concat(File.separator);
                        // 文件路径
                        String filePath = webUploadPath.concat(datdDirectory);

                        File dest = new File(filePath, fileName);
                        if (!dest.getParentFile().exists()) {
                            dest.getParentFile().mkdirs();
                        }
                        // 上传到指定目录
                        file.transferTo(dest);
                    } catch (Exception e) {
                        return "上传失败";
                    }
                }
            }
        }
        return "上传成功";
    }

}
