package com.bkap.techshop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "INVALID MESSAGE KEY", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already existed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1003, "Role not found", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(1004, "Role already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User is not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_BIRTHDAY(1008, "Invalid Birthday", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(1009, "Product not found", HttpStatus.BAD_REQUEST),
    CART_ITEM_NOT_FOUND(1010, "Cart-item not found", HttpStatus.BAD_REQUEST),
    WISHLIST_NOT_FOUND(1011, "Wishlist not found", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1012, "Order not found", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1013, "User not found", HttpStatus.BAD_REQUEST),
    POST_CATEGORY_NOT_FOUND(1014, "PostCategory not found", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1015, "Category not found", HttpStatus.BAD_REQUEST),
    PRODUCT_EXIST_IN_CATEGORY(1016, "product exist in category", HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(1016, "Post not found", HttpStatus.BAD_REQUEST),
    POST_EXIST_IN_POST_CATEGORY(1016, "post exist in post category", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND(1017, "Comment not found", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1018, "Cart not found", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
