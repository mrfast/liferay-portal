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
--%>

<%@ include file="/html/taglib/aui/input/init.jsp" %>

<c:if test='<%= !type.equals("hidden") && !type.equals("assetCategories") %>'>
	<span class="<%= fieldCss %>">
		<span class="aui-field-content">
			<c:if test='<%= Validator.isNotNull(label) && !inlineLabel.equals("right") %>'>
				<label <%= labelTag %>>
					<liferay-ui:message key="<%= label %>" />

					<c:if test="<%= required %>">
						<span class="aui-label-required">(<liferay-ui:message key="required" />)</span>
					</c:if>

					<c:if test="<%= Validator.isNotNull(helpMessage) %>">
						<liferay-ui:icon-help message="<%= helpMessage %>" />
					</c:if>

					<c:if test="<%= changesContext %>">
						<span class="aui-helper-hidden-accessible"><liferay-ui:message key="changing-the-value-of-this-field-will-reload-the-page" />)</span>
					</c:if>
				</label>
			</c:if>

			<c:if test="<%= Validator.isNotNull(prefix) %>">
				<span class="aui-prefix"><liferay-ui:message key="<%= prefix %>" /></span>
			</c:if>

			<span class='aui-field-element <%= Validator.isNotNull(label) && inlineLabel.equals("right") ? "aui-field-label-right" : StringPool.BLANK %>'>
</c:if>

<c:choose>
	<c:when test='<%= (model != null) && type.equals("assetCategories") %>'>
		<liferay-ui:asset-categories-selector
			className="<%= model.getName() %>"
			classPK="<%= _getClassPK(bean, classPK) %>"
			contentCallback='<%= portletResponse.getNamespace() + "getSuggestionsContent" %>'
		/>
	</c:when>
	<c:when test='<%= (model != null) && type.equals("assetTags") %>'>
		<liferay-ui:asset-tags-selector
			className="<%= model.getName() %>"
			classPK="<%= _getClassPK(bean, classPK) %>"
			contentCallback='<%= portletResponse.getNamespace() + "getSuggestionsContent" %>'
		/>
	</c:when>
	<c:when test="<%= (model != null) && Validator.isNull(type) %>">
		<liferay-ui:input-field
			bean="<%= bean %>"
			cssClass="<%= inputCss %>"
			defaultValue="<%= value %>"
			disabled="<%= disabled %>"
			field="<%= field %>"
			fieldParam='<%= fieldParam %>'
			format='<%= (Format)dynamicAttributes.get("format") %>'
			formName="<%= formName %>"
			languageId="<%= languageId %>"
			model="<%= model %>"
		/>
	</c:when>
	<c:when test='<%= type.equals("checkbox") %>'>

		<%
		boolean valueBoolean = checked;

		if (value != null) {
			String valueString = value.toString();

			valueBoolean = GetterUtil.getBoolean(valueString);
		}

		if (!ignoreRequestValue) {
			valueBoolean = ParamUtil.getBoolean(request, name, valueBoolean);
		}
		%>

		<input id="<%= id %>" name="<%= namespace + name %>" type="hidden" value="<%= valueBoolean %>" />

		<input <%= valueBoolean ? "checked" : StringPool.BLANK %> class="<%= inputCss %>" <%= disabled ? "disabled" : StringPool.BLANK %> id="<%= id %>Checkbox" name="<%= namespace + name %>Checkbox" <%= Validator.isNotNull(onChange) ? "onChange=\"" + onChange + "\"" : StringPool.BLANK %> onClick="Liferay.Util.updateCheckboxValue(this); <%= onClick %>" <%= Validator.isNotNull(title) ? "title=\"" + title + "\"" : StringPool.BLANK %> type="checkbox" <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %> />
	</c:when>
	<c:when test='<%= type.equals("radio") %>'>

		<%
		boolean valueBoolean = checked;

		String valueString = StringPool.BLANK;

		if (value != null) {
			valueString = value.toString();

			if (!ignoreRequestValue) {
				String requestValue = ParamUtil.getString(request, name);

				if (Validator.isNotNull(requestValue)) {
					valueBoolean = valueString.equals(requestValue);
				}
			}
		}
		%>

		<input <%= valueBoolean ? "checked" : StringPool.BLANK %> class="<%= inputCss %>" <%= disabled ? "disabled" : StringPool.BLANK %> id="<%= id %>" name="<%= namespace + name %>" <%= Validator.isNotNull(onChange) ? "onChange=\"" + onChange + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(onClick) ? "onClick=\"" + onClick + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(title) ? "title=\"" + title + "\"" : StringPool.BLANK %> type="radio" value="<%= valueString %>" <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %> />
	</c:when>
	<c:when test='<%= type.equals("timeZone") %>'>
		<span class="<%= fieldCss %>">
			<span class="aui-field-content">

				<%
				int displayStyle = TimeZone.LONG;

				if (dynamicAttributes.get("displayStyle") != null) {
					displayStyle = GetterUtil.getInteger((String)dynamicAttributes.get("displayStyle"));
				}
				%>

				<liferay-ui:input-time-zone
					daylight='<%= GetterUtil.getBoolean((String)dynamicAttributes.get("daylight")) %>'
					disabled="<%= disabled %>"
					displayStyle="<%= displayStyle %>"
					name="<%= name %>"
					nullable='<%= GetterUtil.getBoolean((String)dynamicAttributes.get("nullable")) %>'
					value="<%= value.toString() %>"
				/>
			</span>
		</span>
	</c:when>
	<c:otherwise>

		<%
		String valueString = StringPool.BLANK;

		if (value != null) {
			valueString = value.toString();
		}

		if (type.equals("hidden") && (value == null)) {
			valueString = BeanPropertiesUtil.getStringSilent(bean, name);
		}
		else if (!ignoreRequestValue && (type.equals("text") || type.equals("textarea"))) {
			valueString = BeanParamUtil.getStringSilent(bean, request, name, valueString);

			if (Validator.isNotNull(fieldParam)) {
				valueString = ParamUtil.getString(request, fieldParam, valueString);
			}
		}
		%>

		<c:choose>
			<c:when test='<%= type.equals("textarea") %>'>
				<textarea class="<%= inputCss %>" <%= disabled ? "disabled" : StringPool.BLANK %> id="<%= id %>" <%= multiple ? "multiple" : StringPool.BLANK %> name="<%= namespace + name %>" <%= Validator.isNotNull(onChange) ? "onChange=\"" + onChange + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(onClick) ? "onClick=\"" + onClick + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(title) ? "title=\"" + title + "\"" : StringPool.BLANK %> <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>><%= HtmlUtil.escape(valueString) %></textarea>
			</c:when>
			<c:otherwise>
				<input class="<%= inputCss %>" <%= disabled ? "disabled" : StringPool.BLANK %> id="<%= id %>" <%= multiple ? "multiple" : StringPool.BLANK %> name="<%= namespace + name %>" <%= Validator.isNotNull(onChange) ? "onChange=\"" + onChange + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(onClick) ? "onClick=\"" + onClick + "\"" : StringPool.BLANK %> <%= Validator.isNotNull(title) ? "title=\"" + title + "\"" : StringPool.BLANK %> type="<%= Validator.isNull(type) ? "text" : type %>" value="<%= HtmlUtil.escapeAttribute(valueString) %>" <%= AUIUtil.buildData(data) %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %> />
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>

