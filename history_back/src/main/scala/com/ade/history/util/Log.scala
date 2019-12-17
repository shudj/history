package com.ade.history.util

import org.slf4j.LoggerFactory

/**
  * @author: shudj
  * @time: 2019/12/17 15:30
  * @description:
  */
trait Log {

    protected lazy implicit val logging = LoggerFactory.getLogger(getClass)

    def info(message: => String) = {
        if (logging.isInfoEnabled) {
            logging.info(String.valueOf(message))
        }
    }

    def debug(message: => String) =
        if (logging.isDebugEnabled) logging.debug(String.valueOf(message))

    def error(message: => String) =
        if (logging.isErrorEnabled) logging.error(String.valueOf(message))

    def trace(message: => String) =
        if (logging.isTraceEnabled) logging.trace(String.valueOf(message))
}
