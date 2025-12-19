package com.lixiaoyue.controller;

import com.google.common.net.MediaType;
import com.lixiaoyue.common.BusinessResponse;
import com.lixiaoyue.exception.BusinessException;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.vo.CourseVO;
import com.lixiaoyue.model.vo.FileVO;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传控制器（适配springdoc-openapi）
 * 解决Swagger3标签重复/为空问题
 */
@RestController
@RequestMapping("/file")
// 自定义Swagger标签，关闭默认类名标签后仅显示此名称
@Api(tags = "文件管理", description = "单文件/多文件上传接口")
public class FileController {

    // 文件存储根路径（可配置到application.yml）
    private static final String UPLOAD_DIR = "D:/upload/";
    private static final String UPLOAD_FILE_FRONT = "file:///";

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

        // 2. 创建存储目录（不存在则创建）
        File uploadDirFile = new File(UPLOAD_DIR);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        // 3. 处理文件名（避免重复，用UUID+原文件名）
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalFilename;
        File destFile = new File(UPLOAD_DIR + fileName);

        // 4. 写入文件到指定路径
        try {
            file.transferTo(destFile);
            FileVO fileVO = new FileVO();
            fileVO.setFileName(fileName);
            fileVO.setPath(UPLOAD_FILE_FRONT+UPLOAD_DIR + fileName);
            fileVO.setFileType(file.getContentType());

            //获取文件大小
            long size = file.getSize();
            double sizeInKB= size/1024.0;
            fileVO.setFileSize(String.format("%.2f", sizeInKB));
            return BusinessResponse.success(fileVO);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }


//    /**
//     * 多文件上传接口
//     */
//    @PostMapping("/upload/multi")
//    @Operation(
//            summary = "多文件上传",
//            description = "支持批量上传文件，总大小限制50MB"
//    )
//    public ResponseEntity<String> multiFileUpload(
//            @Parameter(description = "待上传的文件列表", required = true)
//            @RequestPart() List<MultipartFile> files
//    ) {
//        // 1. 校验文件列表
//        if (files.isEmpty()) {
//            return ResponseEntity.badRequest().body("上传失败：文件列表不能为空");
//        }
//
//        // 2. 创建存储目录
//        File uploadDirFile = new File(UPLOAD_DIR);
//        if (!uploadDirFile.exists()) {
//            uploadDirFile.mkdirs();
//        }
//
//        // 3. 批量处理文件
//        List<String> successFiles = new ArrayList<>();
//        List<String> failFiles = new ArrayList<>();
//        for (MultipartFile file : files) {
//            if (file.isEmpty()) {
//                failFiles.add("空文件");
//                continue;
//            }
//            String originalFilename = file.getOriginalFilename();
//            String fileName = UUID.randomUUID() + "_" + originalFilename;
//            File destFile = new File(UPLOAD_DIR + fileName);
//            try {
//                file.transferTo(destFile);
//                successFiles.add(originalFilename);
//            } catch (IOException e) {
//                failFiles.add(originalFilename + "：" + e.getMessage());
//            }
//        }
//
//        // 4. 返回上传结果
//        StringBuilder result = new StringBuilder();
//        result.append("成功上传：").append(String.join(",", successFiles)).append("\n");
//        if (!failFiles.isEmpty()) {
//            result.append("上传失败：").append(String.join(",", failFiles));
//        }
//        return ResponseEntity.ok(result.toString());
//    }

}
