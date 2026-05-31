package Lab1;

public class Processor extends Component {
    private String socket;

    public Processor(String name, double price, int power, String socket) {
        super(name, Category.CPU, price, power);
        this.socket = socket;
    }

    public String getSocket() { return socket; }
}
