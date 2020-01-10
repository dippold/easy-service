package org.ftd.builderforce.easyservice.web.adapters;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-08-30
 * 
 */
public class ListNameAdapter {
    private final long id;
    private final String name;
    
    public ListNameAdapter(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }    
    
}
