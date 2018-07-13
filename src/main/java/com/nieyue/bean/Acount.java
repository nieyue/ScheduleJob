package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 账户
 * @author 聂跃
 * @date 2017年4月12日
 */
public class Acount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户id
	 */
	private Integer acountId;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 图像
	 */
	private String icon;
	/**
	 * 合伙人收益比例
	 */
	private Double scale;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * 性别,默认为0未知，为1男性，为2女性
	 */
	private Integer sex;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * email
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 身份证
	 */
	private String identityCards;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 微信号
	 */
	private String wechat;
	/**
	 * 微博
	 */
	private String microblog;
	/**
	 * 支付宝账号
	 */
	private String alipay;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 登陆时间
	 */
	private Date loginDate;
	/**
	 * 状态 0是正常，1是锁定，2是异常
	 */
	private Integer status;
	/**
	 * 推广id
	 */
	private Integer spreadId;
	/**
	 * 师傅id
	 */
	private Integer masterId;
	/**
	 * 角色id外键
	 */
	private Integer roleId;
	public Integer getAcountId() {
		return acountId;
	}
	public void setAcountId(Integer acountId) {
		this.acountId = acountId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdentityCards() {
		return identityCards;
	}
	public void setIdentityCards(String identityCards) {
		this.identityCards = identityCards;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getMicroblog() {
		return microblog;
	}
	public void setMicroblog(String microblog) {
		this.microblog = microblog;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getAlipay() {
		return alipay;
	}
	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public Acount() {
		super();
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSpreadId() {
		return spreadId;
	}
	public void setSpreadId(Integer spreadId) {
		this.spreadId = spreadId;
	}
	public Acount(Integer acountId, String nickname, String icon, Double scale, String openid, String uuid, Integer sex,
			String country, String province, String city, String realname, String phone, String email, String password,
			String identityCards, String qq, String wechat, String microblog, String alipay, Date createDate,
			Date loginDate, Integer status, Integer spreadId, Integer masterId, Integer roleId) {
		super();
		this.acountId = acountId;
		this.nickname = nickname;
		this.icon = icon;
		this.scale = scale;
		this.openid = openid;
		this.uuid = uuid;
		this.sex = sex;
		this.country = country;
		this.province = province;
		this.city = city;
		this.realname = realname;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.identityCards = identityCards;
		this.qq = qq;
		this.wechat = wechat;
		this.microblog = microblog;
		this.alipay = alipay;
		this.createDate = createDate;
		this.loginDate = loginDate;
		this.status = status;
		this.spreadId = spreadId;
		this.masterId = masterId;
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "Acount [acountId=" + acountId + ", nickname=" + nickname + ", icon=" + icon + ", scale=" + scale
				+ ", openid=" + openid + ", uuid=" + uuid + ", sex=" + sex + ", country=" + country + ", province="
				+ province + ", city=" + city + ", realname=" + realname + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", identityCards=" + identityCards + ", qq=" + qq + ", wechat=" + wechat
				+ ", microblog=" + microblog + ", alipay=" + alipay + ", createDate=" + createDate + ", loginDate="
				+ loginDate + ", status=" + status + ", spreadId=" + spreadId + ", masterId=" + masterId + ", roleId="
				+ roleId + "]";
	}
	
}
