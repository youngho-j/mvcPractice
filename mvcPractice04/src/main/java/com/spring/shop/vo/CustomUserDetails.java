package com.spring.shop.vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"userName"})
@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails{
	
	private String userName;
    private String password;
    private String authority;
    private boolean enabled;
    private String memberName;
    private int money;
    private int point;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		
		auth.add(new SimpleGrantedAuthority(authority));
		
		return auth;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지?
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠기지 않았는지?
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않았는지?
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정이 활성화 되어있는지?
		return enabled;
	}
	
}
