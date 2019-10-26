package multiscripter.tmp.dao;

import multiscripter.tmp.TmpApplication;
import multiscripter.tmp.config.DbConfig;
import multiscripter.tmp.models.FileUpload;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class})
@EnableJpaRepositories("multiscripter.tmp.dao")
@SpringBootTest(classes = TmpApplication.class)
public class FileUploadRepositoryTest {

    private static FileUpload expected;

    @Autowired
    FileUploadRepository repo;

    @BeforeClass
    public static void beforeAllTests() {
        expected = new FileUpload();
        expected.setId("93dfdc51-e374-4d97-a040-49ae0ac9ff20");
        expected.setFileName("dump-3.csv");
        expected.setCompanyId("1");
        expected.setUserId("user-1");
        expected.setUploaded(new Date(1551582183000L));
    }

    @Test
    public void testFindLatestByCompIdAndUserId() {
        FileUpload actual = this.repo.findLatestByCompIdAndUserId(
                "1", "user-1");
        assertEquals(expected, actual);
    }

    @Test
    public void testSave() {
        FileUpload saved = new FileUpload();
        saved.setFileName("saved-1.csv");
        saved.setCompanyId("3");
        saved.setUserId("user-3");
        saved.setUploaded(new Date());
        saved = this.repo.save(saved);
        assertNotNull(saved.getId());
    }
}
