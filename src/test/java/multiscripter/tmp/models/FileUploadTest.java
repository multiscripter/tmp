package multiscripter.tmp.models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class FileUploadTest tests class FileUpload.
 *
 * @author Multiscripter
 * @version 2019-10-26
 * @since 2019-10-26
 */
public class FileUploadTest {

    /**
     * Actual object.
     */
    private FileUpload actual;

    /**
     * Expected object.
     */
    private static FileUpload expected;

    /**
     * Actions before all test.
     */
    @BeforeClass
    public static void beforeAllTests() {
        expected = new FileUpload();
        expected.setId("121035a1-bc0b-484f-9ded-726968a13e4b");
        expected.setFileName("testfilename.csv");
        expected.setCompanyId("1");
        expected.setUserId("new_user");
        expected.setUploaded(new Date(1572048000L));
    }

    /**
     * Actions before each test.
     */
    @Before
    public void before() {
        actual = new FileUpload();
        actual.setId("121035a1-bc0b-484f-9ded-726968a13e4b");
        actual.setFileName("testfilename.csv");
        actual.setCompanyId("1");
        actual.setUserId("new_user");
        actual.setUploaded(new Date(1572048000L));
    }

    /**
     * Tests public boolean equals(Object o).
     */
    @Test
    public void testEquals() {
        assertEquals(expected, actual);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison 2 references of one object.
     */
    @Test
    public void testEquals2refsOfOneObject() {
        actual = expected;
        assertEquals(expected, actual);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with null;
     */
    @Test
    public void testEqualsWithNull() {
        assertNotEquals(expected, null);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with object which has different class;
     */
    @Test
    public void testEqualsWithObjectOfDifferentClass() {
        assertNotEquals(expected, "");
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with object which has different identifier;
     */
    @Test
    public void testEqualsDifferentId() {
        actual.setId("fc8580c3-23dd-429e-9a42-660be2449397");
        assertNotEquals(expected, actual);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with object which has different file name.
     */
    @Test
    public void testEqualsDifferentFileName() {
        actual.setFileName("another-filename.csv");
        assertNotEquals(expected, actual);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with object which has different company identifier.
     */
    @Test
    public void testEqualsDifferentCompanyId() {
        actual.setCompanyId("another-company-id");
        assertNotEquals(expected, actual);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with object which has different user identifier.
     */
    @Test
    public void testEqualsDifferentUserId() {
        actual.setUserId("another-user-id");
        assertNotEquals(expected, actual);
    }

    /**
     * Tests public boolean equals(Object o).
     * Comparison with object which has different upload date.
     */
    @Test
    public void testEqualsDifferentUploadDate() {
        actual.setUploaded(new Date());
        assertNotEquals(expected, actual);
    }

    /**
     * Tests public int hashCode().
     */
    @Test
    public void testHashCode() {
        int expected = Objects.hash(
                UUID.fromString("121035a1-bc0b-484f-9ded-726968a13e4b"),
                "testfilename.csv", "1", "new_user", new Date(1572048000L));
        int actual = this.actual.hashCode();
        assertEquals(expected, actual);
    }
}
