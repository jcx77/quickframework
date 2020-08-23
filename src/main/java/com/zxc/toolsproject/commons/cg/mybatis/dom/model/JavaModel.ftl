package ${data.packageName};

import java.io.Serializable;
<#list data.imports as text>
import ${text};
</#list>
import javax.persistence.*;

@Table(name = "${data.tableName}")
public class ${data.className} implements Serializable {
	private static final long serialVersionUID = 1L;
    
<#list data.fields as field>
	<#if field.column.primaryKey>
	@Id
	</#if>
	@Column(name = "${field.column.name}")
	private ${field.type.simpleName} ${field.name};
	<#if field_has_next>

	</#if>
</#list>

<#list data.fields as field>
	public ${field.type.simpleName} get${field.name?cap_first}() {
		return ${field.name};
	}

	public void set${field.name?cap_first}(${field.type.simpleName} ${field.name}) {
		this.${field.name} = ${field.name};
	}
	<#if field_has_next>

	</#if>
</#list>
}