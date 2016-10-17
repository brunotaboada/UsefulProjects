package com.db.udeploy;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PrintFilesData {
	
	private Map<String,List<String>> files = new HashMap<>();
	private Path destination;
	private String id;
	private Map<String,List<String>> filesCopied = new HashMap<>();

	public boolean constainsFile(Path fileName) {
		boolean result = files.get(id).contains(fileName.toString());
		if(result)
			filesCopied.get(id).add(fileName.toString());
		return result;
	}

	public Path getDestination() {
		return destination;
	}

	public void setDestination(Path destination) {
		this.destination = destination;
	}
	

	public Map<String,List<String>> getFilesCopied() {
		return filesCopied;
	}

	public PrintFilesData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		filesCopied.put(id, new ArrayList<>());
		this.id = id;
	}

	public Map<String, List<String>> getFiles() {
		return files;
	}

	public void setFiles(String id, List<String> files) {
		this.files.put(id, files);
	}

}