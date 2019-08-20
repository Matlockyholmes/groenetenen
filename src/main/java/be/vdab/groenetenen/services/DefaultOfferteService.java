package be.vdab.groenetenen.services;

import be.vdab.groenetenen.domain.Offerte;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.repositories.OfferteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultOfferteService implements OfferteService{
    private final OfferteRepository repository;
    private final MailSender mailSender;

    public DefaultOfferteService(OfferteRepository repository, MailSender mailSender) {
        this.repository = repository;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void create(Offerte offerte, String offertesURL) {
        repository.save(offerte);
        mailSender.nieuweOfferte(offerte, offertesURL);
    }
}
