package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.model.dto.TextDTO;
import com.lixiaoyue.model.entity.Text;
import com.lixiaoyue.model.vo.TextVO;
import org.springframework.stereotype.Service;


public interface ITextService extends IService<Text> {
    TextVO saveText(TextDTO textDTO);

    TextVO getByUserAndHomework(Long userId, Long homeworkId);
}
