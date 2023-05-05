package main.artfix.passtimenote.services;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.models.Users;
import main.artfix.passtimenote.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RandomCodeService randomCodeService;
    private final UsersRepo noteRepo;
    private final AppMailSender appMailSender;
    private final AESService aesService;


    @Value("${app.site.address}")
    private String SiteAddress;

    public String registration(String RegName, String RegMail, String RegPassword) {
        String result;
        try {
            Users loginProcess = noteRepo.findByMail(RegMail);
            loginProcess.getMail();
            result = "User Already Registred!";
        } catch (Exception e) {
            String UserCode = randomCodeService.getRandom();

            String RegPasswordEncrypted = aesService.encrypt(RegPassword);

            Users notesDbSetLogin = new Users();
            notesDbSetLogin.setName(RegName);
            notesDbSetLogin.setMail(RegMail);
            notesDbSetLogin.setPassword(RegPasswordEncrypted);
            notesDbSetLogin.setStatus(UserCode);
            noteRepo.save(notesDbSetLogin);

            String verificationLink = SiteAddress + "/process/verify?MailForVerify=" + RegMail + "&VerificationCode=" + UserCode;
            appMailSender.sendMail(RegMail, "PassTimeNote Verification Link Received.",
                    "Hi, Dear " + RegName + "."
                            + "Thank you for registering on PassTimeNote. Please verify your account by clicking the following link:\n"
                            + verificationLink);
            result = "Success Registred!";
        }
        return result;
    }
}