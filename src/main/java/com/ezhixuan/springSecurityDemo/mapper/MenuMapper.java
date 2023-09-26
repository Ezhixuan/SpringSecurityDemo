package com.ezhixuan.springSecurityDemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezhixuan.springSecurityDemo.domain.entitiy.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);
}
