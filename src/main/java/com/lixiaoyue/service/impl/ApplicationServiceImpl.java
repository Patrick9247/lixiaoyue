package com.lixiaoyue.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lixiaoyue.mapper.ApplicationMapper;
import com.lixiaoyue.model.entity.Application;
import com.lixiaoyue.service.IApplicationService;
import org.springframework.stereotype.Service;


@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements IApplicationService {

}
