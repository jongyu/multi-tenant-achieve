package com.bean.primary

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by ZhongYu on 31/08/2017.
 */
@Entity
@Table(name = "brand")
class Brand {

    @Id
    Long id
    String name

}
