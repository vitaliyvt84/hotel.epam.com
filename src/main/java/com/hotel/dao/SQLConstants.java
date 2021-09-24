package com.hotel.dao;

public class SQLConstants {
    private SQLConstants() {
    }
    public static final String SELECT_ALL = "SELECT * FROM %s";
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM %s";
    public static final String FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
    public static final String FIND_BY = "SELECT * from %s where %s = ?";
    public static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";
    public static final String SELECT_ALL_ORDER_BY_LIMIT = "SELECT * FROM %s ORDER BY %s LIMIT ?,?";

    public static final String SELECT_ALL_FROM_APARTMENT_WHERE_ORDER_BY_LIMIT = "SELECT * FROM apartment " +
            "WHERE max_count_adult >= ? AND max_count_child >= ? AND count_of_room >= ? " +
            "ORDER BY %s LIMIT ?,? ";
    public static final String SELECT_ALL_FROM_APARTMENT_WHERE = "SELECT * FROM apartment " +
            "WHERE max_count_adult >= ? AND max_count_child >= ? AND count_of_room >= ?";
    public static final String SELECT_COUNT_FROM_APARTMENT_WHERE = "SELECT COUNT(*) FROM apartment " +
            "WHERE max_count_adult >= ? AND max_count_child >= ? AND count_of_room >= ?";

    public static  final String INSERT_ACCOUNT = "INSERT INTO account (login, email, password, name, phone, address, role_id) " +
            "VALUES (?,?,SHA1(?),?,?,?,?)";
    public static  final String UPDATE_ACCOUNT =
            "UPDATE account SET login = ?, email = ?, password = SHA1(?), name = ?, phone = ?, address = ?, role_id = ? WHERE id = ?";
    public static  final String INSERT_APARTMENT_CLASS = "INSERT INTO apartment_class (name, description) VALUES (?,?)";
    public static  final String UPDATE_APARTMENT_CLASS =
            "UPDATE apartment_class SET name = ?, description = ? WHERE id = ?";
    public static  final String INSERT_PRE_ORDER =
            "INSERT INTO pre_order (number_of_adult, number_of_child, number_of_rooms, check_in, check_out, account_id, " +
                    "apartment_class_id, status, apartment_id) VALUES (?,?,?,?,?,?,?,?,?)";
    public static  final String UPDATE_PRE_ORDER =
            "UPDATE pre_order SET number_of_adult = ?, number_of_child = ?, number_of_rooms = ?, check_in = ?, " +
                    "check_out = ?, account_id = ?, apartment_class_id = ?, status = ?, apartment_id = ? WHERE id = ?";
    public static  final String INSERT_APARTMENT = "INSERT INTO apartment (number, price, max_count_adult, max_count_child, " +
            "status, count_of_room, description, number_of_bed, apartment_class_id) VALUES (?,?,?,?,?,?,?,?,?)";
    public static  final String UPDATE_APARTMENT =
            "UPDATE apartment SET number = ?, price = ?, max_count_adult = ?, max_count_child = ?, status = ?, " +
                    "count_of_room = ?, description = ?, number_of_bed = ?, apartment_class_id = ? WHERE id = ?";
    public static  final String INSERT_BOOKING = "INSERT INTO booking (date_in, date_out, status, price, " +
            "number_of_adult, number_of_child, number_of_rooms, account_id, apartment_id) VALUES (?,?,?,?,?,?,?,?,?)";
    public static  final String UPDATE_BOOKING =
            "UPDATE booking SET date_in = ?, date_out = ?, status = ?, price = ?, number_of_adult = ?, " +
                    "number_of_child = ?, number_of_rooms = ?, account_id = ?, apartment_id = ? WHERE id = ?";

    public static  final String INSERT_APARTMENT_IMAGE = "INSERT INTO apartment_image (apartment_id, image_url, image_type) VALUES (?,?,?)";
    public static  final String UPDATE_APARTMENT_IMAGE =
            "UPDATE apartment_image SET apartment_id = ?, image_url = ?, image_type = ? WHERE id = ?";
}
