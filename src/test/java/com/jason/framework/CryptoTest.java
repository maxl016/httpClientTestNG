package com.jason.framework;

import com.jason.framework.third.baseUtil.DESHelper;
import com.jason.framework.third.baseUtil.RSAHelper;

public class CryptoTest {
	public static String CLIENT_PRIVATE_KEY = 
			"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJOX6DqdJDMBAhqy" + 
			"JjaGT1JSxnQcqzZg5EMbO21NgnW/UabZ7tLx36w5QANNO0+IGOKCsEldeuo9Khzc" +
			"X+yKaxckWJ1P9LPxqzXUJZdlPxWBIKS6xmhZhotXtZmTrQPhmTmorqX8zLbaYBw9" +
			"shR4UQI+G968ShGL5BWVkFDmeEQZAgMBAAECgYBX6VeN8iOpraQPOqXAPGfq5RsH" +
			"9juU/El6HzJafJyEfhyogY7bXu6XqtISJWJ6kNohsS5HxMJ920jGQA1y20/FZXS+" +
			"b/FAzG8MbfRJpiRygyoIvf9uX2guCbT2x0oM9Wv1AJwxabsWeg0t+OPtB0p/lnF/" +
			"TIWQe81LjkfSf4GhMQJBAMJqsH6vPqpH32fMzJsc+EBNP9XrE1s7h32vw/Qkm8CQ" +
			"SjOHMwgMkvR/2Xfvwtl9Agtqh+00g982SWw/heBYoKcCQQDCWEwdUgtydlmrYMuq" +
			"YOjsK76pC9U5UzCk1JfsY0Kljc/RYO/PBn5dhfguSCtM4cD45WYJXdEHo/by6gKd" +
			"Rc0/AkB1jqWiNL0jocoIreGaeW3zE8Lhyv8tT1WiPkGywuAbFCSJ+9pzp4bEqPoK" +
			"d8POVrSByqzeVb7Xna/P8Kk2+uBJAkBnmBWKk49Shk+qEMC/o0TFj1PkXFBebD/D" +
			"vSb2dLqz8PHUFC4AVt/JDn6KfSHHq5bnlbBcUcv4nKgoOCEIoIqVAkEAkTC5cwsw" +
			"LddN9T9D+65XXRZ06lbMXocuoHtPE82X4ejExe7tU7dKsW9VFHedtT8xBitAVi+I" +
			"9Jwa9ep1ta/d3Q==";
	
	public static String PEER_PUBLIC_KEY = 
			"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLYGKqvd/2ilfxg6Qt/y2sDUmg" +
			"vex0KdjcpSWf+V/4XuWOkFZZdvhDUx1SUTKtfSMa1PtoO1aZO+1NrxwgKjyYdVTn" +
			"nocgOF5ML9ZwhXi+FSvRn+Zav27mdRfznsvrYHyQYq+Md0A0q5knBnfe5pt+fcuh" +
			"VfEkd2vIpEuR8f2QKwIDAQAB";
	
	private static String hexXmlEnc  ;
	private static String hexKeyEnc ;
	private static String hexSign ;

	public static void main(String[] args) {
		encryptAndSign();
		decryptAndVeriSign();

	}
	
	private static  void encryptAndSign() {
		String data = "Hello World!测试" ;
		String key = "12345678";
		
		System.out.println("明文:"+data);
		System.out.println("密钥:"+key);
		
		RSAHelper rsaHelper = new RSAHelper();
		try {
			rsaHelper.initKey(CLIENT_PRIVATE_KEY, PEER_PUBLIC_KEY, 1024);
			
			hexXmlEnc = DESHelper.desEncryptToHex(data, key.getBytes()) ;
			System.out.println("明文加密结果:"+hexXmlEnc);
			
			byte[] bkey = rsaHelper.encryptRSA(key.getBytes(), false, "utf-8") ;
			hexKeyEnc = DESHelper.hexEncode(bkey) ;
			System.out.println("密钥加密结果:"+hexKeyEnc);
			
			byte[] sign = rsaHelper.signRSA( data.getBytes("utf-8") , false, "utf-8") ;
			hexSign = DESHelper.hexEncode(sign) ;
			System.out.println("签名结果:"+hexKeyEnc);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void decryptAndVeriSign(){
		RSAHelper rsaHelper = new RSAHelper();
		try {
			rsaHelper.initKey(CLIENT_PRIVATE_KEY, PEER_PUBLIC_KEY, 1024);
			
			byte[] bkey = rsaHelper.decryptRSA(DESHelper.hexDecode(hexKeyEnc), false, "utf-8") ;
			System.out.println("密钥解密结果:"+DESHelper.hexEncode(bkey));
			
			String xml = DESHelper.desDecryptFromHex(hexXmlEnc, bkey) ;
			System.out.println("明文解密结果:"+xml);
			
			byte[] bsign =  DESHelper.hexDecode(hexSign) ;
			boolean ret = rsaHelper.verifyRSA(xml.getBytes("utf-8"), bsign, false, "utf-8") ;
			System.out.println("验签结果:"+ret);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
