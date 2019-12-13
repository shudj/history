package com.ade.history.mapper

import org.springframework.stereotype.Repository
import java.util
/**
  * @author: shudj
  * @time: 2019/12/13 10:51
  * @description:
  */
@Repository
trait MenuMapper {

    def listMenu(): util.List[Any]
}
