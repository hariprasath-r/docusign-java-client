package ca.bell.eside.client.test;

import ca.bell.eside.client.template.DSIF;

public class DSTestMain {

	public static void main(String[] args) {
		
		DSIF dsTestObj = new DSTestOffice();
		dsTestObj.getAuthorizationCode();
		
//		String publicKeyFilename = "C:\\_workspace\\_eclipse_workspace\\ws_DocuSign_eSignature\\JWT\\publickey.txt";
//		String privateKeyFilename = "C:\\_workspace\\_eclipse_workspace\\ws_DocuSign_eSignature\\JWT\\privatekey.txt";
//		String basePath = "https://account-d.docusign.com";
//		String clientId = "ad6aba73-a5a1-4039-81e0-5c4a36b12312";
//		String userId = "adaa84ef-c62b-4603-8742-a987eeb7e4d6";
//		long expiresIn = 60;
//		
//		try {
//			String token = JWTUtils.generateJWTAssertion(publicKeyFilename, privateKeyFilename, basePath, clientId, userId, expiresIn);
//			System.out.println(">---Token---<");
//			System.out.println(token);
//		} catch (JWTCreationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

	}

}
