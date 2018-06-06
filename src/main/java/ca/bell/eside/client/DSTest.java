package ca.bell.eside.client;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.Configuration;

public class DSTest {
	
	// Java setup and config
	String IntegratorKey = "63441ef9-cd1d-4c54-a400-b04b2a40c75c";

	// generate a client secret for the integrator key you supply above, again through sandbox admin menu
	String ClientSecret = "************3191";

	// must match a redirect URI (case-sensitive) you configured on the key
	String RedirectURI = "https://www.google.co.in/";

	// use demo authentication server (remove -d for production)
	String AuthServerUrl = "https://account-d.docusign.com";

	// point to the demo (sandbox) environment. For production requests your account sub-domain 
	// will vary, you should always use the base URI that is returned from authentication to
	// ensure your integration points to the correct endpoints (in both environments)
	String RestApiUrl = "https://demo.docusign.net/restapi";
	
	public void getAuthorizationCode() {
		// instantiate the api client and point to auth server
		ApiClient apiClient = new ApiClient(AuthServerUrl, "docusignAccessCode", IntegratorKey, ClientSecret);

		// set the base path for REST API requests
		apiClient.setBasePath(RestApiUrl);

		// configure the authorization flow on the api client
		apiClient.configureAuthorizationFlow(IntegratorKey, ClientSecret, RedirectURI);

		// set as default api client in your configuration
		Configuration.setDefaultApiClient(apiClient);

		try {
			// get DocuSign OAuth authorization url
			String oauthLoginUrl = apiClient.getAuthorizationUri();
			// open DocuSign OAuth login in the browser
			System.out.println(oauthLoginUrl);
			Desktop.getDesktop().browse(URI.create(oauthLoginUrl));
		}
		catch (OAuthSystemException ex)
		{
		  System.out.println(ex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getAuthenticationToken () {
		
	}

}
