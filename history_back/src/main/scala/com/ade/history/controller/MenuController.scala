package com.ade.history.controller

import java.util

import com.ade.history.server.MenuServer
import javax.annotation.Resource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation._

/**
  * @author: shudj
  * @time: 2019/12/13 10:34
  * @description:
  */
@RestController
@CrossOrigin
@RequestMapping(Array("/menu"))
class MenuController {

    @Resource
    @Qualifier("menuServer")
    private[this] val menuServer: MenuServer = null

    @GetMapping(Array("/listMenu"))
    def listMenu(): util.ArrayList[Any] = {

        menuServer.listMenu()
    }
}
