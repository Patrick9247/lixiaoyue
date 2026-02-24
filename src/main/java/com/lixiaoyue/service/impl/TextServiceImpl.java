package com.lixiaoyue.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.TextMapper;
import com.lixiaoyue.model.dto.TextDTO;
import com.lixiaoyue.model.entity.Text;
import com.lixiaoyue.model.vo.HomeworkVO;
import com.lixiaoyue.model.vo.TextVO;
import com.lixiaoyue.service.IHomeworkService;
import com.lixiaoyue.service.ITextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextServiceImpl extends ServiceImpl<TextMapper,Text> implements ITextService  {

    @Autowired
    IHomeworkService homeworkService;
    @Override
    public TextVO saveText(TextDTO textDTO) {
        Text one = this.getOne(new LambdaQueryWrapper<Text>()
                .eq(Text::getUserId, textDTO.getUserId())
                .eq(Text::getHomeworkId, textDTO.getHomeworkId()));
        if (ObjectUtils.isNotEmpty(one)){
            Text text = new Text();
            text.setId(one.getId());
            BeanUtil.copyProperties(textDTO, text);
            saveOrUpdate(text);
            return BeanUtil.copyProperties(text, TextVO.class);
        }
        Text text = new Text();
        BeanUtil.copyProperties(textDTO, text);
        save(text);
        return BeanUtil.copyProperties(text, TextVO.class);
    }

    @Override
    public TextVO getByUserAndHomework(Long userId, Long homeworkId) {

        Text one = this.getOne(new LambdaQueryWrapper<Text>()
                .eq(Text::getHomeworkId, homeworkId)
                .eq(Text::getUserId, userId));

        if (ObjectUtils.isEmpty(one)){
            return new TextVO();
        }
        return BeanUtil.copyProperties(one, TextVO.class);
    }
}
