package org.ftd.builderforce.easyservice.web.adapters;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-08-30
 * 
 */
public class IdValueAdapter {
    private final String id;
    private final String value;
    
    public IdValueAdapter(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }    
    
}
