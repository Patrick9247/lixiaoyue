package com.lixiaoyue.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.google.common.net.MediaType;
import com.lixiaoyue.common.*;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.vo.CourseVO;
import com.lixiaoyue.model.vo.FileVO;
import com.lixiaoyue.model.vo.HomeworkDetailVO;
import com.lixiaoyue.service.IHomeworkDetailService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件上传控制器（适配springdoc-openapi）
 * 解决Swagger3标签重复/为空问题
 */
@RestController
@RequestMapping("/file")
@Slf4j
// 自定义Swagger标签，关闭默认类名标签后仅显示此名称
@Api(tags = "文件管理", description = "单文件/多文件上传接口")
public class FileController {

    @Autowired
    IHomeworkDetailService homeworkDetailService;
    @Autowired
    private AliOssUtil aliOssUtil;

    // 文件存储根路径（可配置到application.yml）
    private static final String UPLOAD_DIR = "D:/upload/";
    private static final String UPLOAD_FILE_FRONT = "file:///";
    private static final String ENDPOINT = "https://bailian.cdut.edu.cn";
    private static final String FILE_ENDPOINT = "/cre_llm/api/v1/application/upload_file";
    private static final String OPEN_CHAT_ENDPOINT = "/cre_llm/api/v1/application/open_chat";
    private static final String CHAT_ENDPOINT = "/cre_llm/api/v1/application/chat";
    private static final String APPLICATION_ID = "7ab5ec80-e17a-11f0-92fd-22cf6b01d411";
    private static final String AK = "f90c0aec426e104bacc277c2760350bc";

    /**
     * 单文件上传接口
     */
    @PostMapping(value = "/upload/single", consumes = "multipart/form-data")
    @Operation(
            summary = "单文件上传",
            description = "支持jpg/png/pdf等格式，单个文件大小限制10MB"
    )
    public BusinessResponse<FileVO> singleFileUpload(
            // Swagger参数说明
            @Parameter(description = "待上传的文件", required = true)
            @RequestPart() MultipartFile file
    ) {
        // 1. 校验文件是否为空
        if (file.isEmpty()) {
            throw new BusinessException("文件为空！");
        }


        log.info("文件上传：{}",file);

        try {
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀   dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称：防止上传到阿里云的文件，因为名字重复导致覆盖的问题
            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            //参数：  byte数组，文件对象转成的数组     传上去的图片在阿里云存储空间里面的名字
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            FileVO fileVO = new FileVO();
            fileVO.setFileName(objectName);
            fileVO.setPath(filePath);
            fileVO.setFileType(file.getContentType());
            fileVO.setFileSize(String.valueOf(file.getSize()/1024));
            return BusinessResponse.success(fileVO);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }

        return BusinessResponse.fail("文件上传失败，请重试！");
    }
    /**
     * 上传文件进行批改
     */
    @PostMapping(value = "/upload/file", consumes = "multipart/form-data")
    @Deprecated
    @Operation(
            summary = "上传文件到百炼（调试中）",
            description = "支持jpg/png/pdf等格式，单个文件大小限制10MB"
    )
    public BusinessResponse<String> uploadFile(// Swagger参数说明
                                         @Parameter(description = "待上传的文件", required = true)
                                         @RequestPart() MultipartFile multipartFile) throws Exception{
        String chatId = createChat();

        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        File file = new File(UPLOAD_DIR+multipartFile.getOriginalFilename());

        //创建请求对象
        HttpPost httpPost = new HttpPost(ENDPOINT+FILE_ENDPOINT);

        multipartFile.transferTo(file);

        // 构建Form-Data请求体
        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("application_id", APPLICATION_ID)
                .addBinaryBody("files", file)
                .build();

        httpPost.setEntity(entity);
        //指定请求编码方式
        //指定数据格式
        httpPost.setHeader("Ak", AK);


        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析返回结果
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);

        HttpEntity entity1 = response.getEntity();
        String stringEntity1 = EntityUtils.toString(entity1);
        BailianData bailianData = new BailianData();
        try{
            bailianData = JSON.parseObject(stringEntity1, BailianData.class);
            log.info("解析成功：{}",bailianData);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (bailianData.getCode()!=200){
            throw new BusinessException("上传失败请重试！");
        }

        //url存入数据库
//        HomeworkDetailVO finish = homeworkDetailService.finish(homeworkDetailVO);

        List<FileDetail> data = bailianData.getData();
        List<FileIDAndName> fileList = data.stream().map(fileDetail -> {
            FileIDAndName fileIDAndName = new FileIDAndName();
            fileIDAndName.setId(fileDetail.getId());
            fileIDAndName.setName(fileDetail.getFile_name());
            return fileIDAndName;
        }).collect(Collectors.toList());

        postJson(chatId,fileList);

        //关闭资源
        response.close();
        httpClient.close();

        return BusinessResponse.success(stringEntity1);
    }

