package com.truper.spring.aop.util.bean.api.impl;

import org.fusesource.jansi.Ansi;
import org.springframework.stereotype.Component;

import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

@Component
public class ColorWriter implements IColorWriter {

	@Override
	public String getColoredMessage(Color color, String mensaje) {
		return Ansi.ansi().eraseScreen()
				.render("@|" + color.getColor() + "    " + mensaje + "|@")
				.toString();
	}

}
