package ca.bell.eside.client.api;

import java.io.IOException;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.Configuration;
import com.docusign.esign.client.auth.OAuth.UserInfo;

import ca.bell.eside.client.template.UserCredentials;

public class JWTUtility implements UserCredentials{
	
	private String accessToken = null;
	private final long expirationTime = 3600; 
	private Long expirationTimeInMillis = null;
	
	private ApiClient apiClient = null;
	
	public ApiClient getApiClient() {
		return apiClient;
	}
	
	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Long getExpirationTimeInMillis() {
		return expirationTimeInMillis;
	}

	public void setExpirationTimeInMillis(Long expirationTimeInMillis) {
		this.expirationTimeInMillis = expirationTimeInMillis;
	}

	public long getExpirationTime() {
		return expirationTime;
	}

	public ApiClient apiClientFactory () {
		System.out.println(">---Configuring API Client---<");
		apiClient = new ApiClient(BaseUrl);
		accessTokenUtil();
		return apiClient;
	}
	
	public void accessTokenUtil() {
		try {
			System.out.println(">---Creating Access Token---<");
			apiClient.configureJWTAuthorizationFlow(userDir + publicKeyFilename, userDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, expirationTime);
			setAccessToken(apiClient.getAccessToken());
			setExpirationTimeInMillis(System.currentTimeMillis()+expirationTime);
		} catch (IOException e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (ApiException e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	public void checkAndUpdateAccessToken() {
		if(System.currentTimeMillis() > expirationTimeInMillis)
			System.out.println(">---Access Token Still Alive---<");
		else {
			accessTokenUtil();
			System.out.println(">---Access Token Updated---<");
		}
	}
	
	public UserInfo retrieveOAuthUserInfo(ApiClient apiClient) {
		UserInfo userInfo = null;
		try {
			System.out.println(">---Retrieving User Information---<");
			userInfo = apiClient.getUserInfo(apiClient.getAccessToken());
			System.out.println("----------------------------------------");
			System.out.println(userInfo);
			System.out.println("----------------------------------------");
			System.out.println(">---Configuring Base URL---<");
			apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");
			Configuration.setDefaultApiClient(apiClient);
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		} catch (ApiException e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return userInfo;
	}
	
}
