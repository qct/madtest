package madtest.web.controller.oauth2;

import com.google.common.collect.Maps;
import madtest.common.oauth2.Constants;
import madtest.common.oauth2.service.ClientService;
import madtest.common.oauth2.service.OAuthService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by qct on 2015/7/24.
 */
@Controller
@RequestMapping("/oauth2")
public class AuthorizeController {

    private static Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

    private static Map<String, String> cache = Maps.newHashMap();

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private ClientService clientService;

    /* *
    * 构建OAuth2授权请求 [需要client_id与redirect_uri绝对地址]
    * @param request
    * @param session
    * @param model
    * @return 返回授权码(code)有效期10分钟，客户端只能使用一次[与client_id和redirect_uri一一对应关系]
    * @throws OAuthSystemException
    * @throws IOException
    * @url  http://localhost:8080/oauth2/authorize?client_id={AppKey}&response_type=code&redirect_uri={YourSiteUrl}
    * @test http://localhost:8080/oauth2/authorize?client_id=fbed1d1b4b1449daa4bc49397cbe2350&response_type=code&redirect_uri=http://localhost:8080/client/oauth_callback
    */
    @RequestMapping("/authorize")
    public Object authorize(Model model, HttpServletRequest request, HttpSession session)
            throws OAuthProblemException, OAuthSystemException, URISyntaxException {
        try {
            //构建OAuth 授权请求
            OAuthAuthzRequest oAuthRequest = new OAuthAuthzRequest(request);

            //检查传入的客户端id是否正确
            if (!oAuthService.checkClientId(oAuthRequest.getClientId())) {
                OAuthResponse oAuthResponse = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                        .setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION)
                        .buildJSONMessage();
                logger.error("oauthRequest.getRedirectURI() : " + oAuthRequest.getRedirectURI() + " oauthResponse.getBody() : " + oAuthResponse.getBody());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf("text/html;charset=utf-8"));
                return new ResponseEntity(oAuthResponse.getBody(), headers, HttpStatus.valueOf(oAuthResponse.getResponseStatus()));
            }

            //验证appkey是否正确
            if (!validateOAuth2AppKey(oAuthRequest)) {
                OAuthResponse oAuthResponse = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setError(OAuthError.CodeResponse.ACCESS_DENIED)
                        .setErrorDescription(OAuthError.CodeResponse.UNAUTHORIZED_CLIENT)
                        .buildJSONMessage();
                logger.error("oauthRequest.getRedirectURI() : " + oAuthRequest.getRedirectURI() + " oauthResponse.getBody() : " + oAuthResponse.getBody());
                model.addAttribute("errorMsg", oAuthResponse.getBody());
                return "/oauth2/error";
            }

            //查询客户端Appkey应用的信息
            String clientName = "Just Test App";//clientService.findByClientId(oAuthRequest.getClientId());
            model.addAttribute("clientName", clientName);
            model.addAttribute("response_type", oAuthRequest.getResponseType());
            model.addAttribute("client_id", oAuthRequest.getClientId());
            model.addAttribute("redirect_uri", oAuthRequest.getRedirectURI());
            model.addAttribute("scope", oAuthRequest.getScopes());

            //验证用户是否已登录
//            if (session.getAttribute(Constants.MEMBER_SESSION_KEY) == null) {
//                //用户登录
//                if (!validateOAuth2Pwd(request)) {
//                    //登录失败跳转到登陆页
//                    return "/oauth2/login";
//                }
//            }

            //判断此次请求是否是用户授权
//            if (request.getParameter("action") == null
//                    || !request.getParameter("action").equalsIgnoreCase("authorize")) {
//                //到申请用户同意授权页
//                return "/oauth2/authorize";
//            }

            //生成授权码
            String authorizationCode = null;
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oAuthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();
                //oAuthService.addAuthCode(authorizationCode, username);
                //把授权码存入缓存
                cache.put(authorizationCode, DigestUtils.sha1Hex(oAuthRequest.getClientId() + oAuthRequest.getRedirectURI()));
            }

            //构建oauth2授权返回信息
            OAuthResponse oauthResponse = OAuthASResponse
                    .authorizationResponse(request, HttpServletResponse.SC_FOUND)
                    .setCode(authorizationCode)
                    .location(oAuthRequest.getParam(OAuth.OAUTH_REDIRECT_URI))
                    .buildQueryMessage();

            //申请令牌成功重定向到客户端页
            return "redirect:" + oauthResponse.getLocationUri();

        } catch (OAuthProblemException ex) {
            //出错处理
            OAuthResponse oauthResponse = OAuthResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .error(ex)
                    .buildJSONMessage();
            logger.error("oauthRequest.getRedirectURI() : " + ex.getRedirectUri() + " oauthResponse.getBody() : " + oauthResponse.getBody());
            model.addAttribute("errorMsg", oauthResponse.getBody());
            return "/oauth2/error";
        }

    }

    /**
     * 验证ClientID 是否正确
     *
     * @param oAuthRequest
     * @return
     */
    public boolean validateOAuth2AppKey(OAuthAuthzRequest oAuthRequest) {
        //客户端Appkey
        ArrayList arrayKeys = new ArrayList();
        arrayKeys.add("fbed1d1b4b1449daa4bc49397cbe2350");
        arrayKeys.add("a85b033590714fafb20db1d11aed5497");
        arrayKeys.add("d23e06a97e2d4887b504d2c6fdf42c0b");
        return arrayKeys.contains(oAuthRequest.getClientId());
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    private boolean validateOAuth2Pwd(HttpServletRequest request) {
        if ("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            return false;
        }
        try {
            if (name.equalsIgnoreCase("qct") && pwd.equalsIgnoreCase("123456")) {
                //登录成功
                request.getSession().setAttribute(Constants.MEMBER_SESSION_KEY, "qct");
                return true;
            }
            return false;
        } catch (Exception ex) {
            logger.error("validateOAuth2Pwd Exception: " + ex.getMessage());
            return false;
        }
    }
}
