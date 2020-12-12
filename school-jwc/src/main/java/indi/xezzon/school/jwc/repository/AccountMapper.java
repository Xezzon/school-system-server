package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface AccountMapper {
    Long insert(Account account);
}
