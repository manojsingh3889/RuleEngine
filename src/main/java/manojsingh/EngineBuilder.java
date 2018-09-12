package manojsingh;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.*;
import org.drools.io.ResourceFactory;

import java.io.File;
import java.util.List;

public class EngineBuilder {

	public static KnowledgeBase createKnowledgeBaseByString(List<String> rules){
		return createKnowledgeBase(rules);
	}

	public static KnowledgeBase createKnowledgeBaseByFile(List<File> rules){
		return createKnowledgeBase(rules);
	}

	private static KnowledgeBase createKnowledgeBase(List<? extends Object> rules){
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		//add rule to builder
		rules.forEach((R)->setRule(builder, R));
		validateBuilder(builder);
		return buildKnowledgeBase(builder);
	}


	private static KnowledgeBase buildKnowledgeBase(KnowledgeBuilder builder) {
		/*create base using builder*/
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(builder.getKnowledgePackages());
		return kbase;
	}

	private static void validateBuilder(KnowledgeBuilder builder) {
		if (builder.getErrors().isEmpty())
			return;

		/*validate builder object for any error*/
		builder.getErrors().forEach((E)->{System.err.println(E);});
		throw new IllegalArgumentException("Could not parse drool file. Invalid format");
	}

	private static void setRule(KnowledgeBuilder builder,Object file) {
		if (file instanceof File)
			setRule(builder,(File) file);
		else
			setRule(builder,(String)file);
	}
	private static void setRule(KnowledgeBuilder builder,File file) {
		builder.add(ResourceFactory.newFileResource(file), ResourceType.DRL);
	}

	private static void setRule(KnowledgeBuilder builder, String ruleStr){
		builder.add(ResourceFactory.newByteArrayResource(ruleStr.getBytes()), ResourceType.DRL);
	}
}
