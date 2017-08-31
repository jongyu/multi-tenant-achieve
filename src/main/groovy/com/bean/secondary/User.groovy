package com.bean.secondary

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Entity
@Table(name = "user")
class User {

    @Id
    Long id
    String name

}
