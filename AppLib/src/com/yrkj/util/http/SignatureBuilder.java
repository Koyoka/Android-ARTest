package com.yrkj.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

public class SignatureBuilder {

	public String tmsSignature(HttpUriRequest request, String secretKey) {
		String method = request.getMethod();
		String contentType = "";
		String hexDigest = "d41d8cd98f00b204e9800998ecf8427e"; // Hex digest of an empty string

		if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
			// Do nothing because the strings are already set correctly
		} else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
			contentType = "application/json";
			// If this is a POST or PUT the request should have a request body
			hexDigest = contentMD5((HttpEntityEnclosingRequestBase) request);
		} else {
			System.out.println("ERROR: Invalid content type passed to Sig Builder");
		}

		// Date in the header and date used to calculate the hash must be the same
		String dateValue = request.getFirstHeader("Date").getValue();
		String requestPath = request.getURI().getPath();
		String toDigest = new String(method + "\n" + hexDigest + "\n" + contentType + "\n" + dateValue + "\n" + requestPath);
		String shaHashed = "";
		try {
			System.out.println(toDigest);
			shaHashed = calculateRFC2104HMAC(secretKey, toDigest);
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return new String(shaHashed);
	}

	private String contentMD5(HttpEntityEnclosingRequestBase httpMethod) {
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		try {
			httpMethod.getEntity().writeTo(requestOutputStream);
		} catch (IOException e) {
			System.out.println("ERROR: IOException caught when writing Content MD5 hash");
			e.printStackTrace();
		}
		return DigestUtils.md5Hex(requestOutputStream.toByteArray()).toLowerCase();
	}

	public static String calculateRFC2104HMAC(String key, String data) throws java.security.SignatureException {
		String result = "";
		try {
			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");

			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);

			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(data.getBytes());

			// base64-encode the hmac
			result = new String(Base64.encodeBase64(rawHmac, false));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
