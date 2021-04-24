package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xezzon
 */
@Getter
@Setter
public class DepartmentRole {
    private Department department;
    private Role role;

    @Override
    public String toString() {
        return department + ":" + role.getName();
    }
}
