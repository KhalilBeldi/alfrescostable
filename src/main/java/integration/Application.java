package integration;

import integration.service.CmisService;
import integration.service.FilesStorageService;
import org.apache.chemistry.opencmis.client.api.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(Application.class);

	@Resource
	FilesStorageService storageService;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

//
//	@Override
//	public void run(String... args) throws Exception {
//
//		log.info("--Creating documents...");
//		Document docA = cmisService.createDocument(cmisService.getRootFolder(), "document-a.txt");
//		log.info("\tcreated document A " + docA.getId());
//		Document docB = cmisService.createDocument(cmisService.getRootFolder(), "document-b.txt");
//		log.info("\tcreated document B " + docB.getId());
//
//		log.info("--Setting aspects...");
//		cmisService.addAspect(docA, "P:cmisassoc:relationable");
//		cmisService.addAspect(docB, "P:cmisassoc:relationable");
//
//		log.info("--Setting relationships...");
//		ObjectId relationship = cmisService.createRelationship(docA, docB, "R:cmisassoc:related");
//		log.info("\tcreated relationship " + relationship);
//
//		log.info("--Relationships retrieval are not available in CMIS QL...");
//		log.info("... but you can get them using CMIS Services.");
//		ItemIterable<Relationship> relationships = cmisService.getRelationships(docA, "R:cmisassoc:related");
//		relationships.forEach((r) -> {
//			log.info("\trelationship for docA: " + r.getId());
//		});
//
//		log.info("--Alternative nodeRef field to get relationships using CMIS QL...");
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("cmisassoc:relatedRef", Arrays.asList(new String[]{docB.getProperty("alfcmis:nodeRef").getValueAsString()}));
//		cmisService.updateProperties(docA, properties);
//
//		ItemIterable<QueryResult> queryRelationships =
//				cmisService.query("SELECT cmisassoc:relatedRef FROM cmisassoc:relationable WHERE cmis:name='document-a.txt'");
//		queryRelationships.forEach((r) -> {
//			log.info("\trelationship for docA: " + r.getPropertyValueById("cmisassoc:relatedRef"));
//		});
//
//
//		log.info("Removing created documents...");
//		cmisService.remove(docA);
//		cmisService.remove(docB);
//
//		System.exit(0);
//	}
}
