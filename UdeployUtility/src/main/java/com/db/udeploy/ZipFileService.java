package com.db.udeploy;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

@Service
public class ZipFileService {

	@Autowired
	FileService fs;

	public void compress(String src, String dest, String zipFileName) {
		try {
			ZipFile zipFile = new ZipFile(fs.getFilePath(dest, zipFileName).toFile());
			ZipParameters params = new ZipParameters();
			params.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			params.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			Path originalSource = fs.getFilePath(src);
			zipFile.addFolder(originalSource.toFile(), params);
		} catch (ZipException e) {
			e.printStackTrace();
		} 
	}
}
