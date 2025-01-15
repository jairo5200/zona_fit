package gm.zona_fit;

import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("inciiando la aplicacion");
		//levantar la fabrica de spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("aplicacion finalizada!!");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			var opcion = mostrarMenu(consola);
			//salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
	}

	private int mostrarMenu(Scanner consola) {
		logger.info("""
       	\n*** aplicacion zonafit (GYM) ***
    	1. Listar Clientes
    	2. Buscar Cliente
    	3. Agregar Cliente
    	4. Modificar Cliente
    	5. Eliminar Cliente
    	6. Salir
    	Elige una opcion:\s""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}
}
