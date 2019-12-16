package com.ade.history.util

import com.ade.history.entity.{Code, Resp}
import com.ade.history.exception.{InternetException, ParamsException}
import org.springframework.core.MethodParameter
import org.springframework.http.{HttpStatus, MediaType}
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.server.{ServerHttpRequest, ServerHttpResponse}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody, ResponseStatus}
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@ControllerAdvice
class ReturnCodeHandler extends ResponseBodyAdvice[Any]{

    @ExceptionHandler(Array(classOf[InternetException]))
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    def internetException(e: InternetException): Resp[Any] = {
        new Resp[Any](String.valueOf(Code.INTERNET_ERROR).toInt, e.getMessage, null)
    }


    @ExceptionHandler(Array(classOf[ParamsException]))
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    def paramsException(e: ParamsException): Resp[Any] =
        new Resp[Any](String.valueOf(Code.REQUEST_PARAM_ERROR).toInt, e.getMessage, null)

    override def supports(methodParameter: MethodParameter,
                          aClass: Class[_ <: HttpMessageConverter[_]]): Boolean = {
        classOf[MappingJackson2HttpMessageConverter].isAssignableFrom(aClass)
    }

    override def beforeBodyWrite(t: Any,
                                 methodParameter: MethodParameter,
                                 mediaType: MediaType,
                                 aClass: Class[_ <: HttpMessageConverter[_]],
                                 serverHttpRequest: ServerHttpRequest,
                                 serverHttpResponse: ServerHttpResponse): Any = {

        if (t.isInstanceOf[Resp[Any]]) {
            t
        } else {
            new Resp[Any](String.valueOf(Code.OK).toInt, "OK", t)
        }
    }
}
