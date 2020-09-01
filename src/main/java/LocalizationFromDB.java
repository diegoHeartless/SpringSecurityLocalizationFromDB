import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

public class LocalizationFromDB implements MessageSource {

    MessageRepository messageRepository;

    public LocalizationFromDB() {
    }

    public String getMessage(String s, Object[] objects, String s1, Locale locale) {
        return s1;
    }

    public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
        messageRepository.setLang(locale.getCountry());
       return messageRepository.getMessageFromDB(s);
    }

    public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }
    public void createRepAndLoadDataSource(DataSource dataSource){
        messageRepository = new MessageRepository(dataSource);
    }
}
