package madtest.common.oauth2.service.impl;

import madtest.common.oauth2.service.OAuthService;
import org.springframework.stereotype.Service;

/**
 * Created by qct on 2015/7/28.
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    @Override
    public void addAuthCode(String authCode, String username) {

    }

    @Override
    public void addAccessToken(String accessToken, String username) {

    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return true;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return true;
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return null;
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return "qct";
    }

    @Override
    public long getExpireIn() {
        return 1000;
    }

    @Override
    public boolean checkClientId(String clientId) {
        return true;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return true;
    }
}
