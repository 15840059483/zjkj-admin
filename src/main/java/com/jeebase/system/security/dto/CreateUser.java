
package com.jeebase.system.security.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author fyy
 * @since  
 */
public class CreateUser implements Serializable
{

    /**
      * @Fields serialVersionUID : 
      */
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotBlank(message="账号不能为空")
    @Pattern(regexp = "^[a-z0-9_-]{3,16}$", message="账号格式不正确")
    private String userAccount;
    
    /**
     * 身份证
     */
    @ApiModelProperty(value = "身份证")
    @Pattern(regexp = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)", message="身份证格式不正确")
    private String userIdCard;
    
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @Size(min=2,max=16,message="昵称长度不正确")
    private String userNickName;
    
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Size(min=2,max=16,message="姓名长度不正确")
    private String userName;
    
    /**
     * 1 : 男，0 : 女
     */
    @ApiModelProperty(value = "1 : 男，0 : 女， 2: 未知")
    private String userSex;
    
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotBlank
    @Email
    private String userEmail;
    
    /**
     * 电话
     */
    @ApiModelProperty(value = "手机号码")
    @NotBlank(message="手机号码不能为空")
    @Size(min=11,max=11,message="手机号码长度不正确")
    private String userMobile;
    
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,18}$", message="密码格式不正确")
    private String userPassword;
    
    /**
     * '0'禁用，'1' 启用, '2' 密码过期或初次未修改
     */
    @ApiModelProperty(value = "用户状态 '0'禁用,'1' 启用, '2' 密码过期或初次未修改")
    private String userStatus;

    /**
     * 头像图片地址
     */
    @ApiModelProperty(value = "头像图片地址")
    @Pattern(regexp = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$", message="头像图片地址格式不正确")
    private String headImgUrl;
    
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;
    
    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;
    
    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;
    
    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;
    
    /**
     * 地区数组
     */
    @ApiModelProperty(value = "用户地区ID数组")
    private List<String> areas;
    
    /**
     * 街道详细地址
     */
    @ApiModelProperty(value = "街道详细地址")
    private String street;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Size(min=0,max=255,message="备注信息长度不正确")
    private String description;

    private Long roleId;

    @ApiModelProperty(value = "组织机构id")
    private Long organizationId;

    /**
     * 角色数组
     */
    @ApiModelProperty(value = "用户角色ID数组")
    private List<Long> roleIds;

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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

	public List<String> getAreas() {
		return areas;
	}

	public void setAreas(List<String> areas) {
		this.areas = areas;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CreateUser(Long id,
			@NotBlank(message = "账号不能为空") @Pattern(regexp = "^[a-z0-9_-]{3,16}$", message = "账号格式不正确") String userAccount,
			@NotBlank(message = "账号不能为空") @Pattern(regexp = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$", message = "身份证格式不正确") String userIdCard,
			@Size(min = 2, max = 16, message = "昵称长度不正确") String userNickName,
			@Size(min = 2, max = 16, message = "姓名长度不正确") String userName, String userSex,
			@NotBlank @Email String userEmail,
			@NotBlank(message = "手机号码不能为空") @Size(min = 11, max = 11, message = "手机号码长度不正确") String userMobile,
			@Pattern(regexp = "^[a-zA-Z0-9]{6,18}$", message = "密码格式不正确") String userPassword, String userStatus,
			@Pattern(regexp = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$", message = "头像图片地址格式不正确") String headImgUrl,
			String province, String city, String area, List<String> areas, String street,
			@Size(min = 0, max = 255, message = "备注信息长度不正确") String description, Long roleId, Long organizationId,
			List<Long> roleIds) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.userIdCard = userIdCard;
		this.userNickName = userNickName;
		this.userName = userName;
		this.userSex = userSex;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
		this.userPassword = userPassword;
		this.userStatus = userStatus;
		this.headImgUrl = headImgUrl;
		this.province = province;
		this.city = city;
		this.area = area;
		this.areas = areas;
		this.street = street;
		this.description = description;
		this.roleId = roleId;
		this.organizationId = organizationId;
		this.roleIds = roleIds;
	}

	public CreateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CreateUser [id=" + id + ", userAccount=" + userAccount + ", userIdCard=" + userIdCard
				+ ", userNickName=" + userNickName + ", userName=" + userName + ", userSex=" + userSex + ", userEmail="
				+ userEmail + ", userMobile=" + userMobile + ", userPassword=" + userPassword + ", userStatus="
				+ userStatus + ", headImgUrl=" + headImgUrl + ", province=" + province + ", city=" + city + ", area="
				+ area + ", areas=" + areas + ", street=" + street + ", description=" + description + ", roleId="
				+ roleId + ", organizationId=" + organizationId + ", roleIds=" + roleIds + "]";
	}

   
}
