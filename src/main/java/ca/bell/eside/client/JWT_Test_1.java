package ca.bell.eside.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

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

public class JWT_Test_1 {
	
	private static final String UserName = "hariprasath24@outlook.com";
	private static final String UserId = "a4687be3-83c8-45e7-a00e-846f3b237fd7";
	private static final String IntegratorKey = "b7aa2156-72d1-4e6e-a19e-609cf430879c";
	private static final String IntegratorKeyImplicit = "";
	private static final String ClientSecret = "************5924";
	private static final String RedirectURI = "https://appdemo.docusign.com/home";

	private static final String BaseUrl = "https://demo.docusign.net/restapi";
	private static final String OAuthBaseUrl = "account-d.docusign.com";
	private static final String publicKeyFilename = "/src/test/keys/public_key_2.txt";
	private static final String privateKeyFilename = "/src/test/keys/private_key_2.txt";
	
	private static final String SignTest1File = "/src/test/docs/SignTest1.pdf";
	
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

			String token = JWTUtils.generateJWTAssertion(currentDir + publicKeyFilename, currentDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, 3600);
			System.out.println(">---\n"+token);
			System.out.println("---<");
			apiClient.configureJWTAuthorizationFlow(currentDir + publicKeyFilename, currentDir + privateKeyFilename, OAuthBaseUrl, IntegratorKey, UserId, 3600);
			System.out.println("configured");
			// now that the API client has an OAuth token, let's use it in all
			// DocuSign APIs
			System.out.println("Access Token :: "+apiClient.getAccessToken());
			UserInfo userInfo = apiClient.getUserInfo(apiClient.getAccessToken());
			Assert.assertNotSame(null, userInfo);
			Assert.assertNotNull(userInfo.getAccounts());
			Assert.assertTrue(userInfo.getAccounts().size() > 0);

			System.out.println("UserInfo: " + userInfo);
			// parse first account's baseUrl
			// below code required for production, no effect in demo (same
			// domain)
			apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");
			Configuration.setDefaultApiClient(apiClient);
		} catch (ApiException ex) {
			System.out.println("Exception: " + ex);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
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
		envDef.setEmailSubject("Please Sign my Java SDK Envelope");
		envDef.setEmailBlurb("Hello, Please sign my Java SDK Envelope.");

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
		signer.setEmail(UserName);
		signer.setName("Pat Developer");
		signer.setRecipientId("1");

		// Create a SignHere tab somewhere on the document for the signer to
		// sign
		SignHere signHere = new SignHere();
		signHere.setDocumentId("1");
		signHere.setPageNumber("1");
		signHere.setRecipientId("1");
		signHere.setXPosition("100");
		signHere.setYPosition("100");
		signHere.setScaleValue("0.5");

		List<SignHere> signHereTabs = new ArrayList<SignHere>();
		signHereTabs.add(signHere);
		Tabs tabs = new Tabs();
		tabs.setSignHereTabs(signHereTabs);
		signer.setTabs(tabs);

		// Above causes issue
		envDef.setRecipients(new Recipients());
		envDef.getRecipients().setSigners(new ArrayList<Signer>());
		envDef.getRecipients().getSigners().add(signer);

		// send the envelope (otherwise it will be "created" in the Draft folder
		envDef.setStatus("sent");

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
			Assert.assertNotSame(null, userInfo);
			Assert.assertNotNull(userInfo.getAccounts());
			Assert.assertTrue(userInfo.getAccounts().size() > 0);

			System.out.println("UserInfo: " + userInfo);
			// parse first account's baseUrl
			// below code required for production, no effect in demo (same
			// domain)
			apiClient.setBasePath(userInfo.getAccounts().get(0).getBaseUri() + "/restapi");
			Configuration.setDefaultApiClient(apiClient);
			String accountId = userInfo.getAccounts().get(0).getAccountId();

			EnvelopesApi envelopesApi = new EnvelopesApi();

			EnvelopeSummary envelopeSummary = envelopesApi.createEnvelope(accountId, envDef);

			Assert.assertNotNull(envelopeSummary);
			Assert.assertNotNull(envelopeSummary.getEnvelopeId());
			Assert.assertEquals("sent", envelopeSummary.getStatus());

			System.out.println("EnvelopeSummary: " + envelopeSummary);

		} catch (ApiException ex) {
			System.out.println("Exception: " + ex);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getLocalizedMessage());
		}
	}
}
