//TODO DONE

/**
 * The runnning class
 */
public class Main {

    /**
     * The running method, it starts the controller
     */
    public static void main(String[] args) {
        Controller.GetInstance();
        Controller.GetInstance().Init();

        System.out.println("Welcome to the ProjlaboSOKK team's program!");
    }
}