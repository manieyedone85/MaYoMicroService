package com.mayo.crud.common;

public class Constants {

    public static final String API_BASE_CONTEXT_PATH = "/";

    public class ErrorMessage {
        public static final String INVALID_USER_TOKEN = "Invalid user token";
        public static final String CLIENT_TOKEN_MISSING = "Client token missing";
        public static final String INVALID_CLIENT_TOKEN = "Invalid client token";
        public static final String CLIENT_TOKEN_EXPIRED = "Client token expired";
        public static final String UNAUTHORIZED_CLIENT = "UNAUTHORIZED Client";
        public static final String UNAUTHORIZED_USER = "UNAUTHORIZED User";
        public static final String USER_TOKEN_EXPIRED = "User token expired";
        ErrorMessage(){}
    }

    public class Header {
        public static final String CLIENT_ID = "ClientId";
        public static final String USER_ID = "UserId";
        Header(){

        }
    }

}
