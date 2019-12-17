package com.ade.history.mapper

import org.springframework.stereotype.Repository
import java.util

import org.apache.ibatis.annotations.Param
/**
  * @author: shudj
  * @time: 2019/12/13 10:51
  * @description:
  */
@Repository
trait MenuMapper {

    def listMenu(): util.ArrayList[Any]

    def getMenus(): util.ArrayList[Any]

    def updateData(@Param("id") id: Int,
                   @Param("name") name: String,
                   @Param("icon") icon: String,
                   @Param("url") url: String,
                   @Param("parentid") parentid: Int)
}
