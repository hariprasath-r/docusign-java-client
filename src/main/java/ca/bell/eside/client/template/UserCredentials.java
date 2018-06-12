package ca.bell.eside.client.template;

public interface UserCredentials {

	static final String UserName = "aparajita.maddi@bell.ca";
	static final String UserId = "a4687be3-83c8-45e7-a00e-846f3b237fd7";
	static final String IntegratorKey = "b7aa2156-72d1-4e6e-a19e-609cf430879c";
	static final String IntegratorKeyImplicit = "";
	static final String ClientSecret = "************5924";

	static final String RedirectURI = "https://appdemo.docusign.com/home";
	static final String BaseUrl = "https://demo.docusign.net/restapi";
	static final String OAuthBaseUrl = "account-d.docusign.com";
	
	static final String userDir = System.getProperty("user.dir");
	static final String publicKeyFilename = "/src/test/keys/public_key_1.txt";
	static final String privateKeyFilename = "/src/test/keys/private_key_1.txt";
	static final String SignTest1File = "/src/test/docs/SignTest1.pdf";
	
}
