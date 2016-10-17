package com.db.udeploy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PrintFilesData {
	private List<String> ar;
	private Path destination;
	private List<String> filesCopied = new ArrayList<String>();

	public PrintFilesData() {
	}

	public boolean constainsFile(Path fileName) {
		boolean result = ar.contains(fileName.toString());
		if(result)
			filesCopied.add(fileName.toString());	
		return result;
	}

}