package com.bstan.eje2.controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bstan.eje2.modelo.Titulo;
import com.bstan.eje2.repositories.RepCandidato;
import com.bstan.eje2.repositories.RepTitulo;

@Controller
public class ControladorTitulo {

	@Autowired
	private RepTitulo repTit;
	@Autowired
	private RepCandidato repCand;
	
	@CrossOrigin
	@PostMapping (path = "/titulo")
	public @ResponseBody Object addTit(@RequestBody Titulo titulo) {
		repTit.save(titulo);
		return titulo;
	}
	
	@CrossOrigin
	@GetMapping(path = "/titulo")
	public @ResponseBody Object getTitulo(@RequestParam int candId) {
		List<Titulo> titulos = new ArrayList();
		Iterable<Titulo> iterTit  = repTit.findAll();
		for(Titulo titulo : iterTit) {
			if(titulo.getCandidato().getId() == candId)
				titulos.add(titulo);
		}
		return titulos;
	}
	
	@CrossOrigin
	@DeleteMapping(path = "/titulo")
	public @ResponseBody Object delTit(@RequestBody Titulo titulo) {
		repTit.deleteById(titulo.getId());
		return titulo;
	}
	
	@CrossOrigin
	@PutMapping (path = "/titulo")
	public @ResponseBody Object modifTitulo (@RequestBody Titulo titulo) {
		if(titulo.getId() == 0)
			return null;
		Optional<Titulo> opTitulo = repTit.findById(titulo.getId());
		Titulo newTit = opTitulo.get();
		
		if(titulo.getNombre() != null)
			newTit.setNombre(titulo.getNombre());
		if(titulo.getCentro() != null)
			newTit.setCentro(titulo.getCentro());
		if(titulo.getFechaExp() != null)
			newTit.setFechaExp(titulo.getFechaExp());
		if(titulo.getNivelEd() != null)
			newTit.setNivelEd(titulo.getNivelEd());
		if(titulo.getImg() != null)
			newTit.setImg(titulo.getImg());
		
		repTit.save(newTit);		
		return null;
	}
	
	@GetMapping (path = "/imagen")
	public @ResponseBody BufferedImage getImg(@RequestBody Titulo tit) {
		Iterable<Titulo> iterTit = repTit.findAll();
		for(Titulo titulo : iterTit) {
			if(titulo.getId() == tit.getId()) {				
				try {	
					byte[] img = Files.readAllBytes(titulo.getImg().toPath());
					ByteArrayInputStream bais = new ByteArrayInputStream(img);
					return ImageIO.read(bais);
					
//					HttpHeaders resHeader = new HttpHeaders();
//					InputStream is = new BufferedInputStream(new ByteArrayInputStream(img));
//					resHeader.set("Content", URLConnection.guessContentTypeFromStream(is));
//					return new ResponseEntity<byte[]>(img, resHeader, HttpStatus.CREATED);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				try {
//					FileInputStream fis = new FileInputStream(titulo.getImg());
//					ObjectInputStream ois = new ObjectInputStream(fis);
//					byte[] img = ois.read(buf, off, len)
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
		return null;
	}
	
//	@GetMapping(path = "/imagen")
//	public@ResponseBody Object getImg(@RequestBody Titulo tit) {
//		Iterable<Titulo> iterTit = repTit.findAll();
//		for(Titulo titulo : iterTit) {
//			if(titulo.getId() == tit.getId()) {
//				byte[] img = Util.readBytesFromFile(titulo.getImg().toString());
//				HttpHeaders responseHeaders = new HttpHeaders();
//				InputStream is = new BufferedInputStream(new ByteArrayInputStream(img));
//				try {
//					responseHeaders.set("Content", URLConnection.guessContentTypeFromStream(is));
//					return new ResponseEntity<byte[]> (img, responseHeaders,HttpStatus.CREATED);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
//	@GetMapping (path = "/imagen")
//	public @ResponseBody JLabel getImg (@RequestBody Titulo tit) {
//		Iterable<Titulo> iterTit = repTit.findAll();
//		for (Titulo titulo : iterTit) {
//			if(titulo.getId() == tit.getId()) {		
//				openImg(titulo.getImg().toString());
//				return null;
//			}
//		}
//		return null;
//	}
//	
//	private String openImg(String path) {
//		try {
//			
//			URL	url = new URL(path);
//		
//			URLConnection conn = url.openConnection();
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String input = null;
//			while((input = br.readLine()) != null) {
//				return input;
//			}
//			
//			br.close();
//			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		return null;
//	}
//	protected ImageIcon createImageIcon(URI uri) {
//		URL imgURL;
//		try {
//			imgURL = uri.toURL();
//			if (imgURL != null) {
//				return new ImageIcon(imgURL);
//			} else {
//				System.err.println("Couldn't find file: " + uri.toString());
//				return null;
//			}
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
//	@GetMapping (path ="/imagen")
//	public @ResponseBody BufferedImage getImg (@RequestBody Titulo tit) {
//		Iterable<Titulo> iterTit = repTit.findAll();
//		for(Titulo titulo : iterTit) {
//			if(titulo.getId() == tit.getId()) {
//				BufferedImage img;
//				try {
//					img = ImageIO.read(new ByteArrayInputStream(getImg(titulo.getImg().toString())));
//					return img;	
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}						
//			}				
//		}
//		return null;
//	}
//	
//	private byte[] getImg(String path) {
//		ByteArrayOutputStream output = new ByteArrayOutputStream();						
//		InputStream is;
//		try {
//			is = new FileInputStream(path);			
//			int n = 0;
//			byte[] buffer = Util.readBytesFromFile(path);	
//			while (-1 != (n = is.read(buffer))) {
//				output.write(buffer,0,n);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return output.toByteArray();
//	}
}
