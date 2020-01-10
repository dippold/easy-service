package org.ftd.builderforce.easyservice.web.adapters;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-08-30
 * 
 */
public class KeyValueAdapter {
    private final Long id;
    private final String key;
    private final String value;
    private final String description;
    private final boolean system;
    
    public KeyValueAdapter(Long id, String key, String value) {
        this(id, key, value, true);
    }    
    
    public KeyValueAdapter(Long id, String key, String value, boolean system) {
        this(id, key, value, "", system);
    }

    public KeyValueAdapter(Long id, String key, String value, String description, boolean system) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.system = system;
        this.description = description;
    }    
    
    public Long getId() {
        return id;
    }

        public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }    

    public boolean getSystem() {
        return system;
    }    

    public String getDescription() {
        return description;
    }
    
}
