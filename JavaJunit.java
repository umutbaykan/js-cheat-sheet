import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class JavaJunit {
  public static void main(String[] args) {

    // BeforeEach
    private PasswordValidator pw;
    @Before
    public void setup() {
      // Can now access pw in every test
      pw = new PasswordValidator();
    }

    @BeforeClass
    public static void setUpClass() {
      // This is executed once before any test methods
    }

    @AfterClass
    @After

    // @Test annotation required
    @Test public void TestPasswordLengthLongerThan8Characters() {
      Assert.assertEquals(true, pw.validateLength("morethan8"));
    }

    // Test with a timeout
    @Test(timeout=500)

    // Ignore the test
    @Ignore

    // Expect test to throw argument
    @Test(expected = IllegalArgumentException.class)

    /////// Assertion methods
    Assert.assertTrue(testThatReturnsBoolean);
    Assert.assertFalse(testThatReturnsBoolean);
    Assert.assertEquals(expected, actual);
    Assert.assertSame(v1, v2); // fails if they dont refer to the same object reference
    Assert.assertNotSame(v1, v2);
    Assert.assertNull(value);
    Assert.assertNotNull(value);
    Assert.fail();
    // Each method can also be passed a string to display if it fails, e.g.
    Assert.assertEquals(“message”, expected, actual);


    ////// Suites
    /// Initialize a base case shared amongst multiple suites
    public class BaseTest {
      protected PasswordValidator pw;
      @Before
      public void setup() {
        // Can refer to pw from each suite file
        pw = new PasswordValidator();
      }
    }
    /// Each suite then extends the base to access
    public class PasswordValidatorCapitalsTest extends BaseTest {
      @Test public void TestCapitalLetterExists() {
          Assert.assertEquals(true, pw.validateCapitals("Password"));
          Assert.assertEquals(0, pw.errorMessages.size());
      }
    
    /// 
    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            PasswordValidatorLengthTest.class,
            PasswordValidatorNumericTest.class
            // add more suites as you need
    })
    public class PasswordValidatorTestSuite {
      // No need for anything here, this is just a marker
    }
  }
}
