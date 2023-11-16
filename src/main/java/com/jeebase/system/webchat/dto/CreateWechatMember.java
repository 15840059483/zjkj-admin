package com.jeebase.system.webchat.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * <p>
 * 微信注册会员表
 * </p>
 *
 * @author jeebase
 * @since 2019-03-08
 */
@ApiModel(value="WechatMember对象", description="微信注册会员表")
public class CreateWechatMember implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "支付宝id")
	private String aliUserId;

	@ApiModelProperty(value = "支付宝原id")
	private String alipayUserId;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "系统用户表用户ID")
    private Long userId;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "小程序用户openid")
    private String wechatOpenId;

    @ApiModelProperty(value = "公众号用户openid", required = true)
    private String wechatPlatformOpenId;

    @ApiModelProperty(value = "微信用户union id")
    private String wechatUnionId;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "姓名")
    @Size(min=2,max=16,message="姓名长度不正确")
    private String realname;

    @ApiModelProperty(value = "固定电话")
    private String telephone;

    @ApiModelProperty(value = "用户类型1、普通用户")
    private Long userType;

    @ApiModelProperty(value = "头像url")
    private String avatarUrl;

    @ApiModelProperty(value = "会员积分")
    private Long memberPoints;

    @ApiModelProperty(value = "国家")
    private String contry;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "联系地址")
    private String contactAddress;

    @ApiModelProperty(value = "1 : 男，2 : 女， 3: 未知")
    private Long gender;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "是否记住密码")
    private Boolean remember;

    @ApiModelProperty(value = "注册日期")
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "注册ip")
    private String registerIp;

    @ApiModelProperty(value = "最后登录日期")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "最后登录ip")
    private String lastLoginIp;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long creator;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后修改人")
    private Long operator;

    @ApiModelProperty(value = "是否删除")
    private String delFlag;

    private Long organizationId;

    private Long roleId;
    
    @ApiModelProperty(value = "身份证", required = true)
    @NotBlank(message="身份证不能为空")
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
    "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$", message="身份证格式不正确")
    private String userIdCard;

    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank(message="手机号码不能为空")
    @Size(min=11,max=11,message="手机号码长度不正确")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String userPassword;

    /**
     * 短信验证码
     */
    @NotBlank(message = "短信验证码不能为空")
    @Length(max = 6, min = 6, message = "请输入6位数短信验证码。")
    @ApiModelProperty(value = "短信验证码", required = true, dataType = "String", notes = "输入6位数短信验证码。")
    private String smsCode;
    
    private String cardNo;

	@Override
	public String toString() {
		return "CreateWechatMember{" +
				"aliUserId='" + aliUserId + '\'' +
				", alipayUserId='" + alipayUserId + '\'' +
				", id=" + id +
				", userId=" + userId +
				", parentId=" + parentId +
				", wechatOpenId='" + wechatOpenId + '\'' +
				", wechatPlatformOpenId='" + wechatPlatformOpenId + '\'' +
				", wechatUnionId='" + wechatUnionId + '\'' +
				", nickname='" + nickname + '\'' +
				", realname='" + realname + '\'' +
				", telephone='" + telephone + '\'' +
				", userType=" + userType +
				", avatarUrl='" + avatarUrl + '\'' +
				", memberPoints=" + memberPoints +
				", contry='" + contry + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", area='" + area + '\'' +
				", contactAddress='" + contactAddress + '\'' +
				", gender=" + gender +
				", birthday='" + birthday + '\'' +
				", remember=" + remember +
				", registerTime=" + registerTime +
				", registerIp='" + registerIp + '\'' +
				", lastLoginTime=" + lastLoginTime +
				", lastLoginIp='" + lastLoginIp + '\'' +
				", comments='" + comments + '\'' +
				", createTime=" + createTime +
				", creator=" + creator +
				", updateTime=" + updateTime +
				", operator=" + operator +
				", delFlag='" + delFlag + '\'' +
				", organizationId=" + organizationId +
				", roleId=" + roleId +
				", userIdCard='" + userIdCard + '\'' +
				", mobile='" + mobile + '\'' +
				", userPassword='" + userPassword + '\'' +
				", smsCode='" + smsCode + '\'' +
				", cardNo='" + cardNo + '\'' +
				'}';
	}

	public String getAliUserId() {
		return aliUserId;
	}

	public void setAliUserId(String aliUserId) {
		this.aliUserId = aliUserId;
	}

	public String getAlipayUserId() {
		return alipayUserId;
	}

	public void setAlipayUserId(String alipayUserId) {
		this.alipayUserId = alipayUserId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getWechatOpenId() {
		return wechatOpenId;
	}

	public void setWechatOpenId(String wechatOpenId) {
		this.wechatOpenId = wechatOpenId;
	}

	public String getWechatPlatformOpenId() {
		return wechatPlatformOpenId;
	}

	public void setWechatPlatformOpenId(String wechatPlatformOpenId) {
		this.wechatPlatformOpenId = wechatPlatformOpenId;
	}

	public String getWechatUnionId() {
		return wechatUnionId;
	}

	public void setWechatUnionId(String wechatUnionId) {
		this.wechatUnionId = wechatUnionId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Long getMemberPoints() {
		return memberPoints;
	}

	public void setMemberPoints(Long memberPoints) {
		this.memberPoints = memberPoints;
	}

	public String getContry() {
		return contry;
	}

	public void setContry(String contry) {
		this.contry = contry;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Boolean getRemember() {
		return remember;
	}

	public void setRemember(Boolean remember) {
		this.remember = remember;
	}

	public LocalDateTime getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
