package org.rcisoft.base.aop;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by JiChao on 2018/6/6.
 */
@Aspect
@Component
public class PageAop {

    /**
     * 环绕方法，使用pageHelper的分页功能
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* org.rcisoft.service..*.impl.*ServiceImpl.*ForPage(..))")
    public PageInfo<T> selectEntityForPage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
//            System.out.println("AOP Aronud before...");
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Integer pageNum = request.getParameter("pageNum") == null ? 1 : Integer.parseInt(request.getParameter("pageNum"));
            Integer pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
            PageHelper.startPage(pageNum, pageSize);
            PageInfo page = (PageInfo) proceedingJoinPoint.proceed();
            /** 当分页方法结束后，清空ThreadLocal，避免发生莫名其妙的分页 */
            PageHelper.clearPage();
//            System.out.println("AOP Aronud after...");
            return page;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

}
