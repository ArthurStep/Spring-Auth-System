package main.artfix.passtimenote.services;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.models.Notes;
import main.artfix.passtimenote.repos.NoteRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RandomCodeService randomCodeService;
    private final NoteRepo noteRepo;
    private final AppMailSender appMailSender;
    private final AESService aesService;

    public String regMessage = "";

    @Value("${app.site.address}")
    private String SiteAddress;

    public void registration(String RegName, String RegMail, String RegPassword) {
        try {
            Notes loginProcess = noteRepo.findByMail(RegMail);
            loginProcess.getMail();
            regMessage = "User Already Registred!";
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("### NEW USER REGISTRED ###");
            System.out.println("Name: " + RegName);
            System.out.println("Mail: " + RegMail);
            System.out.println("Password: " + RegPassword);
            System.out.println("### NEW USER REGISTRED END ###");
            System.out.println(" ");

            String UserCode = randomCodeService.getRandom();

            String RegPasswordEncrypted = aesService.encrypt(RegPassword);

            Notes notesDbSetLogin = new Notes();
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
            regMessage = "Success Registred!";
        }
    }
}