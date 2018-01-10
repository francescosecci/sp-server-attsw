package com.pufose.server;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;
import com.gargoylesoftware.htmlunit.javascript.host.dom.NodeList;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=AWebController.class)
public class GridViewHtmlUnitTest {

	@Autowired
	private WebClient webClient;
	
	@MockBean
	private GridService gridService;
	
	@Test
	public void welcomePageTest() throws Exception {
		HtmlPage page = this.webClient.getPage("/");
		assertThat(page.getTitleText()).isEqualTo("Home Page");
		HtmlDivision div = page.getHtmlElementById("choice");
		assertNotNull(div);
		DomNodeList<HtmlElement> ul = div.getElementsByTagName("ul");
		assertEquals(ul.size(), 1);
		HtmlAnchor a = page.getAnchorByHref("/viewdb");
		HtmlAnchor a1 = page.getAnchorByHref("/addtable");
		HtmlAnchor a2 = page.getAnchorByHref("/remtable");
		assertEquals(a.toString(), "HtmlAnchor[<a href=\"/viewdb\">]");
		assertEquals(a1.toString(), "HtmlAnchor[<a href=\"/addtable\">]");
		assertEquals(a2.toString(), "HtmlAnchor[<a href=\"/remtable\">]");
	}
	

}
