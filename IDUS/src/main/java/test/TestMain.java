package test;

import com.idus.common.util.SecurityManager;

public class TestMain {

	public static void main(String[] args) {
		
		String sha1 = SecurityManager.hashBySha("1234");
		String sha2 = SecurityManager.hashBySha("1234");
		
		String md51 = SecurityManager.hashByMd5("1234");
		String md52 = SecurityManager.hashByMd5("1234");
				
		System.out.println(sha1);
		System.out.println(sha2);
		System.out.println(sha1.equals(sha2));
		
		System.out.println(md51);
		System.out.println(md52);
		System.out.println(md52.equals(md51));
		
	}

}
