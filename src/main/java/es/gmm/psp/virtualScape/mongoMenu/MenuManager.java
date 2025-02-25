package es.gmm.psp.virtualScape.mongoMenu;

public class MenuManager {
    public static void mainMenu() {
        System.out.println("\nMenú principal ------------------------------" +
                "\n1. Reservas" +
                "\n2. Salas" +
                "\n3. Consultas especiales" +
                "\n4. Salir" +
                "\n------------------------------");
    }

    public static void reservationsMenu() {
        System.out.println("\nMenú reservas ------------------------------" +
                "\n1. create" +
                "\n2. findAll" +
                "\n3. findById" +
                "\n4. update" +
                "\n5. delete" +
                "\n6. Volver" +
                "\n------------------------------");
    }

    public static void roomsMenu() {
        System.out.println("\nMenú salas ------------------------------" +
                "\n1. create" +
                "\n2. findAll" +
                "\n3. findById" +
                "\n4. update" +
                "\n5. Volver" +
                "\n------------------------------");
    }

    public static void specialQueriesMenu() {
        System.out.println("\nMenú consultas especiales ------------------------------" +
                "\n1. findByDate" +
                "\n2. findByTheme" +
                "\n3. findMostBooked" +
                "\n4. Volver" +
                "\n------------------------------");
    }

    public static void reservationUpdateMenu() {
        System.out.println("\nMenú actualizar reserva ------------------------------" +
                "\n1. Actualizar nombre de la sala" +
                "\n2. Actualizar fecha" +
                "\n3. Actualizar contacto" +
                "\n4. Actualizar número de jugadores" +
                "\n5. Confirmar actualización" +
                "\n6. Volver" +
                "\n------------------------------");
    }

    public static void roomUpdateMenu() {
        System.out.println("\nMenú actualizar sala ------------------------------" +
                "\n1. Actualizar nombre" +
                "\n2. Actualizar capacidad mínima" +
                "\n3. Actualizar capacidad máxima" +
                "\n4. Actualizar temas" +
                "\n5. Confirmar actualización" +
                "\n6. Volver" +
                "\n------------------------------");
    }
}
