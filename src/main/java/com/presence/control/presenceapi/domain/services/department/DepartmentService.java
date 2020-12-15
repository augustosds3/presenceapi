package com.presence.control.presenceapi.domain.services.department;

import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;

public interface DepartmentService {

    DepartmentDTO createDepartment(Department department, Long departmentLocalId);

}
