package ca.bell.eside.client.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.client.Configuration;
import com.docusign.esign.client.auth.JWTUtils;
import com.docusign.esign.client.auth.OAuth.UserInfo;
import com.docusign.esign.model.Document;
import com.docusign.esign.model.EnvelopeDefinition;
import com.docusign.esign.model.EnvelopeSummary;
import com.docusign.esign.model.Recipients;
import com.docusign.esign.model.SignHere;
import com.docusign.esign.model.Signer;
import com.docusign.esign.model.Tabs;
import com.migcomponents.migbase64.Base64;

public class JWT_Test {
	
	private static final String UserName = "aparajita.maddi@bell.ca";
	private static final String UserId = "a4687be3-83c8-45e7-a00e-846f3b237fd7";
	private static final String IntegratorKey = "b7aa2156-72d1-4e6e-a19e-609cf430879c";
	private static final String IntegratorKeyImplicit = "";
	private static final String ClientSecret = "************5924";
	private static final String RedirectURI = "https://appdemo.docusign.com/home";

	private static final String BaseUrl = "https://demo.docusign.net/restapi";
	private static final String OAuthBaseUrl = "account-d.docusign.com";
	private static final String publicKeyFilename = "/src/test/keys/public_key_1.txt";
	private static final String privateKeyFilename = "/src/test/keys/private_key_1.txt";
	
	private static final String SignTest1File = "/src/test/docs/SignTest1.pdf";
	
