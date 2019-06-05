package com.bstan.eje2.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bstan.eje2.modelo.CandTec;
import com.bstan.eje2.modelo.Candidato;
import com.bstan.eje2.modelo.ExpLaboral;
import com.bstan.eje2.modelo.Tecnologia;
import com.bstan.eje2.modelo.Titulo;
import com.bstan.eje2.repositories.RepCandTec;
import com.bstan.eje2.repositories.RepCandidato;
import com.bstan.eje2.repositories.RepExpLaboral;
import com.bstan.eje2.repositories.RepTecnologia;
import com.bstan.eje2.repositories.RepTitulo;

public class Util {

	private static final String PATRON_FECHA = "dd/mm/yyyy";
	
	@Autowired
	private static RepCandidato repCand;
	@Autowired
	private static RepTecnologia repTec;
	@Autowired
	private static RepCandTec repCandTec;
	@Autowired
	private static RepExpLaboral repExpLab;
	@Autowired
	private static RepTitulo repTit;
	
	public static Date parseFecha (String fecha) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(PATRON_FECHA);
		return sdf.parse(fecha);
	}
	
	public static String formatFecha (Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat(PATRON_FECHA);
		return sdf.format(fecha);
	}
	
	public static Candidato getCand(Long id) {
		Iterable<Candidato> iterCand = repCand.findAll();
		for(Candidato cand : iterCand) {
			if(cand.getId() == id)
				return cand;
		}
		return null;
	}
	
	public static Tecnologia getTec(Long id) {
		Iterable<Tecnologia> iterTec = repTec.findAll();
		for(Tecnologia tec : iterTec) {
			if(tec.getId() == id)
				return tec;
		}
		return null;
	}
	
	public static CandTec getCandTec(Long id) {
		Iterable<CandTec> iterCandTec = repCandTec.findAll();
		for(CandTec candTec : iterCandTec) {
			if(candTec.getId() == id)
				return candTec;
		}
		return null;
	}
	
	public static ExpLaboral getExpLab(Long id) {
		Iterable<ExpLaboral> iterExpLab = repExpLab.findAll();
		for(ExpLaboral expLab : iterExpLab) {
			if(expLab.getId() == id)
				return expLab;
		}
		return null;
	}
	
	public static Titulo getTitulo(Long id) {
		Iterable<Titulo> iterTit = repTit.findAll();
		for(Titulo titulo : iterTit) {
			if(titulo.getId() == id)
				return titulo;			
		}
		return null;
	}
	
	  public static byte[] readBytesFromFile(String filePath) {

	        FileInputStream fileInputStream = null;
	        byte[] bytesArray = null;

	        try {

	            File file = new File(filePath);
	            bytesArray = new byte[(int) file.length()];
	            
	            fileInputStream = new FileInputStream(file);
	            fileInputStream.read(bytesArray);

	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (fileInputStream != null) {
	                try {
	                    fileInputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }

	        }

	        return bytesArray;

	    }
}
