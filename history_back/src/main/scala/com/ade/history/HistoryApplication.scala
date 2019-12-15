package com.ade.history

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * @author: shudj
  * @time: 2019/12/13 10:30
  * @description:
  */
@SpringBootApplication
@MapperScan(Array("com.ade.history.mapper"))
class HistoryApplication
object HistoryApplication {
    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[HistoryApplication])
    }
}
