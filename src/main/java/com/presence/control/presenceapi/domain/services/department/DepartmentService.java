package com.presence.control.presenceapi.domain.services.department;

import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(Department department, Long departmentLocalId);

    String subscribeUser(Long departmentId, Long userId);

    List<DepartmentDTO> findAllDepartments();

    List<DepartmentDTO> findAllUserDepartments(Long userId);

}
