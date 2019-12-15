package com.ade.history.server

/**
  * @author: shudj
  * @time: 2019/12/13 11:06
  * @description:
  */
trait MenuServer {

    /**
     * 获取菜单栏，用于左侧菜单栏
     * @return
     */
    def listMenu(): java.util.ArrayList[Any]

    /**
     * 获取菜单栏，用于菜单增删改
     * @return
     */
    def getMenus(): java.util.ArrayList[Any]

    def updateData(menu: String)
}
