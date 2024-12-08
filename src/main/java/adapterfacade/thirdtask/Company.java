package adapterfacade.thirdtask;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Company {
    private String name;
    private String description;
    private String logo;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}