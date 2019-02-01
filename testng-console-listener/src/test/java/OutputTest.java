import com.github.letsrokk.testng.TestNGConsoleProgressListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGConsoleProgressListener.class)
public class OutputTest {

    @Test
    public void test1(){
        // do nothing
    }

    @Test
    public void test2(){
        // do nothing
    }

}
