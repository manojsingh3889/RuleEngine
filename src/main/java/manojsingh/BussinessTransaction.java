package manojsingh;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public class BussinessTransaction implements Serializable{

    private static final long serialVersionUID = 1L;
    private Map<String, Object> facts;
    private Map<String,Object> results;

    public BussinessTransaction() {
        this.facts = new HashMap<>();
        this.results = new HashMap<>();
    }

    public void addFact(String key, String value){
        facts.put(key,value);
    }

    public Object getFact(String key) {
        return facts.get(key);
    }

    public void addResult(String key, String value){
        results.put(key,value);
    }

    public Object getResult(String key) {
        return results.get(key);
    }
}

