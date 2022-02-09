package com.truper.spring.core.practica3.jugador.api;

import com.truper.spring.core.practica3.liga.Partido;
import com.truper.spring.core.practica3.liga.Torneo;

public interface IJugador {

	public String getNombre();

	public void setNombre(String nombre);

	public Partido getPartido();

	public void setPartido(Partido partido);

	public Torneo getTorneo();

	public void setTorneo(Torneo torneo);

	public void saludar();
}
