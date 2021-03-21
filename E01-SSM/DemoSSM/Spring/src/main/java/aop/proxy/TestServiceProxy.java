package aop.proxy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 代理增强类=
 */
@Component(value = "testServiceProxy") //表明将本类交给Spring管理，相当于在xml文件中配置一个bean：<bean id="HelloIOC" class="kyle.demo.HelloIOC"/>
@Aspect //表明这是一个代理对象，要去增强他人
public class TestServiceProxy {
    //相同切入点抽取
    @Pointcut(value = "execution(* aop.service.TestService.testMethod(..))")
    public void pointdemo() {
        System.out.println("相同切入点的抽取");
    }

    //前置通知：在方法执行执行
    @Before(value = "execution(* aop.service.TestService.testMethod(..))")
//    @Before(value = "pointdemo")
    public void before() {
        System.out.println("Before...");
    }

    //最终通知：在方法执行之后执行。不管有没有异常都会执行。
    @After(value = "execution(* aop.service.TestService.testMethod(..))")
    public void after() {
        System.out.println("after...");
    }
    //后置通知：在方法返回结果之后执行。如果有异常就不会执行。
    @AfterReturning(value = "execution(* aop.service.TestService.testMethod(..))")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    //环绕通知：方法之前、之后都执行
    @Around(value = "execution(* aop.service.TestService.testMethod(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前.........");
        // 被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后.........");
    }

    //异常通知（只有发生异常时才执行）
    @AfterThrowing(value = "execution(* aop.service.TestService.testMethod(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }
}
