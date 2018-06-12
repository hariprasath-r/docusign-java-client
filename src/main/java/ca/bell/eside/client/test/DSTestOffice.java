package ca.bell.eside.client.test;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.Configuration;

import ca.bell.eside.client.template.DSIF;

public class DSTestOffice implements DSIF{
	
	// Java setup and config
	String IntegratorKey = "b7aa2156-72d1-4e6e-a19e-609cf430879c";

	// generate a client secret for the integrator key you supply above, again through sandbox admin menu
	String ClientSecret = "************5924";

	// must match a redirect URI (case-sensitive) you configured on the key
	String RedirectURI = "https://appdemo.docusign.com/home";

	// use demo authentication server (remove -d for production)
	String AuthServerUrl = "https://account-d.docusign.com";

	// point to the demo (sandbox) environment. For production requests your account sub-domain 
	// will vary, you should always use the base URI that is returned from authentication to
	// ensure your integration points to the correct endpoints (in both environments)
	String RestApiUrl = "https://demo.docusign.net/restapi";
	
	@Override
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
		}	}
	
	public void getAuthenticationToken () {
		
	}

	@Override
	public void getBaseURI() {
		// TODO Auto-generated method stub
		
	}

}
