//package com.example.apigateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import lombok.extern.slf4j.Slf4j;
//import java.net.ConnectException;
//
//@Slf4j
//public class CustomZuulErrorFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return "error";
//    }
//
//    @Override
//    public int filterOrder() {
//        return -1;
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
//
//        Throwable throwable = ctx.getThrowable();
//
//        if (throwable instanceof ZuulException) {
//            ZuulException zuulException = (ZuulException) throwable;
//            if (throwable.getCause().getCause().getCause() instanceof ConnectException) {
//                ctx.remove("throwable");
//                ctx.setResponseBody(zuulException.getMessage());
//                ctx.getResponse()
//                        .setContentType("application/json");
//                ctx.setResponseStatusCode(503);
//            }
//        }
//        
//        if (throwable.getCause().getCause().getCause() instanceof ConnectException) {
//            ZuulException customException = new ZuulException("", 503, "Service Unavailable");
//            ctx.setThrowable(customException);
//        }
//        
//        return null;
//    }
//}
