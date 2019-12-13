package com.ade.history.server.impl

import java.util

import com.ade.history.mapper.MenuMapper
import com.ade.history.server.MenuServer
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

        println(buffer)
        buffer
    }
}
