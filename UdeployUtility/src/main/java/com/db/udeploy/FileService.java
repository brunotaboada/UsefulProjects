package com.db.udeploy;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	@Autowired
	private PrintFilesData data;
	

	public void copyFile(Path from){
		try {
			Path dest = Paths.get(data.getDestination()+FileSystems.getDefault().getSeparator()+from.getFileName());
			Files.copy(from, dest, StandardCopyOption.REPLACE_EXISTING);
			System.out.printf("File %s has been copied succesffuly to %s \r\n",from.toAbsolutePath(),dest.toAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean constainsFile(Path fileName) {
		return data.constainsFile(fileName);
	}
	
}