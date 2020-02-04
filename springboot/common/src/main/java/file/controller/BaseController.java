package file.controller;

import com.huishu.file.FileServer.common.AjaxResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author yindq
 * @date 2017年11月8日
 */
public abstract class BaseController {
	public AjaxResult success(Object data) {
		return new AjaxResult().setData(data).setSuccess(true).setStatus(0);
	}

	public AjaxResult error(String message) {
		return new AjaxResult().setMessage(message).setSuccess(false).setStatus(1);
	}


	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;

	@ModelAttribute
	public void HttpModel(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response =response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
