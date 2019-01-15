package com.shm.test;

import org.apache.commons.lang3.StringUtils;

/**"
 * StringUtils是Apache提供的一个工具类
 * 	在Commons-lang3的jar包中
 * 	该类就是Apache提供的一个对String操作的工具类
 * @author SHM
 *
 */
public class StringUtilsTest {

	public static void main(String[] args) {
		String str1 = null;
		String str2 = "";
		String str3 = "    ";
		System.out.println(StringUtils.isEmpty(str1));
		System.out.println(StringUtils.isEmpty(str2));
		System.out.println(StringUtils.isEmpty(str3));
		System.out.println("===================");
		System.out.println(StringUtils.isBlank(str1));
		System.out.println(StringUtils.isBlank(str2));
		System.out.println(StringUtils.isBlank(str3));


	}

}
