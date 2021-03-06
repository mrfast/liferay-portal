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

package com.liferay.portalweb.plugins.testlocalized;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewPortletChineseLocalizedTest extends BaseTestCase {
	public void testViewPortletChineseLocalized() throws Exception {
		selenium.open("/web/guest/home/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Test Localized Page")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.saveScreenShotAndSource();
		selenium.clickAt("link=Test Localized Page",
			RuntimeVariables.replace("Test Localized Page"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		assertEquals(RuntimeVariables.replace("Chinese"),
			selenium.getText("//section/div/div/div/a[1]"));
		selenium.click(RuntimeVariables.replace("//section/div/div/div/a[1]"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		assertEquals(RuntimeVariables.replace(
				"Override a portal language entry from a hook."),
			selenium.getText("//td[1]"));
		assertEquals(RuntimeVariables.replace("first-name"),
			selenium.getText("//td[2]"));
		assertEquals(RuntimeVariables.replace("first-name"),
			selenium.getText("//td[3]"));
		assertEquals(RuntimeVariables.replace("\u540d\u5b57"),
			selenium.getText("//td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override a portal language entry from a portlet."),
			selenium.getText("//tr[3]/td[1]"));
		assertEquals(RuntimeVariables.replace("welcome"),
			selenium.getText("//tr[3]/td[2]"));
		assertEquals(RuntimeVariables.replace("Welcome 2.0"),
			selenium.getText("//tr[3]/td[3]"));
		assertEquals(RuntimeVariables.replace("Welcome 2.0"),
			selenium.getText("//tr[3]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[3]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Add a new portlet language entry from a hook."),
			selenium.getText("//tr[4]/td[1]"));
		assertEquals(RuntimeVariables.replace("playing-basketball-is-fun"),
			selenium.getText("//tr[4]/td[2]"));
		assertEquals(RuntimeVariables.replace("playing-basketball-is-fun"),
			selenium.getText("//tr[4]/td[3]"));
		assertEquals(RuntimeVariables.replace("Playing basketball is fun."),
			selenium.getText("//tr[4]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[4]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Add a new portlet language entry from a portlet."),
			selenium.getText("//tr[5]/td[1]"));
		assertEquals(RuntimeVariables.replace("please-take-a-cool-drink"),
			selenium.getText("//tr[5]/td[2]"));
		assertEquals(RuntimeVariables.replace("Please take a cool drink."),
			selenium.getText("//tr[5]/td[3]"));
		assertEquals(RuntimeVariables.replace("Please take a cool drink."),
			selenium.getText("//tr[5]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[5]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Add a new unlocalized language entry from a hook."),
			selenium.getText("//tr[6]/td[1]"));
		assertEquals(RuntimeVariables.replace(
				"this-is-an-unlocalized-hook-message"),
			selenium.getText("//tr[6]/td[2]"));
		assertEquals(RuntimeVariables.replace(
				"this-is-an-unlocalized-hook-message"),
			selenium.getText("//tr[6]/td[3]"));
		assertEquals(RuntimeVariables.replace(
				"This is an unlocalized hook message."),
			selenium.getText("//tr[6]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[6]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Add a new unlocalized language entry from a portlet."),
			selenium.getText("//tr[7]/td[1]"));
		assertEquals(RuntimeVariables.replace(
				"this-is-an-unlocalized-portlet-message"),
			selenium.getText("//tr[7]/td[2]"));
		assertEquals(RuntimeVariables.replace(
				"This is an unlocalized portlet message."),
			selenium.getText("//tr[7]/td[3]"));
		assertEquals(RuntimeVariables.replace(
				"This is an unlocalized portlet message."),
			selenium.getText("//tr[7]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[7]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the default modifier for a portal language entry from a hook."),
			selenium.getText("//tr[8]/td[1]"));
		assertEquals(RuntimeVariables.replace("post"),
			selenium.getText("//tr[8]/td[2]"));
		assertEquals(RuntimeVariables.replace("post"),
			selenium.getText("//tr[8]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u53d1\u5e03"),
			selenium.getText("//tr[8]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[8]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the adjective modifier for a portal language entry from a hook."),
			selenium.getText("//tr[9]/td[1]"));
		assertEquals(RuntimeVariables.replace("post[adjective]"),
			selenium.getText("//tr[9]/td[2]"));
		assertEquals(RuntimeVariables.replace("post[adjective]"),
			selenium.getText("//tr[9]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u53d1\u5e03"),
			selenium.getText("//tr[9]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[9]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the noun modifier for a portal language entry from a hook."),
			selenium.getText("//tr[10]/td[1]"));
		assertEquals(RuntimeVariables.replace("post[noun]"),
			selenium.getText("//tr[10]/td[2]"));
		assertEquals(RuntimeVariables.replace("post[noun]"),
			selenium.getText("//tr[10]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u53d1\u5e03"),
			selenium.getText("//tr[10]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[10]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the verb modifier for a portal language entry from a hook."),
			selenium.getText("//tr[11]/td[1]"));
		assertEquals(RuntimeVariables.replace("post[verb]"),
			selenium.getText("//tr[11]/td[2]"));
		assertEquals(RuntimeVariables.replace("post[verb]"),
			selenium.getText("//tr[11]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u53d1\u5e03"),
			selenium.getText("//tr[11]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[11]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the default modifier for a portal language entry from a portlet."),
			selenium.getText("//tr[12]/td[1]"));
		assertEquals(RuntimeVariables.replace("comment"),
			selenium.getText("//tr[12]/td[2]"));
		assertEquals(RuntimeVariables.replace("comment"),
			selenium.getText("//tr[12]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u8bc4\u8bba"),
			selenium.getText("//tr[12]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[12]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the adjective modifier for a portal language entry from a portlet."),
			selenium.getText("//tr[13]/td[1]"));
		assertEquals(RuntimeVariables.replace("comment[adjective]"),
			selenium.getText("//tr[13]/td[2]"));
		assertEquals(RuntimeVariables.replace("comment[adjective]"),
			selenium.getText("//tr[13]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u8bc4\u8bba"),
			selenium.getText("//tr[13]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[13]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the noun modifier for a portal language entry from a portlet."),
			selenium.getText("//tr[14]/td[1]"));
		assertEquals(RuntimeVariables.replace("comment[noun]"),
			selenium.getText("//tr[14]/td[2]"));
		assertEquals(RuntimeVariables.replace("comment[noun]"),
			selenium.getText("//tr[14]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u8bc4\u8bba"),
			selenium.getText("//tr[14]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[14]/td[5]"));
		assertEquals(RuntimeVariables.replace(
				"Override the verb modifier for a portal language entry from a portlet."),
			selenium.getText("//tr[15]/td[1]"));
		assertEquals(RuntimeVariables.replace("comment[verb]"),
			selenium.getText("//tr[15]/td[2]"));
		assertEquals(RuntimeVariables.replace("comment[verb]"),
			selenium.getText("//tr[15]/td[3]"));
		assertEquals(RuntimeVariables.replace("\u8bc4\u8bba"),
			selenium.getText("//tr[15]/td[4]"));
		assertEquals(RuntimeVariables.replace("PASSED"),
			selenium.getText("//tr[15]/td[5]"));
	}
}