package indi.xezzon.school.passport.repository;

import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.common.model.StubDO;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface AccountMapper {
    /**
     * 新建一个账号
     *
     * @param record 用户名、密码
     * @return 账号id
     */
    int insert(Account record);
    
    /**
     * 查询账号
     *
     * @param id 账号id
     * @return 账号信息
     */
    Account selectByPrimaryKey(Long id);
    
    /**
     * 获取principal和credential
     *
     * @param username 用户名
     * @return id：principal；cipher：credential
     */
    StubDO selectStubByUsername(String username);
    
    /**
     * 修改密码、状态，自动更新updatedTime，危险操作：修改用户名
     *
     * @param record 用户id、修改的信息
     * @return 修改的行数
     */
    int updateByPrimaryKey(Account record);
}