    private String createChat() throws IOException {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpPost httpPost = new HttpPost("https://bailian.cdut.edu.cn/cre_llm/application/open_chat");

        //构造请求体
        cn.hutool.json.JSONObject jsonObject = new cn.hutool.json.JSONObject();
        jsonObject.put("application_id",APPLICATION_ID);
        jsonObject.put("is_debug",false);


        StringEntity entity = new StringEntity(jsonObject.toString());
        //指定请求编码方式
        entity.setContentEncoding("utf-8");
        //指定数据格式
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Ak", "f90c0aec426e104bacc277c2760350bc");

        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析返回结果
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);

        HttpEntity entity1 = response.getEntity();
        String stringEntity1 = EntityUtils.toString(entity1);
        System.out.println(stringEntity1);
        BailianChatData bailianChatData = JSON.parseObject(stringEntity1, BailianChatData.class);


        //关闭资源
        response.close();
        httpClient.close();

        return bailianChatData.getData();
    }
    @Deprecated
    @PostMapping(value = "/upload/correct", consumes = "multipart/form-data")
    @Operation(
            summary = "上传文件进行批改（调试中）",
            description = "支持jpg/png/pdf等格式，单个文件大小限制10MB"
    )
    public BusinessResponse<String> POST(// Swagger参数说明
                                         @Parameter(description = "待上传的文件", required = true)
                                         @RequestPart() MultipartFile file) throws Exception{

        return null;
    }

    private static String postJson(String chatId,List<FileIDAndName> fileIDAndNameList) throws IOException {
        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpPost httpPost = new HttpPost("https://bailian.cdut.edu.cn/cre_llm/application/chat");

        //构造请求体
        cn.hutool.json.JSONObject jsonObject = new cn.hutool.json.JSONObject();
        jsonObject.put("application_id",APPLICATION_ID);
        jsonObject.put("chat_id",chatId);
        jsonObject.put("is_debug",false);
        jsonObject.put("input","批改这个试卷");
        jsonObject.put("files", fileIDAndNameList);
        jsonObject.put("stream",true);
        jsonObject.put("question","批改这个作业");

        StringEntity entity = new StringEntity(jsonObject.toString());
        //指定请求编码方式
        entity.setContentEncoding("utf-8");
        //指定数据格式
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Ak", "f90c0aec426e104bacc277c2760350bc");

        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析返回结果
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);

        HttpEntity entity1 = response.getEntity();
        String stringEntity1 = EntityUtils.toString(entity1);
        System.out.println(stringEntity1);

        //关闭资源
        response.close();
        httpClient.close();

        return stringEntity1;
    }

    private void chatWithFile( List<FileIDAndName> fileList,String chatId) throws IOException {

        //创建httpclient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //创建请求对象
        HttpPost httpPost = new HttpPost(ENDPOINT+CHAT_ENDPOINT);

        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
        httpPost.setHeader("Ak", "f90c0aec426e104bacc277c2760350bc");

        JSONObject jsonParam = new JSONObject();
        jsonParam.put("chat_id", chatId);
        jsonParam.put("question", "批改这个作业");
        jsonParam.put("stream", false);
        jsonParam.put("files", fileList);




        // 3.2 构建 JSON 请求体：将 JSONObject 转换为 StringEntity（HTTP 请求实体）
        StringEntity requestEntity = new StringEntity(
                jsonParam.toJSONString(), // JSON 字符串
                StandardCharsets.UTF_8    // 明确指定编码，避免乱码
        );


        httpPost.setEntity(requestEntity);


        //指定请求编码方式
        //指定数据格式



        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析返回结果
        int code = response.getStatusLine().getStatusCode();
        System.out.println(code);

        HttpEntity entity1 = response.getEntity();
        String stringEntity1 = EntityUtils.toString(entity1);
        System.out.println(stringEntity1);
    }




    /**
     * 上传文件进行批改
     */
    @Deprecated
    @PostMapping(value = "/create/chat")
    @Operation(
            summary = "创建对话（调试中）"
    )
    public BusinessResponse<String> openChat() throws Exception{

        String chat = createChat();
        return BusinessResponse.success(chat);
    }

}
