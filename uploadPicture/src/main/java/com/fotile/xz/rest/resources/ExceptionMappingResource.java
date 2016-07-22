package com.fotile.xz.rest.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fotile.xz.exception.BaseException;
import com.fotile.xz.exception.ExceptionResponse;

/**
 * 必须添加该注解使得在程序的任何地方发生运行时异常时，自动的进行异常统一处理 同时该类要能够被Jersey扫描到
 */
@Provider
public class ExceptionMappingResource implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		ResponseBuilder responseBuilder = null;

		// 用户自定义的运行时异常处理
		if (exception instanceof BaseException) {

			// 获取用户抛出的异常信息
			String code = exception.getMessage();

			// 根据异常key获取对应的中文异常信息
			String message = exception.getMessage();
			Throwable cause = exception.getCause();
			if (cause != null) {
				String realReason = cause.getMessage();
				message += " 可能的原因是：" + realReason;
			}

			// 自定义异常返回实体bean类
			ExceptionResponse error = new ExceptionResponse();
			error.setCode(code);
			error.setMessage(message);
			error.setStatus("error");

			responseBuilder = Response.ok(error).status(
					((BaseException) exception).getCode());
		}
		// 其他异常
		else {
			ExceptionResponse error = new ExceptionResponse();
			error.setCode("E000000");
			error.setMessage(exception.getMessage());
			error.setStatus("error ");
			responseBuilder = Response.ok(error).status(
					Response.Status.INTERNAL_SERVER_ERROR);
		}
		return responseBuilder.build();
	}
}