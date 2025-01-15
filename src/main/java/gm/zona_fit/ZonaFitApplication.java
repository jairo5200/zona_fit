package gm.zona_fit;

import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZonaFitApplication {

	@Autowired
	private IClienteServicio clienteServicio;



	public static void main(String[] args) {
		SpringApplication.run(ZonaFitApplication.class, args);
	}

}
