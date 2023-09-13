package poo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Partidas {
	private int cod,timeB,timeN;
	private String nombre;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean bot, colorF,auto;
	public Partidas(int cod, int timeB, int timeN, String nombre, LocalDate fecha, LocalTime hora, boolean bot,
			boolean colorF, boolean auto) {
		this.cod = cod;
		this.timeB = timeB;
		this.timeN = timeN;
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.bot = bot;
		this.colorF = colorF;
		this.auto = auto;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getTimeB() {
		return timeB;
	}
	public void setTimeB(int timeB) {
		this.timeB = timeB;
	}
	public int getTimeN() {
		return timeN;
	}
	public void setTimeN(int timeN) {
		this.timeN = timeN;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public boolean isColorF() {
		return colorF;
	}
	public void setColorF(boolean colorF) {
		this.colorF = colorF;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	
	
}