<c:if test='<%= !type.equals("hidden") && !type.equals("assetCategories") %>'>
			</span>

			<c:if test="<%= Validator.isNotNull(suffix) %>">
				<span class="aui-suffix"><liferay-ui:message key="<%= suffix %>" /></span>
			</c:if>

			<c:if test='<%= Validator.isNotNull(label) && inlineLabel.equals("right") %>'>
				<label <%= labelTag %>>
					<liferay-ui:message key="<%= label %>" />

					<c:if test="<%= required %>">
						<span class="aui-label-required">(<liferay-ui:message key="required" />)</span>
					</c:if>

					<c:if test="<%= Validator.isNotNull(helpMessage) %>">
						<liferay-ui:icon-help message="<%= helpMessage %>" />
					</c:if>

					<c:if test="<%= changesContext %>">
						<span class="aui-helper-hidden-accessible"><liferay-ui:message key="changing-the-value-of-this-field-will-reload-the-page" />)</span>
					</c:if>
				</label>
			</c:if>
		</span>
	</span>
</c:if>

<%!
private long _getClassPK(Object bean, long classPK) {
	if ((bean != null) && (classPK <= 0)) {
		if (bean instanceof ClassedModel) {
			ClassedModel classedModel = (ClassedModel)bean;

			Serializable primaryKeyObj = classedModel.getPrimaryKeyObj();

			if (primaryKeyObj instanceof Long) {
				classPK = (Long)primaryKeyObj;
			}
			else {
				classPK = GetterUtil.getLong(primaryKeyObj.toString());
			}
		}
	}

	return classPK;
}
%>