package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: yang
 * @Date: 2019/2/1 13:58
 * @Version 1.0
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
