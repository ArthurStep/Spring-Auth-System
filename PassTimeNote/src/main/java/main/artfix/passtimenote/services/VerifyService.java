package main.artfix.passtimenote.services;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.models.Notes;
import main.artfix.passtimenote.repos.NoteRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyService {

    private final NoteRepo noteRepo;

    public void verify(String MailForVerify, String VerificationCode) {
        if (!VerificationCode.equals("VERIFIED")) {
            System.out.println();
            System.out.println("### VERIFY REQUEST APPROVED ###");
            System.out.println("Mail For Verify: " + MailForVerify);
            System.out.println("Verification Code: " + VerificationCode);
            System.out.println("### VERIFY REQUEST APPROVED END ###");
            System.out.println();

            String DbStartCode;
            Notes noteGetPVerifyCode = noteRepo.findByMail(MailForVerify);
            DbStartCode = noteGetPVerifyCode.getStatus();

            if (VerificationCode.equals(DbStartCode)) {
                Notes note = noteRepo.findByMail(MailForVerify);
                note.setStatus("VERIFIED");
                noteRepo.save(note);
            } else {
                System.out.println();
                System.out.println("### VERIFY REQUEST BLOCKED WRONG CODE ###");
                System.out.println("Mail For Verify: " + MailForVerify);
                System.out.println("Verification Code: " + VerificationCode);
                System.out.println("### VERIFY REQUEST BLOCKED WRONG CODE END ###");
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println("### VERIFY REQUEST BLOCKED VERIFIED LYE ###");
            System.out.println("Mail For Verify: " + MailForVerify);
            System.out.println("Verification Code: " + VerificationCode);
            System.out.println("### VERIFY REQUEST BLOCKED VERIFIED LYE END ###");
            System.out.println();
        }
    }
}
