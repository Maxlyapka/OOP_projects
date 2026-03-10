package Lab1;

public class Motherboard extends Component {
    private String socket;

    public Motherboard(String name, double price, int power, String socket) {
        super(name, Category.MOTHERBOARD, price, power);
        this.socket = socket;
    }

    public String getSocket() { return socket; }
}