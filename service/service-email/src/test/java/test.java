import com.blogs.email.EmailApplication;
import com.blogs.email.entity.Email;
import com.blogs.email.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = EmailApplication.class)
@RunWith(SpringRunner.class)
public class test {

    @Resource
    private EmailService emailService;

    @Test
    public void name1() {
        Email email = new Email("1621445009@qq.com", "你有一新邮件", "小赵宝贝啦啦啦");
        // System.out.println(email);
        emailService.send(email);
    }
}
