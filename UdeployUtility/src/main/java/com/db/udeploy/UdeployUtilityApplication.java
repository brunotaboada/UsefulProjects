package com.db.udeploy;

import static org.joox.JOOX.$;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import java.util.Arrays;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@SpringBootApplication
public class UdeployUtilityApplication implements CommandLineRunner {

	@Autowired
	PrintFiles pr;
	
	@Autowired
	DeleteFilesDirectories del;

	@Autowired
	PrintFilesData data;

	@Autowired
	ZipFileService zipFileservice;

	public static void main(String[] args) {
		SpringApplication.run(UdeployUtilityApplication.class, args);
	}

	private Consumer<? super Element> mainLogic = ele -> {
		Path src = Paths.get($(ele).attr(UConstants.SRC.name().toLowerCase()));
		Path dst = Paths.get($(ele).attr(UConstants.DEST.name().toLowerCase()));
		String id = $(ele).attr(UConstants.ID.name().toLowerCase());
		String content = $(ele).content().trim().replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
		this.data.setId(id);
		this.data.setDestination(dst);
		this.data.setFiles(id,Arrays.asList(content.split(",")));
		try {
			Files.createDirectories(dst);
			Files.walkFileTree(src, this.pr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	private Consumer<? super Element> rootLogic = e -> {
		String filename = $(e).child(UConstants.NAME.name().toLowerCase()).text();
		String dest = $(e).child(UConstants.DEST.name().toLowerCase()).text();
		String ending = $(e).child(UConstants.ENDING.name().toLowerCase()).text();
		String prefix = $(e).child(UConstants.PREFIX.name().toLowerCase()).text();
		String referTo = $(e).attr(UConstants.REFERTO.name().toLowerCase());
		this.data.getFiles().get(referTo).stream().forEach(f -> {
			try {
				if(this.data.getFilesCopied().get(referTo).contains(f)){
					Files.write(Paths.get(dest + File.separator + filename),(prefix + f + ending + "\r\n").getBytes(), CREATE, APPEND);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	};

	@Override
	public void run(String... args) {

		if (args.length == 0) {
			System.out.println("Usage: UdeployGenerator file [compressfile(Y/N)]");
			throw new ExitException();
		}

		try {
			String xmlFile = args[0];
			Document document = $(Paths.get(xmlFile).toFile()).document();
			$(document).find("files").forEach(mainLogic);
			$(document).find("root").forEach(rootLogic);
			if (args.length == 2) {
				String src = $(document).find("zipFile").attr(UConstants.SRC.name().toLowerCase());
				String dest = $(document).find("zipFile").attr(UConstants.DEST.name().toLowerCase());
				String zipFileName = $(document).find("zipFile").child(UConstants.NAME.name().toLowerCase()).text();
				zipFileservice.compress(src, dest, zipFileName);
				//Files.walkFileTree(Paths.get(src), del);
				//Runtime rt = Runtime.getRuntime();
				//Arrays.asList("----settings","");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (args.length > 0 && args[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

}