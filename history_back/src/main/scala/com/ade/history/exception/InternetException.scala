package com.ade.history.exception

class InternetException(message: String) extends RuntimeException{
    override def getMessage = message
}
