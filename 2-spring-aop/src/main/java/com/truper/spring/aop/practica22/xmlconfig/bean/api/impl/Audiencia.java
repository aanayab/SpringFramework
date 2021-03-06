package com.truper.spring.aop.practica22.xmlconfig.bean.api.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.truper.spring.aop.practica22.xmlconfig.bean.api.IAudiencia;
import com.truper.spring.aop.util.Color;
import com.truper.spring.aop.util.bean.api.IColorWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Define Bean Audiencia
@Component("audienciaBean")
public class Audiencia implements IAudiencia {

	// Inyecta IColorWriter
	@Autowired
	private IColorWriter colorWriter;

	@Override
	public void aplaudir() {
		print(colorWriter.getColoredMessage(Color.BLUE,
				"[Aspecto Audiencia] El publico aplaude... clap, clap, clap...."));
	}

	@Override
	public void abuchear() {
		print(colorWriter.getColoredMessage(Color.RED,
				"[Aspecto Audiencia] El publico abuchea... buuuuuuuuuuuu...."));
	}

	@Override
	public void sePoneDePie() {
		print(colorWriter.getColoredMessage(Color.YELLOW,
				"[Aspecto Audiencia] El publico se pone de pie."));
	}

	@Override
	public void seSienta() {
		print(colorWriter.getColoredMessage(Color.YELLOW,
				"[Aspecto Audiencia] El publico se sienta en su lugar."));
	}

	@Override
	public void gritaGol() {
		print(colorWriter.getColoredMessage(Color.GREEN,
				"[Aspecto Audiencia] El publico grita gol... GOOOOOOOOOOOOOOOOOOOOOOOOL !!!!"));
	}

	@Override
	public void gritaOle() {
		print(colorWriter.getColoredMessage(Color.MAGENTA,
				"[Aspecto Audiencia] El publico grita Ole... Ooooooleeeeeee..."));
	}

	private void print(String mensaje) {
		log.info("{}", mensaje);
	}

	@Override
	public Object sePoneDeNervios(ProceedingJoinPoint pjp) throws Throwable {

		print(colorWriter.getColoredMessage(Color.CYAN,
				"[Aspecto Audiencia] El publico se pone de nervios"));

		Object o = pjp.proceed();

		// Implementa metodo "se pone de nervios"
		this.abuchear();
		this.abuchear();
		this.aplaudir();
		this.aplaudir();

		print(colorWriter.getColoredMessage(Color.CYAN,
				"[Aspecto Audiencia] El publico se deja de pone de nervios"));

		return o;
	}

}
