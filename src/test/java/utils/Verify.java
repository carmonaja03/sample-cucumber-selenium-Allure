package utils;

import automation.library.common.TestContext;
import automation.library.selenium.core.Element;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import java.util.StringJoiner;

public class Verify extends Assertion {

    private static Verify instance;
    private StringJoiner message = new StringJoiner("\n");
    private Boolean failed = false;

    private Verify() {}

    /**
     * Get the instance of Verify
     * @return Verify instance
     */
    public static Verify getInstance(){
        if(null == instance){
            instance = new Verify();
        }
        return instance;
    }

    /**
     * Test the Assertion
     * @param assertCommand
     */
    @Override
    protected void doAssert(IAssert assertCommand) {
        onBeforeAssert(assertCommand);
        try {
            executeAssert(assertCommand);
            onAssertSuccess(assertCommand);
        } catch(AssertionError ex) {
            onAssertFailure(assertCommand, ex);
        } finally {
            onAfterAssert(assertCommand);
        }
    }

    /**
     * Run After Assert
     * @param assertCommand
     */
    @Override
    public void onAfterAssert(IAssert assertCommand) {
        TestContext.getInstance().sa().executeAssert(assertCommand);
    }

    /**
     * Run if Assert Failure
     * @param assertCommand
     * @param ex
     */
    @Override
    public void onAssertFailure(IAssert assertCommand, AssertionError ex) {
        message.add(ex.getMessage());
        failed = true;
    }

    /**
     * Check if any of the asserts failed so far
     * @return Boolean
     */
    public Boolean hasFailed() {
        return failed;
    }

    /**
     * Reset the object failed state and error message
     * @return error message
     */
    public String reset(){
        failed = false;
        String errorMegs = message.toString();
        message = new StringJoiner("\n");
        return errorMegs;
    }

    public void assertEquals(boolean contains) {
    }

    public void assertFalse(Element mr) {
    }
}
