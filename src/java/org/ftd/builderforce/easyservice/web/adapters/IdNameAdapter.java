package org.ftd.builderforce.easyservice.web.adapters;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-08-30
 * 
 */
public class IdNameAdapter {
    private final String id;
    private final String name;
    private final boolean system;
    
    public IdNameAdapter(String id, String name) {
        this(id, name, true);
    }    
    
    public IdNameAdapter(String id, String name, boolean system) {
        this.id = id;
        this.name = name;
        this.system = system;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }    

    public boolean isSystem() {
        return system;
    }
    
}
