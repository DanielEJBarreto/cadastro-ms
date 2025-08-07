package daniel.caixa.entity;

public class EmailUtils {
    public static String maskEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex <= 2) return email; // Não há caracteres suficientes para mascarar

        String prefix = email.substring(0, 2); // primeiros 2 caracteres
        String masked = "*".repeat(atIndex - 2); // substitui o resto com asteriscos
        String domain = email.substring(atIndex); // mantém o domínio

        return prefix + masked + domain;
    }
}
