package com.db.udeploy;

import static org.joox.JOOX.$;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

@SpringBootApplication
public class UdeployUtilityApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(UdeployUtilityApplication.class, args);
	}

	@Autowired
	PrintFilesData data;
	
	private Consumer<? super Element> mainLogic = ele -> {
		Path src = Paths.get($(ele).attr("src"));
		Path dst = Paths.get($(ele).attr("dest"));
		String content = $(ele).content().trim().replaceAll("\r", "").replaceAll("\t", "").replaceAll("\n", "");
		this.data.setDestination(dst);
		this.data.setAr(Arrays.asList(content.split(",")));
		try {
			Files.createDirectories(dst);
			Files.walkFileTree(src, this.pr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	private Consumer<? super Element> rootLogic = e -> {
		String filename = $(e).child("name").text();
		String dest = $(e).child("dest").text();
		this.data.getFilesCopied().stream().forEach(f -> {
			try {
				Files.write(Paths.get(dest + FileSystems.getDefault().getSeparator() + filename),
						(f + "\r\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	};

	@Autowired
	PrintFiles pr;

	@Override
	public void run(String... args) {

		try {
			Document document = $(Paths.get("test.xml").toFile()).document();
			$(document).find("files").forEach(mainLogic);
			$(document).find("root").forEach(rootLogic);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (args.length > 0 && args[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

}