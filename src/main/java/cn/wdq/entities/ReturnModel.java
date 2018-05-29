package cn.wdq.entities;

import java.util.HashMap;

public class ReturnModel {
	/**
	 * 接口业务是否处理成功
	 */
	boolean success = false;
	
	/**
	 * 是否需要重定向到登录界面
	 */
	boolean redirectLogin = false;
	
	/**
	 * 返回状态
	 */
	int status=0;
	/**
	 * 返回消息
	 */
	String message;
	/**
	 * 单据号
	 */
	String billno;
	/**
	 * 单据主键
	 */
	String pk_bill;
	/**
	 * 单据类型
	 */
	String vbilltype;
	/**
	 * 返回数据
	 */
	Object data;
	
	HashMap<String, Object> props = new HashMap<String, Object>();

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isRedirectLogin() {
		return redirectLogin;
	}

	public void setRedirectLogin(boolean redirectLogin) {
		this.redirectLogin = redirectLogin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getPk_bill() {
		return pk_bill;
	}

	public void setPk_bill(String pk_bill) {
		this.pk_bill = pk_bill;
	}

	public String getVbilltype() {
		return vbilltype;
	}

	public void setVbilltype(String vbilltype) {
		this.vbilltype = vbilltype;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HashMap<String, Object> getProps() {
		return props;
	}

	public void setProps(HashMap<String, Object> props) {
		this.props = props;
	}
	
}
