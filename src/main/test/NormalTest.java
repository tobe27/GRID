import model.GridUser;
import org.junit.Test;

public class NormalTest {
    @Test
    public void nullTest() {
        GridUser gridUser = new GridUser();
        gridUser.setPassword("");
        String psw = gridUser.getPassword();
        System.out.println(gridUser);
        if (psw == null)
            System.out.println("null");
        else if (psw.isEmpty())
            System.out.println("isEmpty");

        else if (psw.equals(""))
            System.out.println("^^^");

    }

    @Test
    public void timeTest() {
        System.out.println(System.currentTimeMillis());
    }
}
