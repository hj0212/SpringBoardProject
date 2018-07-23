package kh.spring.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kh.spring.dto.MemberDTO;
import kh.spring.util.EncryptUtils;

@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

	@Pointcut("execution(* kh.spring.impl.MemberService.insertMember(..))")
	public void insertPW() {}
	
	@Before("insertPW()")
	public void encryptPW(JoinPoint jp) {
		MemberDTO dto = (MemberDTO)jp.getArgs()[0];
		String encPW = EncryptUtils.getSha256(dto.getPw());
		dto.setPw(encPW);
		System.out.println(encPW);
	}
	
	
	@Pointcut("execution(* kh.spring.impl.MemberService.loginMember(..))")
	public void login() {}
	
	@Around("login()")
	public List<MemberDTO> encryptlogin(ProceedingJoinPoint pjp) {
		String id = pjp.getArgs()[0].toString();
		String pw = pjp.getArgs()[1].toString();
		
		pw = EncryptUtils.getSha256(pw);
		
		List<MemberDTO> result = null;
		try {
			result = (List<MemberDTO>)pjp.proceed(new Object[] {id,pw});	// 이전은 Before, 이후는 After. 메소드 다시 호출
		} catch (Throwable e) {
			e.printStackTrace();
		}	
		return result;
	}
	
}
