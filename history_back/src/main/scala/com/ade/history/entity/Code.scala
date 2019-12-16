package com.ade.history.entity

/**
 * 返回code
 */
object Code extends Enumeration {

    type Code = Value
    /**
     * 成功
     */
    val OK = Value(0, "200")
    /**
     * 传入参数错误
     */
    val REQUEST_PARAM_ERROR = Value(1, "2001")
    /**
     * 网络内部错误
     */
    val INTERNET_ERROR = Value(2, "2002")
}
