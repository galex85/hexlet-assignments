package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

    public String getName() {
        return name;
    }

    // BEGIN
    private String message;

    @PostConstruct
    public void init() {
        this.message = "Bean is initialized!";
        System.out.println(message);
    }
    // END
}
