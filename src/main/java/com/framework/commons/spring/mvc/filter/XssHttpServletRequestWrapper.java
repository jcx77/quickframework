package com.framework.commons.spring.mvc.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.framework.commons.exception.SysException;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;


import cn.hutool.core.io.IoUtil;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private boolean isUpload = false;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		String contentType = request.getContentType();
		if (contentType != null) {
			isUpload = contentType.startsWith("multipart");
		}
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value != null) {
			isValid(value);
		}
		return value;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value != null) {
			isValid(value);
		}
		return value;
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				isValid(values[i]);
			}
		}
		return values;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (isUpload) {
			return super.getInputStream();
		}
		String value = IoUtil.read(super.getInputStream(), StandardCharsets.UTF_8);
		if (value != null) {
			isValid(value);
			final ByteArrayInputStream in = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
			return new ServletInputStream() {
				@Override
				public boolean isFinished() {
					return false;
				}

				@Override
				public boolean isReady() {
					return false;
				}

				@Override
				public void setReadListener(ReadListener readListener) {
				}

				@Override
				public int read() {
					return in.read();
				}
			};
		} else {
			return super.getInputStream();
		}
	}

	private void isValid(String value) {
		Document doc = Parser.parseBodyFragment(value, "");
		Cleaner cleaner = new Cleaner(Whitelist.relaxed());
		if (!cleaner.isValid(doc)) {
			throw new SysException("包含非法字符，请检查数据项");
		}
	}
}