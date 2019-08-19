package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.restclients.FixerKoersClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FixerKoersClientTest {
    @Autowired
    private FixerKoersClient client;
    @Test
    public void deKoersMoetPositiefZijn(){
        assertTrue(client.getDollarKoers().compareTo(BigDecimal.ZERO) > 0);
    }
}
