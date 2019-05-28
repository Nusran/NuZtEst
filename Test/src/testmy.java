import java.io.File;
import java.util.ArrayList;

public class testmy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "abcd";
		char[] chr ;
		StringBuilder builder = new StringBuilder();
		System.out.println();
	}

}

 class CallWebservices {

	public static void main(String[] args) {
		try {
			
			String user = "";
			String pwd = "";
			String ws_url = "";
			String cono = "";
			String naardn = "";
			
			String userPassword = user + ":" + pwd;//set basic authentication user and password
			String encoding = java.util.Base64.getEncoder().encodeToString(userPassword.getBytes());
			System.out.println("[:] user --> " + userPassword);
		//	System.out.println("[:] cononaardn "+i_CONO+","+i_NAARDN);
			String responseString = "";
			String outputString = "";
			String wsURL = ws_url;
			
			System.out.println("[:] webservice :"+userPassword+","+ws_url);
			// create a connection
			java.net.URL url = new java.net.URL(wsURL);
			java.net.URLConnection connection = url.openConnection();
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connection;
			java.io.ByteArrayOutputStream bout = new java.io.ByteArrayOutputStream();
			
			// Set envelop
			String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cred=\"http://lawson.com/ws/credentials\" xmlns:pre=\"http://your.company.net/VendorPortalService/PreAllocationSQL\"> "
					+ "<soapenv:Header> "
					+   "<cred:lws> "
					+    "<!--Optional:--> "
					+    "<cred:company>?</cred:company> "
					+    "<!--Optional:--> "
					+    "<cred:division>?</cred:division> "
					+  "</cred:lws> "
					+ "</soapenv:Header> "
					+ "<soapenv:Body> "
					+   "<pre:PreAllocationSQL> "
					+     "<pre:CONO> "+cono+" </pre:CONO> "
					+     "<pre:NAARDN> " +naardn+"</pre:NAARDN> "
					+   "</pre:PreAllocationSQL> "
					+  "</soapenv:Body> "
					+ "</soapenv:Envelope>";


			byte[] buffer = new byte[xmlInput.length()];
			buffer = xmlInput.getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			String SOAPAction = "";
			httpConn.setRequestProperty("Authorization", "Basic " + encoding);
			// Set the appropriate HTTP parameters.
			httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpConn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", SOAPAction);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			java.io.OutputStream out = httpConn.getOutputStream();
			// Write the content of the request to the outputstream of the HTTP
		 
			out.write(b);
			out.close();

			java.io.InputStreamReader isr = null;

			if (httpConn.getResponseCode() == 200) {
				isr = new java.io.InputStreamReader(httpConn.getInputStream());
				System.out.println(" [:] WS responsce" + httpConn.getResponseCode());

			} else {
				isr = new java.io.InputStreamReader(httpConn.getErrorStream());
				System.out.println(" [:] WS responsce" + httpConn.getResponseCode());
			}

			java.io.BufferedReader in = new java.io.BufferedReader(isr);

			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}

			String output = outputString;
			javax.xml.parsers.DocumentBuilder builder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.xml.sax.InputSource src = new org.xml.sax.InputSource();
			src.setCharacterStream(new java.io.StringReader(output));

			org.w3c.dom.Document doc = builder.parse(src);
			
			//Get number of result has be returned from web service
			int countResultSet = doc.getElementsByTagName("ResultItem").getLength();
			System.out.println("[:] count of result set : " + countResultSet);
			
		}catch(Exception ex) {
			
		}

	}

}
