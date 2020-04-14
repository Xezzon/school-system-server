package indi.xezzon.school.repository;

import indi.xezzon.school.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface AccountMapper {
    /**
     * 新建一个账号
     * @param record 用户名、密码
     * @return 账号id
     */
    int insert(Account record);
    
    /**
     * 查询账号
     * @param username 用户名
     * @return 账号信息
     */
    Account selectByUsername(String username);
    
    /**
     * 修改密码、状态，自动更新updatedTime，危险操作：修改用户名
     * @param record 用户id、修改的信息
     * @return 修改的行数
     */
    int updateByPrimaryKey(Account record);
}
