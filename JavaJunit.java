import org.junit.Test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

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

    /////// Mocks
    // Create a mock of the List interface
    List<String> mockList = Mockito.mock(List.class);
    // Create a mock of existing class;
    Password password = Mockito.mock(Password.class);
    Mockito.when(password.getPassword()).thenReturn("thepassword");
    // Mock a collaborator;
    DatabaseConnection dbMock = mock(DatabaseConnection.class);
    when(dbMock.query(anyString())).thenReturn("Mocked result");

    // Setting an expectation for a method call
    when(mockList.get(0)).thenReturn("Mocked Value");
    // Setting expectations for consecutive method calls. Remember, these are consecutive!
    when(mockList.size()).thenReturn(2, 3, 4);
    // Verifying that a method was called with specific arguments
    verify(mockList).add("Test Value");

    // Stubbing a method to throw an exception
    when(password.getPassword()).thenThrow(new RuntimeException("Error"));

    // Using argument matchers
    when(mockList.get(anyInt())).thenReturn("Any Value");
    Mockito.when(mockList.get(Mockito.anyInt())).thenReturn("Any Value");
    when(mockList.contains(eq("Test"))).thenReturn(true);

    // Verifying that a method was called a certain number of times
    // Remember, the last method you are chaning is supposed to be the method of the mocked class
    verify(mockList, times(3)).clear();
    Mockito.verify(password, Mockito.times(5)).getPassword();
    Mockito.verify(password, Mockito.times(1)).setPassword("calledWithThis");

    // Verifying that a method was never called
    verify(mockList, never()).isEmpty();
    Mockito.verify(password, Mockito.never()).setPassword(Mockito.anyString());

    // Verifying that a method was called with correct args
    // Call a method on the mock
    mockList.add("Test Value");
    // Verify that the method was called with any string argument
    verify(mockList).add(anyString());
    // Verify that the method was called with the correct argument
    verify(mockList).add("Test Value");

    // Can also verify mocked beans with this method
    verify(mockedAwsStorageService, Mockito.times(1)).deleteTrackFromStorage("music.mp3-test");

    // Partial mocking of a real object
    Collaborator realCollaborator = new Collaborator();
    Collaborator collaboratorSpy = spy(realCollaborator);
    when(collaboratorSpy.someMethod()).thenReturn("Mocked result");

    Password pw = Password.createPassword("Hellothere");
    Password mockPw = Mockito.spy(pw);
    Mockito.when(mockPw.getPassword()).thenReturn("Your password!");
    Assert.assertEquals("Your password!", mockPw.getPassword());
    // Method not explicitly mocked, but inherits it from the original class;
    Assert.assertEquals("Hi!", mockPw.someMethod());

}
