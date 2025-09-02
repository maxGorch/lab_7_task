import org.example.Main;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testMain
{
    @Test
    public void test_Solve()
    {
        int a=10;
        int b=10;
        Main test = new Main();
        assertEquals("Должно быть 20", 20,test.solve(a,b));
    }
}
