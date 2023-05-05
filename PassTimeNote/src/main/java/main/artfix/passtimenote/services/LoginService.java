package main.artfix.passtimenote.services;

import lombok.RequiredArgsConstructor;
import main.artfix.passtimenote.models.Users;
import main.artfix.passtimenote.repos.UsersRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UsersRepo noteRepo;
    private final AESService aesService;

    public String login(String LoginMail, String LoginPassword) {
        String result;
        try {
            Users loginProcess = noteRepo.findByMail(LoginMail);
            String dbPassword = loginProcess.getPassword();
            String dbStatus = loginProcess.getStatus();

            String dbPasswordDecrypted = aesService.decrypt(dbPassword);

            if (dbStatus.equals("VERIFIED")) {
                if (LoginPassword.equals(dbPasswordDecrypted)) {
                    result = "Success Logined!";
                } else {
                    result = "Wrong Mail or Password!";
                }
            } else {
                result = "Verify Account for Login!";
            }
        } catch (Exception e) {
            result = "Wrong Mail or Password!";
        }
        return result;
    }
}
