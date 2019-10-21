import org.junit.Test;

public class TestMain {

    @Test
    public void test(){
        System.out.println(TestMain.class.getClassLoader().getResourceAsStream("./xml/henan-dishes.xml"));

    }
}
