package com.sharifi.kotlinweather.data.repository.server.service

/**
 * Created by sharifi on 9/25/17.
 */
data class ApiError(
        val status: RestStatus,
        val code: Int = -1,
        val message: String = ""
)

enum class RestStatus {
    /**
     * no error occurred
     */
    RECEIVED,
    /**
     * error in connecting to repository (Server or Database)
     */
    NO_CONNECTION,
    /**
     * error in getting response (Json Error, Server Error, etc)
     */
    BAD_RESPONSE,
    /**
     * no data available in repository
     */
    EMPTY_RESPONSE;

    /*
    *

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage(Context context) {
        if (!TextUtils.isEmpty(message)) {
            return message;
        }
        if (context == null) {
            return "";
        }
        switch (this) {
            case NO_CONNECTION:
                message = context.getString(R.string.error_connection);
                break;
            case BAD_RESPONSE:
                message = context.getString(R.string.error_response);
                break;
            case EMPTY_RESPONSE:
                message = context.getString(R.string.error_response_empty);
                break;
        }
        return message;
    }*/
}