	public void getJWT() {
		String currentDir = System.getProperty("user.dir");
		String token = null;
		try {
			token = JWTUtils.generateJWTAssertion(currentDir + publicKeyFilename, currentDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, 3600);
		} catch (JWTCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(token);
	}
	
	public void JWTLoginTest() {
		System.out.println("\nJWTLoginTest:\n" + "===========================================");
		ApiClient apiClient = new ApiClient(BaseUrl);
		String currentDir = System.getProperty("user.dir");
		
		try {
			// IMPORTANT NOTE:
			// the first time you ask for a JWT access token, you should grant access by making the following call
			// get DocuSign OAuth authorization url:
			//String oauthLoginUrl = apiClient.getJWTUri(IntegratorKey, RedirectURI, OAuthBaseUrl);
			// open DocuSign OAuth authorization url in the browser, login and grant access
			//Desktop.getDesktop().browse(URI.create(oauthLoginUrl));
			// END OF NOTE

//			String token = JWTUtils.generateJWTAssertion(currentDir + publicKeyFilename, currentDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, 3600);
//			System.out.println(">---\n"+token);
//			System.out.println("---<");
			apiClient.configureJWTAuthorizationFlow(currentDir + publicKeyFilename, currentDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, 3600);
			System.out.println("configured");
			// now that the API client has an OAuth token, let's use it in all
			// DocuSign APIs
			UserInfo userInfo = apiClient.getUserInfo(apiClient.getAccessToken());
			System.out.println("-----------------------------");
			System.out.println(userInfo);
//			Assert.assertNotSame(null, userInfo);
//			Assert.assertNotNull(userInfo.getAccounts());
//			Assert.assertTrue(userInfo.getAccounts().size() > 0);

			// parse first account's baseUrl
			// below code required for production, no effect in demo (same
			// domain)
			apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");
			Configuration.setDefaultApiClient(apiClient);
			System.out.println(">---Updating Access Token---<");
			apiClient.updateAccessToken();
			System.out.println(apiClient.getAccessToken());
		} catch (ApiException ex) {
			System.out.println("Exception: " + ex);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	public void RequestASignatureTest() {
		System.out.println("\nRequestASignatureTest:\n" + "===========================================");
		byte[] fileBytes = null;
		try {
			// String currentDir = new java.io.File(".").getCononicalPath();

			String currentDir = System.getProperty("user.dir");

			Path path = Paths.get(currentDir + SignTest1File);
			fileBytes = Files.readAllBytes(path);
		} catch (IOException ioExcp) {
			Assert.assertEquals(null, ioExcp);
		}

		// create an envelope to be signed
		EnvelopeDefinition envDef = new EnvelopeDefinition();
		envDef.setEmailSubject("Document needs signature");
		envDef.setEmailBlurb("Hello Boys, Please Sign ASAP.");

		// add a document to the envelope
		Document doc = new Document();
		String base64Doc = Base64.encodeToString(fileBytes, false);
		doc.setDocumentBase64(base64Doc);
		doc.setName("TestFile.pdf");
		doc.setDocumentId("1");

		List<Document> docs = new ArrayList<Document>();
		docs.add(doc);
		envDef.setDocuments(docs);

		// Add a recipient to sign the document
		Signer signer = new Signer();
		signer.setEmail("hariprasath.ravichandran@ducenit.com");
//		signer.setEmail("robinson.rengaraj@bell.ca");
		signer.setName("Hariprasath");
		signer.setRecipientId("1");
		signer.setRoutingOrder("2");
		
		// Create a SignHere tab somewhere on the document for the signer to sign
		SignHere signHere = new SignHere();
		signHere.setDocumentId("1");
		signHere.setPageNumber("1");
		signHere.setRecipientId("1");
		signHere.setXPosition("100");
		signHere.setYPosition("100");
		signHere.setScaleValue("1");
		
		List<SignHere> signHereTabs = new ArrayList<SignHere>();
		signHereTabs.add(signHere);
		Tabs tabs = new Tabs();
		tabs.setSignHereTabs(signHereTabs);
		signer.setTabs(tabs);
		
		Signer signer1 = new Signer();
		signer1.setEmail("viswanathan.mahadevan@ducenit.com");
		signer1.setName("Viswanathan");
		signer1.setRecipientId("2");
		signer1.setRoutingOrder("1");
		
		SignHere signHere1 = new SignHere();
		signHere1.setDocumentId("1");
		signHere1.setPageNumber("1");
		signHere1.setRecipientId("2");
		signHere1.setXPosition("100");
		signHere1.setYPosition("150");
		signHere1.setScaleValue("2");
		
		List<SignHere> signHereTabs1 = new ArrayList<SignHere>();
		signHereTabs1.add(signHere1);
		Tabs tabs1 = new Tabs();
		tabs1.setSignHereTabs(signHereTabs1);
		signer1.setTabs(tabs1);
		
		Signer signer2 = new Signer();
		signer2.setEmail("vijayabaskar.kasi@ducenit.com");
		signer2.setName("Vijay");
		signer2.setRecipientId("3");
		signer2.setRoutingOrder("2");
		
		SignHere signHere2 = new SignHere();
		signHere2.setDocumentId("1");
		signHere2.setPageNumber("1");
		signHere2.setRecipientId("3");
		signHere2.setXPosition("100");
		signHere2.setYPosition("200");
		signHere2.setScaleValue("2");
		
		List<SignHere> signHereTabs2 = new ArrayList<SignHere>();
		signHereTabs2.add(signHere2);
		Tabs tabs2 = new Tabs();
		tabs2.setSignHereTabs(signHereTabs2);
		signer2.setTabs(tabs2);

		// Above causes issue
		envDef.setRecipients(new Recipients());
		envDef.getRecipients().setSigners(new ArrayList<Signer>());
		envDef.getRecipients().getSigners().add(signer);
		envDef.getRecipients().getSigners().add(signer1);
		envDef.getRecipients().getSigners().add(signer2);

		// send the envelope (otherwise it will be "created" in the Draft folder
		envDef.setStatus("sent");

		System.out.println(envDef);
		
		ApiClient apiClient = new ApiClient(BaseUrl);
		String currentDir = System.getProperty("user.dir");
		
		try {
			// IMPORTANT NOTE:
			// the first time you ask for a JWT access token, you should grant access by making the following call
			// get DocuSign OAuth authorization url:
			//String oauthLoginUrl = apiClient.getJWTUri(IntegratorKey, RedirectURI, OAuthBaseUrl);
			// open DocuSign OAuth authorization url in the browser, login and grant access
			//Desktop.getDesktop().browse(URI.create(oauthLoginUrl));
			// END OF NOTE
			
			apiClient.configureJWTAuthorizationFlow(currentDir + publicKeyFilename, currentDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, 3600);

			// now that the API client has an OAuth token, let's use it in all
			// DocuSign APIs
			UserInfo userInfo = apiClient.getUserInfo(apiClient.getAccessToken());
//			Assert.assertNotSame(null, userInfo);
//			Assert.assertNotNull(userInfo.getAccounts());
//			Assert.assertTrue(userInfo.getAccounts().size() > 0);

			System.out.println("UserInfo: " + userInfo);
			// parse first account's baseUrl
			// below code required for production, no effect in demo (same
			// domain)
			apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");
			System.out.println(apiClient.getBasePath());
			
			Configuration.setDefaultApiClient(apiClient);
			String accountId = userInfo.getAccounts().get(0).getAccountId();

			EnvelopesApi envelopesApi = new EnvelopesApi();

			EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);

//			Assert.assertNotNull(envelopeSummary);
//			Assert.assertNotNull(envelopeSummary.getEnvelopeId());
//			Assert.assertEquals("sent", envelopeSummary.getStatus());

			System.out.println("EnvelopeSummary: " + envelopeSummary);

		} catch (ApiException ex) {
			System.out.println("Exception: " + ex);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
		}
	}
}
