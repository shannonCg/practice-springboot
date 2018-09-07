package com.syntrontech.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.syntrontech.test.model.Student;
import com.syntrontech.test.repository.StudentRepository;

@Aspect
@Component
public class StudentRepositoryAOP {

	private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryAOP.class);
	
	@Before("execution(* com.syntrontech.test.repository.StudentRepository.save(..))")
	public void before(JoinPoint joinPoint){
		Object arg = joinPoint.getArgs()[0];
		if(arg instanceof Student){
			Student student = (Student) arg;
			logger.debug("1.need to save model(arg):"+student);
		}
		logger.debug("1.before calling save method");
	}
	
	@After("execution(* com.syntrontech.test.repository.StudentRepository.save(..))")
	public void after(JoinPoint joinPoint){
		Object arg = joinPoint.getArgs()[0];
		if(arg instanceof Student){
			Student student = (Student) arg;
			logger.debug("2.need to save model(arg):"+student);
			student.setName("after aop agin one");
		}
		logger.debug("2.joinPoint getSignature().getName()="+joinPoint.getSignature().getName());
		logger.debug("2.joinPoint getSignature().getDeclaringTypeName()="+joinPoint.getSignature().getDeclaringTypeName());
		Object target = joinPoint.getTarget();
		if(target instanceof StudentRepository){
			StudentRepository repository = (StudentRepository) target;
			logger.debug("2.can get repository"+repository.getClass().getName());
		}
		
		logger.debug("2.after calling save method");
	}
	
	@AfterReturning(
			pointcut = "execution(* com.syntrontech.test.repository.StudentRepository.save(..))",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result){
		Object arg = joinPoint.getArgs()[0];
		if(arg instanceof Student){
			Student student = (Student) arg;
			logger.debug("3.need to save model(arg):"+student);
		}
		logger.debug("3.after calling save method");
		if(result instanceof Student){
			Student student = (Student) result;
			logger.debug("3.after saving model(return value):"+student);
			student.setName("after aop agin two");
		}
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.syntrontech.test.repository.StudentRepository.save(..))",
			throwing = "error")
	public void afterThrowing(JoinPoint joinPoint, Throwable error){
		logger.debug("4.throw error"+error);
	}
	
	@Around("execution(* com.syntrontech.test.repository.StudentRepository.save(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		
		logger.debug("5.before calling save method");
		Object arg = joinPoint.getArgs()[0];
		if(arg instanceof Student){
			Student student = (Student) arg;
			logger.debug("5.need to save model(arg):"+student);
			student.setName("after return aop");
			logger.debug("5.change model(arg):"+student);
			arg = student;
		}
		Object result = joinPoint.proceed();
		logger.debug("5.after calling save method");
		if(result instanceof Student){
			Student student = (Student) result;
			logger.debug("5.after saving model(return value):"+student);
		}
		
		return result;
	}
	
}
