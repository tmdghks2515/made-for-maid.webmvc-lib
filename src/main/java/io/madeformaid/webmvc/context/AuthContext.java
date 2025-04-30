package io.madeformaid.webmvc.context;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class AuthContext {

    private static final ThreadLocal<AuthInfo> authHolder = new ThreadLocal<>();

    public static void set(String accountId, String userId, String shopId, List<String> roles) {
        authHolder.set(new AuthInfo(accountId, userId, shopId, roles));
    }

    public static String getAccountId() {
        return Optional.ofNullable(authHolder.get())
                .map(AuthInfo::getAccountId)
                .orElse(null);
    }

    public static String getUserId() {
        return Optional.ofNullable(authHolder.get())
                .map(AuthInfo::getUserId)
                .orElse(null);
    }

    public static List<String> getRoles() {
        return Optional.ofNullable(authHolder.get())
                .map(AuthInfo::getRoles)
                .orElse(List.of());
    }

    public static boolean hasRole(String role) {
        return getRoles().contains(role);
    }

    public static void clear() {
        authHolder.remove();
    }

    @Getter
    public static class AuthInfo {
        private final String accountId;
        private final String userId;
        private final String shopId;
        private final List<String> roles;

        public AuthInfo(String accountId, String userId, String shopId, List<String> roles) {
            this.accountId = accountId;
            this.userId = userId;
            this.shopId = shopId;
            this.roles = roles != null ? roles : List.of();
        }
    }
}
