<?xml version="1.0" encoding="UTF-8"?>
<ureport>
	<cell expand="None" name="A1" col="1" row="1">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="B1" col="2" row="1">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="C1" col="3" row="1">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="D1" col="4" row="1">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	
	<cell expand="None" name="A2" col="1" row="2">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="B2" col="2" row="2">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="C2" col="3" row="2">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="D2" col="4" row="2">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	
	<cell expand="None" name="A3" col="1" row="3">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="B3" col="2" row="3">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="C3" col="3" row="3">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<cell expand="None" name="D3" col="4" row="3">
		<simple-value><![CDATA[]]></simple-value>
		<cell-style font-size="10" align="center" valign="middle"></cell-style>
	</cell>
	<row row-number="1" height="18"/>
	<row row-number="2" height="18"/>
	<row row-number="3" height="18"/>
	<column col-number="1" width="80"/>
	<column col-number="2" width="80"/>
	<column col-number="3" width="80"/>
	<column col-number="4" width="80"/>
<#if set??>
	<datasource name="数据源" type="spring" bean="reportBean">
<#list set as set>
		<dataset name="${set.name}" type="bean" method="loadReportData" clazz="">
<#if set.item??>
<#list set.item as item>
			<field name="${item.name}"/>
</#list>
</#if>
		</dataset>
</#list>
	</datasource>
</#if>
	<paper type="A4" orientation="portrait" paging-mode="fitpage"></paper>
</ureport>