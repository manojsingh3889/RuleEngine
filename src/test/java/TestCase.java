import manojsingh.BussinessTransaction;
import manojsingh.EngineBuilder;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    @Test
    public void testWithStringBasedRules(){
        //create rules
        List<String> rules = new ArrayList<>();
        rules.add("import manojsingh.BussinessTransaction;\n" +
                "\n" +
                "dialect \"java\"\n" +
                "rule \"string based rule\"\n" +
                "\n" +
                "when \n" +
                "$fact : BussinessTransaction()\n" +
                "item : BussinessTransaction($fact.getFact(\"sugar\")=='yes')\n" +
                "\n" +
                "then\n" +
                "item.addResult(\"Future\",\"Diabetic\");\n" +
                "end");

        //create fact transaction
        BussinessTransaction txn = new BussinessTransaction();
        txn.addFact("sugar","yes");

        //build knowledge session
        StatefulKnowledgeSession knowledgeSession = EngineBuilder
                .createKnowledgeBaseByString(rules)
                .newStatefulKnowledgeSession();


        /*rule evaluation*/
        knowledgeSession.insert(txn);
        knowledgeSession.fireAllRules();

        Assert.assertEquals(txn.getResult("Future"),"Diabetic");

    }

    @Test
    public void testWithFileBasedRules(){
        //create rules
        List<File> rules = new ArrayList<>();
        rules.add(new File("rulefile.drl"));

        //create fact transaction
        BussinessTransaction txn = new BussinessTransaction();
        txn.addFact("drool","brms");

        //build knowledge session
        StatefulKnowledgeSession knowledgeSession = EngineBuilder
                .createKnowledgeBaseByFile(rules)
                .newStatefulKnowledgeSession();


        /*rule evaluation*/
        knowledgeSession.insert(txn);
        knowledgeSession.fireAllRules();

        Assert.assertEquals(txn.getResult("Rule"),"Engine");

    }
}
