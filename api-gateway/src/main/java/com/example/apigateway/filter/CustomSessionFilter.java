//package com.example.apigateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.session.Session;
//import org.springframework.session.SessionRepository;
//
//import javax.servlet.http.HttpSession;
//
//@Slf4j
//public class CustomSessionFilter extends ZuulFilter {
//
//    @Autowired
//    private SessionRepository sessionRepository;
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 10;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpSession httpSession = ctx.getRequest().getSession();
//        Session session = sessionRepository.findById(httpSession.getId());
//        ctx.addZuulRequestHeader("CartId", httpSession.getId());
//        log.info(httpSession.getId());
//        return null;
//    }
//}
