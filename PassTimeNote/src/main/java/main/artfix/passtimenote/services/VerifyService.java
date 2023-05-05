package main.artfix.passtimenote.services;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.models.Users;
import main.artfix.passtimenote.repos.UsersRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService {

    private final UsersRepo noteRepo;

    public void verify(String MailForVerify, String VerificationCode) {
        if (!VerificationCode.equals("VERIFIED")) {
            String DbStartCode;
            Users noteGetPVerifyCode = noteRepo.findByMail(MailForVerify);
            DbStartCode = noteGetPVerifyCode.getStatus();
            if (VerificationCode.equals(DbStartCode)) {
                Users note = noteRepo.findByMail(MailForVerify);
                note.setStatus("VERIFIED");
                noteRepo.save(note);
            }
        }
    }
}
