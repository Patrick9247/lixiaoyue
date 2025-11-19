package com.lixiaoyue.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lixiaoyue.model.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户 Mapper 接口（基础 CRUD，继承 MyBatis-Plus BaseMapper）
 * 无需写 SQL，BaseMapper 已提供 17+ 个基础方法（selectById、insert、updateById 等）
 */
@Repository // 标识为持久层组件（可选，MyBatis-Plus 会自动扫描）
public interface UserMapper extends BaseMapper<User> {
    // 基础 CRUD 无需添加任何方法，直接使用 BaseMapper 提供的方法：
    // 示例：
    // 1. 新增：insert(User entity)
    // 2. 根据 ID 查询：selectById(Long id)
    // 3. 更新：updateById(User entity)
    // 4. 删除：deleteById(Long id)
    // 5. 条件查询：selectList(QueryWrapper<User> queryWrapper)
}