<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:option:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:option:scopedAttributes");
CustomAttributes customAttributes = (CustomAttributes)request.getAttribute("aui:option:customAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

_options.putAll(scopedAttributes);
_options.putAll(dynamicAttributes);

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:option:cssClass"));
boolean disabled = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:option:disabled")));
java.lang.Object label = (java.lang.Object)request.getAttribute("aui:option:label");
boolean selected = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:option:selected")));
java.lang.String style = GetterUtil.getString((java.lang.String)request.getAttribute("aui:option:style"));
java.lang.Object value = (java.lang.Object)request.getAttribute("aui:option:value");

_updateOptions(_options, "cssClass", cssClass);
_updateOptions(_options, "disabled", disabled);
_updateOptions(_options, "label", label);
_updateOptions(_options, "selected", selected);
_updateOptions(_options, "style", style);
_updateOptions(_options, "value", value);
%>

<%@ include file="init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:option:";
%>