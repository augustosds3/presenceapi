package com.presence.control.presenceapi.infrastructure.repository.department;

import com.presence.control.presenceapi.data.domain.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Long countByDepartmentNameAndDepartmentLocal_Id(String departmentName, Long departmentLocalId);

}
