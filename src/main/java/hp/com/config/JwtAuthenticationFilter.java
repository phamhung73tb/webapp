package hp.com.config;

import hp.com.dao.entity.User;
import hp.com.dao.repository.UserRepository;
import hp.com.log.LOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter JWT token from request.
 *
 * @author Pham Van Hung
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;
    /**
     * Get member from token and set member for context
     * @param request http request
     * @param response http response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {

                String username = tokenProvider.getUsernameFromJWT(jwt);

                User userDetails = userRepository.getReferenceById(username);

            }
        } catch (Exception ex) {
            LOG.errorFile("failed on set user authentication");
        }

        filterChain.doFilter(request, response);
    }

    /**
     * get token from request
     * @param request http request
     * @return token
     */

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
