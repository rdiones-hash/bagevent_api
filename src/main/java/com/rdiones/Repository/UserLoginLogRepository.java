package com.rdiones.Repository;

import com.rdiones.entity.UserLoginLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginLogRepository extends CrudRepository<UserLoginLog, String>, JpaSpecificationExecutor<UserLoginLog> {

}
