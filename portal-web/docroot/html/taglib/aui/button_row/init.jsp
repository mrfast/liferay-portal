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
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:button-row:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:button-row:scopedAttributes");
CustomAttributes customAttributes = (CustomAttributes)request.getAttribute("aui:button-row:customAttributes");

Map<String, Object> _options = new HashMap<String, Object>();

_options.putAll(scopedAttributes);
_options.putAll(dynamicAttributes);

java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:button-row:cssClass"));

_updateOptions(_options, "cssClass", cssClass);
%>

<%@ include file="init-ext.jspf" %>

<%!
private static final String _NAMESPACE = "aui:button-row:";
%>