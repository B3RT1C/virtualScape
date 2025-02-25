package es.gmm.psp.virtualScape;

import es.gmm.psp.virtualScape.model.Contact;
import es.gmm.psp.virtualScape.model.Date;
import es.gmm.psp.virtualScape.model.Reservation;
import es.gmm.psp.virtualScape.mongoMenu.MongoconsoleApp;
import es.gmm.psp.virtualScape.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VirtualScapeApplication {

	@Autowired
	private ReservationService reservaService;

	public static void main(String[] args) {
		var context = SpringApplication.run(VirtualScapeApplication.class, args);
		VirtualScapeApplication app = context.getBean(VirtualScapeApplication.class);

		MongoconsoleApp mongoconsoleApp = context.getBean(MongoconsoleApp.class);
		mongoconsoleApp.start();
	}
}
