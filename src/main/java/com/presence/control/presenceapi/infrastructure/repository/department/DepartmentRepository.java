package com.presence.control.presenceapi.infrastructure.repository.department;

import com.presence.control.presenceapi.domain.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Long countByDepartmentNameAndDepartmentLocal_Id(String departmentName, Long departmentLocalId);

    List<Department> findAllBySubscribedUsers_Id(Long userId);
}
