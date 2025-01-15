package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
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
			salir = ejecutarOpciones(consola, opcion);
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

	private boolean ejecutarOpciones(Scanner consola,int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info(nl + "---  Listado de clientes   ---" + nl);
				List<Cliente> clientes = clienteServicio.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente + nl));
			}
			case 2 -> {
				logger.info(nl + "---  Buscar cliente por Id   ---" + nl);
				logger.info(nl + "id cliente a buscar: " );
				int idCliente = Integer.parseInt(consola.nextLine());
				Cliente elCliente =clienteServicio.buscarClientePorId(idCliente);
				if (elCliente != null){
					logger.info(nl + "Cliente encontrado:" + elCliente + nl);
				}else{
					logger.info("Cliente no encontrado"+ nl);
				}
			}
			case 3 -> {
				logger.info(nl+ "---   Agregar cliente   ---");
				logger.info(nl + "Digite el nombre del cliente a agregar: " );
				String nombre = String.valueOf(consola.nextLine());
				logger.info(nl + "Digite el apellido del cliente a agregar: " );
				String apellido = String.valueOf(consola.nextLine());
				logger.info(nl + "Digite el numero de membresia del cliente a agregar: " );
				Integer membresia = Integer.parseInt(consola.nextLine());
				Cliente miCliente = new Cliente();
				miCliente.setNombre(nombre);
				miCliente.setApellido(apellido);
				miCliente.setMembresia(membresia);
				clienteServicio.guardarCliente(miCliente);
				logger.info(nl+ "cliente agregado: " + miCliente +nl);
			}
			case 4 -> {
				logger.info(nl + "---  Editar cliente por Id   ---" + nl);
				logger.info(nl + "id cliente a modificar: " );
				Integer idCliente = Integer.parseInt(consola.nextLine());
				Cliente elCliente =clienteServicio.buscarClientePorId(idCliente);
				if (elCliente != null){
					logger.info(nl + "Digite el nombre del cliente a modificar: " );
					String nombre = String.valueOf(consola.nextLine());
					logger.info(nl + "Digite el apellido del cliente a modificar: " );
					String apellido = String.valueOf(consola.nextLine());
					logger.info(nl + "Digite el numero de membresia del cliente a modificar: " );
					Integer membresia = Integer.parseInt(consola.nextLine());
					elCliente.setNombre(nombre);
					elCliente.setApellido(apellido);
					elCliente.setMembresia(membresia);
					clienteServicio.guardarCliente(elCliente);
					logger.info(nl+ "cliente modificado: " + elCliente +nl);
				}else {
					logger.info("Cliente no encontrado");
				}
			}
			case 5 -> {
				logger.info(nl + "---  Eliminar cliente por Id   ---" + nl);
				logger.info(nl + "id cliente a Eliminar: " );
				Integer idCliente = Integer.parseInt(consola.nextLine());
				Cliente elCliente =clienteServicio.buscarClientePorId(idCliente);
				if (elCliente != null){
					clienteServicio.eliminarCliente(elCliente);
					logger.info( nl +"se elimino el cliente: " + elCliente);
				}else{
					logger.info("Cliente no encontrado");
				}
			}
			case 6 -> {
				logger.info("Hasta pronto!" + nl + nl);
				salir = true;
			}
			default -> {
				logger.info("Opcion NO reconocida!" + opcion + nl);
			}
		}
		return salir;
	}

}
