package com.zhongyu.controller

import com.bean.primary.BrandRepository
import com.bean.secondary.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Created by ZhongYu on 31/08/2017.
 */
@RestController
class TenantController {

    @Autowired
    BrandRepository brandRepository

    @Autowired
    UserRepository userRepository

    @GetMapping('{tenant}')
    def tenant(@PathVariable String tenant) {
        println "loading data from $tenant"
        final brands = brandRepository.findAll().toList().subList(0, 3)
        final users = userRepository.findAll().toList().subList(0, 3)
        return [
                teant : tenant,
                brands: brands,
                users : users
        ]
    }

}
