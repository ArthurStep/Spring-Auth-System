package main.artfix.passtimenote.services;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.models.Notes;
import main.artfix.passtimenote.repos.NoteRepo;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LoginService {
    public String loginMessage = "";

    private final NoteRepo noteRepo;
    private final AESService aesService;

    public void login(String LoginMail, String LoginPassword) {
        try {
            Notes loginProcess = noteRepo.findByMail(LoginMail);
            String dbPassword = loginProcess.getPassword();
            String dbStatus = loginProcess.getStatus();

            String dbPasswordDecrypted = aesService.decrypt(dbPassword);

            if (dbStatus.equals("VERIFIED")) {
                if (LoginPassword.equals(dbPasswordDecrypted)) {
                    System.out.println();
                    System.out.println("### NEW USER LOGINED ###");
                    System.out.println("Mail: " + LoginMail);
                    System.out.println("Password: " + LoginPassword);
                    System.out.println("### NEW USER LOGINED END ###");
                    System.out.println();
                    loginMessage = "Success Logined!";
                } else {
                    System.out.println();
                    System.out.println("### NEW USER LOGIN BLOCKED WRONG PASSWORD ###");
                    System.out.println("Mail: " + LoginMail);
                    System.out.println("Password: " + LoginPassword);
                    System.out.println("### NEW USER LOGIN BLOCKED WRONG PASSWORD END ###");
                    System.out.println();
                    loginMessage = "Wrong Mail or Password!";
                }
            } else {
                System.out.println();
                System.out.println("### NEW USER LOGIN BLOCKED ACCOUNT NOT VERIFIED ###");
                System.out.println("Mail: " + LoginMail);
                System.out.println("Password: " + LoginPassword);
                System.out.println("### NEW USER LOGIN BLOCKED ACCOUNT NOT VERIFIED END ###");
                System.out.println();
                loginMessage = "Verify Account for Login!";
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("### NEW USER LOGIN BLOCKED USER NOT FOUND ###");
            System.out.println("Mail: " + LoginMail);
            System.out.println("Password: " + LoginPassword);
            System.out.println("### NEW USER LOGIN BLOCKED USER NOT FOUND END ###");
            System.out.println();
            loginMessage = "Wrong Mail or Password!";
        }
    }
}
