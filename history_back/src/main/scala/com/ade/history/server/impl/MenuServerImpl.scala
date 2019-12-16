package com.ade.history.server.impl

import java.util

import com.ade.history.exception.{InternetException, ParamsException}
import com.ade.history.mapper.MenuMapper
import com.ade.history.server.MenuServer
import com.alibaba.fastjson.{JSON, JSONObject}
import org.springframework.beans.factory.annotation.{Autowired, Qualifier}
import org.springframework.stereotype.Service

/**
  * @author: shudj
  * @time: 2019/12/13 11:07
  * @description:
  */
@Service
@Qualifier("menuServer")
class MenuServerImpl extends MenuServer{

    @Autowired
    private[this] val menuMapper: MenuMapper = null

    override def listMenu(): util.ArrayList[Any] = {
        try {
            val buffer = new util.ArrayList[Any]()
            import scala.collection.JavaConversions._
            val array = menuMapper.listMenu()
            var children: util.ArrayList[Any] = null
            array.foreach(arr => {
                val map = arr.asInstanceOf[util.Map[String, Any]]
                if (map.get("parentid") == null) {
                    children = new util.ArrayList[Any]()
                    map.put("children", children)
                    buffer.add(map)
                } else {
                    map.put("children", Array())
                    children.add(map)
                }
            })

            buffer
        } catch {
            case e: Exception => throw new InternetException(e.getMessage)
        }
    }

    /**
     * 获取菜单栏，用于菜单增删改
     *
     * @return
     */
    override def getMenus(): util.ArrayList[Any] = {
        try {
            menuMapper.listMenu()
        } catch {
            case e: Exception => throw new InternetException(e.getMessage)
        }
    }

    /**
     * 更新菜单
     * @param menu
     */
    override def updateData(menu: String): Unit = {
        try {
            val array = JSON.parseArray(menu)
            for (i <- 0 until array.size()) {
                val json = array.getJSONObject(i)
                if (!json.containsKey("id")) {
                    throw new ParamsException(s"更新菜单需要传入参数id")
                }
                if (!json.containsKey("name")) {
                    throw new ParamsException("更新菜单需要参入参数name")
                }
                val id = json.getInteger("id")
                val name = json.getString("name")
                var icon: String = null
                if (json.containsKey("icon")) {
                    icon = json.getString("icon")
                }
                var parentid: Int = 0
                if (json.containsKey("parentid")) {
                    parentid = json.getInteger("parentid")
                }
                var url: String = null
                if (json.containsKey("url")) {
                    url = json.getString("url")
                }
                menuMapper.updateData(id, name, icon, url, parentid)
            }
        } catch {
            case e: Exception => throw new InternetException(e.getMessage)
        }
    }
}
