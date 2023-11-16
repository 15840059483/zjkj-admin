package com.jeebase.system.security.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeebase.system.common.component.SQLAdapter;

public interface HomePageMapper extends BaseMapper<Object>{

	/**
	 * 获取时间段内 时间集合
	 * @param map
	 * @return
	 */
	public Map<String, Object> get30DayInfo(Map<String, Object> map);
	
	/**
	 * 获取时间内数据和
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getList(SQLAdapter sql);
}
