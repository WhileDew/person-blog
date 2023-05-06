import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.Verification;
import com.blogs.commonutils.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;

public class test {
    @org.junit.Test
    public void name() {
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("username", "111");
            put("id", 111L);
        }};
        String token = JwtUtils.createToken(map);
        System.out.println(JwtUtils.validateToken(token).get("username").asString());

    }
}
