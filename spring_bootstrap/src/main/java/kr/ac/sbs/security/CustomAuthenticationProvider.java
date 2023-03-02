package kr.ac.sbs.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.dto.MemberVO;

import kr.ac.sbs.exception.InvalidPasswordException;
import kr.ac.sbs.exception.NotFoundIdException;
import kr.ac.sbs.service.MemberService;

public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String login_id = (String) auth.getPrincipal(); // 로그인 시도한 ID를 가져온다
		String login_pwd = (String) auth.getCredentials(); // 로그인 시도한 Password 를 가져온다.
		
		try {
			memberService.login(login_id, login_pwd);
			
			MemberVO member = memberService.getMember(login_id);
			
			UserDetails authUser = new User(member);
			boolean invalidCheck = authUser.isAccountNonExpired() 
					&& authUser.isAccountNonLocked()
					&& authUser.isCredentialsNonExpired() 
					&& authUser.isEnabled();
			if (invalidCheck) {
				// 스프링 시큐리티 내부 클래스로 인증 토큰 생성한다.
				UsernamePasswordAuthenticationToken result 
							= new UsernamePasswordAuthenticationToken(
						authUser.getUsername(), authUser.getPassword(), 
						authUser.getAuthorities());
				// 전달할 내용을 설정한 후
				result.setDetails(authUser);
				// 리턴한다. successHandler로 전송된다.
				return result;
			}else {
				throw new BadCredentialsException("유효하지 않는 계정입니다.");
			}
			
		} catch (NotFoundIdException | InvalidPasswordException e) {
			throw new BadCredentialsException(e.getMessage());		
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationServiceException("서버 장애로 서비스가 불가합니다.");
		}
		
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}




