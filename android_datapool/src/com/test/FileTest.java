package com.test;

import java.io.File;

import android.test.AndroidTestCase;

import com.utils.FileUtils;
import com.utils.L;
import com.utils.SDCardUtils;

public class FileTest extends AndroidTestCase {

	public void sdcardTest() {
		if (SDCardUtils.isExistSDCard()) {
			String rootPath = SDCardUtils.getSDCardPath();
			String filePath = rootPath + "androidTest" + File.separator
					+ "Test.txt";
			System.out.println("exies!");
			System.out.println(rootPath);
			System.out.println("left:" + SDCardUtils.getSDFreeSize());
			if (FileUtils.makeDirs(filePath)) {
				if (FileUtils.writeFile(filePath, "test!!")) {
					System.out.println("write successfully");
					System.out.println("ÄÚÈÝ£º" + FileUtils.readFile(filePath));
				}
			}

		}
	}
	 

}
