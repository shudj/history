package com.ade.history.exception

class ParamsException(message: String) extends RuntimeException{

    override def getMessage = message
}
