package com.db.udeploy;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.springframework.stereotype.Component;

@Component
public class DeleteFilesDirectories extends SimpleFileVisitor<Path> {
	
	public DeleteFilesDirectories() {
		super();
	}

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
        	try {
				Files.delete(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return CONTINUE;
    }
    
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    	try {
			Files.delete(dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
}