package com.lind.bookshop.entity;

import com.lind.bookshop.entity.base.EntityBase;
import com.lind.bookshop.exception.Exceptions;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends EntityBase implements UserDetails {
  @Length(max = 20, min = 5, message = "用户名长度最大为50个字")
  private String username;
  @Length(max = 20, message = "真实姓名长度最大为50个字")
  private String realname;
  private String telephone;
  private Integer age;
  @Email(message = "email格式不正确")
  private String email;
  private String password;
  private UserShipper userShipper;
  private List<OrderInfo> orderInfoList;

  @Builder(toBuilder = true)
  public UserInfo(Integer isDelete, Long id, Timestamp createdOn, Timestamp updatedOn, @Length(max = 20, min = 5, message = "用户名长度最大为50个字") String username, @Length(max = 20, message = "真实姓名长度最大为50个字") String realname, String telephone, Integer age, @Email(message = "email格式不正确") String email, String password, UserShipper userShipper, List<OrderInfo> orderInfoList) {
    super(isDelete, id, createdOn, updatedOn);
    this.username = username;
    this.realname = realname;
    this.telephone = telephone;
    this.age = age;
    this.email = email;
    this.password = password;
    this.userShipper = userShipper;
    this.orderInfoList = orderInfoList;
  }

  public void validateName() {
    if (username == null || username.length() > 20) {
      throw Exceptions.badRequestParams("用户名长度最大为20个字");
    }
  }


  /**
   * Returns the authorities granted to the user. Cannot return <code>null</code>.
   *
   * @return the authorities, sorted by natural key (never <code>null</code>)
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
  }

  /**
   * Indicates whether the user's account has expired. An expired account cannot be
   * authenticated.
   *
   * @return <code>true</code> if the user's account is valid (ie non-expired),
   * <code>false</code> if no longer valid (ie expired)
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is locked or unlocked. A locked user cannot be
   * authenticated.
   *
   * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Indicates whether the user's credentials (password) has expired. Expired
   * credentials prevent authentication.
   *
   * @return <code>true</code> if the user's credentials are valid (ie non-expired),
   * <code>false</code> if no longer valid (ie expired)
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Indicates whether the user is enabled or disabled. A disabled user cannot be
   * authenticated.
   *
   * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}
