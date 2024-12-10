package features;

import com.bickyraj.demo.DemoApplication;
import com.bickyraj.demo.config.TestConfig;
import com.bickyraj.demo.entity.Account;
import com.bickyraj.demo.entity.Restaurant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountSteps {

    @LocalServerPort
    private int port;

    @Given("I have {double} euro in my account")
    public void iHaveEuroInMyAccount(double balance) {
        Account account = Account.builder()
                .id(123123L)
                .address("test")
                .name("rambo")
                .username("rambo")
                .phone("123342343")
                .balance(balance)
                .build();
    }

    @When("I withdraw {double} euro")
    public void iWithdrawEuro(double amount) {
    }

    @Then("the remaining balance should be {double} euro")
    public void theRemainingBalanceShouldBeEuro(double balance) {
    }
}
