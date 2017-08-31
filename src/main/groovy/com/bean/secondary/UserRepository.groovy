package com.bean.secondary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {

}