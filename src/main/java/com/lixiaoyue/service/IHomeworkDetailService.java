package com.lixiaoyue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lixiaoyue.common.PageVO;
import com.lixiaoyue.model.dto.HomeworkDetailDTO;
import com.lixiaoyue.model.dto.HomeworkDetailQueryDTO;
import com.lixiaoyue.model.dto.HomeworkStudentQueryDTO;
import com.lixiaoyue.model.dto.StudentHomeworkUserDTO;
import com.lixiaoyue.model.entity.Homework;
import com.lixiaoyue.model.entity.HomeworkDetail;
import com.lixiaoyue.model.vo.HomeworkDetailVO;

public interface IHomeworkDetailService extends IService<HomeworkDetail> {
    Boolean create(Homework homework);

    HomeworkDetailVO finish(HomeworkDetailDTO homeworkDetailDTO);

    HomeworkDetailVO getHomeworkDetail(Long homeworkId, Long userId);

    PageVO<HomeworkDetailVO> getStudentHomeworkPage(HomeworkDetailQueryDTO homeworkDetailPageQueryVO);

    PageVO<StudentHomeworkUserDTO> getUserPageByHomework(HomeworkStudentQueryDTO homeworkStudentQueryDTO);
}
