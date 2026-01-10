import static org.junit.Assert.assertEquals; import org.junit.Test;

public class BmiEvaluatorTest {

@Test
public void testUnderweight() {
    assertEquals("You are underweight", BmiEvaluator.evaluate(17.5f));
}

@Test
public void testNormalWeight() {
    assertEquals("You are normal", BmiEvaluator.evaluate(22.0f));
}

@Test
public void testOverweight() {
    assertEquals("You are overweight", BmiEvaluator.evaluate(28.5f));
}

@Test
public void testObese() {
    assertEquals("You are obese", BmiEvaluator.evaluate(32.0f));
}

@Test
public void testExtremelyObese() {
    assertEquals("You are extremely obese", BmiEvaluator.evaluate(40.0f));
}

}
