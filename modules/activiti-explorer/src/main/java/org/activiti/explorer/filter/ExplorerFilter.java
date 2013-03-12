package org.activiti.explorer.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.explorer.Constants;

public class ExplorerFilter implements Filter {
  
  private List<String> ignoreList = new ArrayList<String>();
  private List<String> protectedList = new ArrayList<String>();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    ignoreList.add("/ui");
    ignoreList.add("/VAADIN");
    ignoreList.add("/api");
    ignoreList.add("/editor");
    ignoreList.add("/explorer");
    ignoreList.add("/libs");
    //ignoreList.add("/service");
    ignoreList.add("/diagram-viewer");
    
    protectedList.add("/service");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String path = req.getRequestURI().substring(req.getContextPath().length());
    int indexSlash = path.indexOf("/", 1);
    String firstPart = null;
    if (indexSlash > 0) {
      firstPart = path.substring(0, indexSlash);
    } else {
      firstPart = path;
    }
    
    if(protectedList.contains(firstPart)){
    	if(canAccessProtectedResources(request, response)){
    	
    		chain.doFilter(request, response); // We're good, send to default servlet.
    	
    	}else{
    		// redirect to ui for Authentication
    		if(req.getContextPath().isEmpty())
    			((HttpServletResponse)response).sendRedirect("/ui");
    		else
    			((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/ui");
    	}
    }else if (ignoreList.contains(firstPart)) {
        
      chain.doFilter(request, response); // Goes to default servlet.
    } else {
      request.getRequestDispatcher("/ui" + path).forward(request, response);
    }
  }
  
  protected boolean canAccessProtectedResources(ServletRequest request, ServletResponse response){
	  HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	  
	  if(httpServletRequest.getRemoteUser() != null) return true;
	  
	  if(httpServletRequest.getSession(false) != null){
		  if(httpServletRequest.getSession(false).getAttribute(Constants.AUTHENTICATED_USER_ID) != null)
			  return true;
	  }
	  
	  return false;
  }

  @Override
  public void destroy() {
  }

}
