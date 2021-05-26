package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DepartmentMapperTest {
    @Autowired
    private DepartmentMapper mapper;

    @Test
    void query() {
        List<Department> departments = mapper.query(Department.builder().name("测绘工程").build(), null, null, null, null);
        log.debug("{}", departments);
    }

    @Test
    void queryByPrimaryKey() {
        Department department = mapper.queryByPrimaryKey(46L);
        log.debug("{}", department);
    }
}