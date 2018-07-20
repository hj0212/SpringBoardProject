package kh.spring.aspect;

import org.aspectj.lang.JoinPoint;
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
	
}
