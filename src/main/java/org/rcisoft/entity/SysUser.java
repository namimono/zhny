package org.rcisoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by JiChao on 2018/5/29.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_user")
public class SysUser implements UserDetails {

	/**
	 * 主键
	 */
	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 用户名
	 */
	@Column(name = "username" )
	private String username;

	/**
	 * 密码
	 */
	@Column(name = "password" )
	private String password;

	/**
	 * 姓名
	 */
	@Column(name = "real_name" )
	private String realName;

	/**
	 * 用户类型，1：超管，2：管理员，3：业主
	 */
	@Column(name = "type" )
	private Integer type;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time" )
	private Date createTime;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time" )
	private Date updateTime;

	/**
	 * 手机号
	 */
	@Column(name = "mobile" )
	private Integer mobile;

	/**
	 * 邮箱
	 */
	@Column(name = "email" )
	private String email;

	@Transient
	private List<SysRole> roles = Lists.newArrayList();

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> auths = new ArrayList<>();
//        List<SysRole> roles = this.getRoles();
//        for (SysRole role : roles) {
//            auths.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return auths;
		return null;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


}
