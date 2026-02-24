package com.lixiaoyue.controller;



import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.lixiaoyue.common.BusinessResponse;


import com.lixiaoyue.model.dto.TextDTO;
import com.lixiaoyue.model.entity.User;
import com.lixiaoyue.model.vo.TextVO;
import com.lixiaoyue.service.ITextService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/text")
@Slf4j
// 自定义Swagger标签，关闭默认类名标签后仅显示此名称
@Api(tags = "文本管理", description = "文本管理")
public class TextController {
    @Autowired
    ITextService textService;
    @Autowired
    RedisTemplate redisTemplate;

    private final String TITLE = "title";
    private final String CONTENT = "content";

    @PostMapping("/save")
    @Operation(summary = "保存文本内容")
    public BusinessResponse<TextVO> save(@RequestBody TextDTO textDTO) {
        TextVO textVO = textService.saveText(textDTO);
        return BusinessResponse.success(textVO);
    }

    @PostMapping("/cache")
    @Operation(summary = "暂存文件")
    public BusinessResponse<TextVO> cache(@RequestBody TextDTO textDTO) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        TextVO vo = textService.getByUserAndHomework(textDTO.getUserId(), textDTO.getHomeworkId());
        String titleKey = TITLE+textDTO.getUserId().toString() + textDTO.getHomeworkId().toString();
        String contentKey = CONTENT+ textDTO.getUserId() + textDTO.getHomeworkId();
        valueOperations.set(titleKey,
                textDTO.getTitle());
        valueOperations.set(contentKey,
                textDTO.getContent());
        return BusinessResponse.success(BeanUtil.copyProperties(textDTO,TextVO.class));
    }

    @GetMapping("/get/cache")
    @Operation(summary = "获取暂存文件")
    public BusinessResponse<TextVO> getCache(@Param("userId") Long userId, @Param("homeworkId") Long homeworkId) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String titleKey = TITLE+ userId+homeworkId;
        String contentKey = CONTENT+ userId+homeworkId;
        String title = valueOperations.get(titleKey).toString();
        String content = valueOperations.get(contentKey).toString();
        TextVO textVO = new TextVO();
        textVO.setTitle(title);
        textVO.setContent(content);
        textVO.setHomeworkId(homeworkId);
        textVO.setUserId(userId);
        return BusinessResponse.success(textVO);
    }

    @GetMapping("/get")
    @Operation(summary = "获取保存的文本")
    public BusinessResponse<TextVO> register(@Param("userId") Long userId, @Param("homeworkId") Long homeworkId) {
        TextVO textVO = textService.getByUserAndHomework(userId,homeworkId);
        return BusinessResponse.success(textVO);
    }
}
