package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.messaging.OfferteEnOffertesURL;
import be.vdab.groenetenen.repositories.OfferteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultOfferteService implements OfferteService{
    private final OfferteRepository repository;
    private final MailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final String nieuweOfferteQueue;

    public DefaultOfferteService(OfferteRepository repository, MailSender mailSender, JmsTemplate jmsTemplate, @Value("${nieuweOfferteQueue}") String nieuweOfferteQueue) {
        this.repository = repository;
        this.mailSender = mailSender;
        this.jmsTemplate = jmsTemplate;
        this.nieuweOfferteQueue = nieuweOfferteQueue;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Offerte offerte, String offertesURL) {
        repository.save(offerte);
        OfferteEnOffertesURL offerteEnOffertesURL = new OfferteEnOffertesURL(offerte, offertesURL);
        jmsTemplate.convertAndSend(nieuweOfferteQueue, offerteEnOffertesURL);
    }

    @Override
    @Scheduled(/*cron = " 0 0/1 * 1/1 * ? * "*/ fixedRate=60000)
    public void aantalOffertesMail() {
        mailSender.aantalOffertesMail(repository.count());
    }
}
