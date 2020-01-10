package org.ftd.builderforce.easyservice.web.adapters;

/**
 *
 * @author Fabio Tavares Dippold
 * @version 2017-09-12
 * 
 */
public class FormDefinition {
    public static final String FIELD_TYPE_INPUT = "input";
    public static final String FIELD_TYPE_INPUT_EMAIL = "inputEmail";
    public static final String FIELD_TYPE_INPUT_PASSWD = "inputPasswd";
    public static final String FIELD_TYPE_COMBOBOX = "comboBox";
    public static final String FIELD_TYPE_TEXT_AREA = "textArea";
    
    private String id;
    private String type;
    private String label;
    private String value;
    private String maxSize;
    private String rows;
    private String placeHolder;
    private Object datasource;
    private String required;
    private String title;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public String getRows() {
        return rows;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public Object getDatasource() {
        return datasource;
    }

    public String getRequired() {
        return required;
    }    

    public String getTitle() {
        return title;
    }
    
}
