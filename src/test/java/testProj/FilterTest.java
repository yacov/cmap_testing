package testProj;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testProj.pages.LoginPage;
import testProj.pages.TaskPage;

import java.io.IOException;

public class FilterTest extends TestBase {

    private LoginPage loginpage;
    private TaskPage taskPage;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        loginpage = PageFactory.initElements(driver, LoginPage.class);
        taskPage = PageFactory.initElements(driver, TaskPage.class);
        loginpage.login("cmap_admin","222");
    }


    @Test
    public void testBugFilterPositive() throws IOException, InterruptedException {
taskPage.waitUntilTaskPageIsLoaded();
        Assert.assertTrue(taskPage.isOnTaskPage());
        taskPage
                .openTypFilterForm()
                .checkBugBox()
                .pressFilterButton()
                .checkTypColumn();
    }
}
