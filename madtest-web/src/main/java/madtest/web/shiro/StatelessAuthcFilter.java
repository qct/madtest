package madtest.web.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qct on 2016/4/26.
 *
 * @author qct
 */
public class StatelessAuthcFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter("digest");
        //2、客户端传入的用户身份
        String username = request.getParameter("username");
        //3、客户端请求的参数列表
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove("digest");
        //4、生成无状态Token
        StatelessToken token = new StatelessToken(username, params, clientDigest);

        //5、委托给Realm进行登录
        try {
            getSubject(request, response).login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            onLoginFail(response); //6、登录失败
            return false;
        }

        return true;
    }

    /**
     * 登录失败时默认返回401状态码
     * @param response
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }
}
