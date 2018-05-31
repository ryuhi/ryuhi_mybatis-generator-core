package org.mybatis.generator.plugins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class InsertIgnoreColumnPlugin extends PluginAdapter {
	
	private List<String>  insertIgnoreColumns = new ArrayList<>();
	
	private String beginningDelimiter;
	
	private String endingDelimiter;

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
	
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        for (Entry<Object, Object> entry : properties.entrySet()) {
        	String key = (String)entry.getKey();
        	insertIgnoreColumns.add(key);
        }
    }
    
    @Override
    public void initialized(IntrospectedTable introspectedTable) {
    	beginningDelimiter = context.getBeginningDelimiter();
    	endingDelimiter = context.getEndingDelimiter();
    	List<IntrospectedColumn> list = introspectedTable.getAllColumns();
    	List<IntrospectedColumn> newList = Collections.synchronizedList(list);
    	for (IntrospectedColumn ic : list) {
    		if (this.properties.containsKey(ic.getActualColumnName())) {
    			newList.remove(ic);
    		}
		}
    }
}