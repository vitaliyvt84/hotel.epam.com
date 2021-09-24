-- START OF SCRIPT
USE hotel;

INSERT INTO role (name) VALUES ("admin");
INSERT INTO role (name) VALUES ("manager");
INSERT INTO role (name) VALUES ("user");

INSERT INTO account VALUES 
(default, "admin", "igor182301@gmail.com", SHA1("adminPass"), default, "Невзоров Игорь", "+80955172013", "Харьков, пер. Ивановский, 36", (SELECT id FROM role WHERE name = "admin"));
INSERT INTO account (login, email, password, name, phone, address, role_id) 
VALUES ("max86", "maimilian86@ukr.net", SHA1("maxPass"), "Шерохов Максим", "+80509312056", "Украина, г.Харьков, ул.Клочковская, 23, кв.89", (SELECT id FROM role WHERE name = "manager"));

-- INSERT INTO account (login, email, password, name, phone, address, role_id) 
-- VALUES ("murphy", "murhyD1976@gmail.com", "murphyPass", "Murphy Diane", "+1396795123", "Canada, Ballston, 684 Westport Drive", (SELECT id FROM role WHERE name = "user"));
-- INSERT INTO account (login, email, password, name, phone, address, role_id) 
-- VALUES ("patterson", "d34paterson@mail.com", "pattersonPass", "Patterson William", "+6536391743", "USA, CA, LosBanos, 167 Jame St.", (SELECT id FROM role WHERE name = "user"));
-- INSERT INTO account (login, email, password, name, phone, address, role_id) 
-- VALUES ("thom1325son", "thompson@gmail.com", "thompsonPass", "Thompson Leslie", "+6375114930", "USA, NY, Forest Hills, 26 Market Drive", (SELECT id FROM role WHERE name = "user"));

INSERT INTO apartment_class (name, description) VALUES ("Стандарт", "Стандартные однокомнатные номера (Standart)");
INSERT INTO apartment_class (name, description) VALUES ("Улучшенный", "Однокомнатные номера категории студия c мини-кухней (Studio)");
INSERT INTO apartment_class (name, description) VALUES ("Полулюкс", "Номера улучшенной планировки категории полулюкс(Junior Suite)");
INSERT INTO apartment_class (name, description) VALUES ("Семейный", "Семейные двух- и трехкомнатные номера (Family Room).");
INSERT INTO apartment_class (name, description) VALUES ("Молодожены", "Номера для молодоженов (Honeymoon Room)");
INSERT INTO apartment_class (name, description) VALUES ("Люкс", "Номера улучшенной планировки, состоящие из 2 и более комнат(Suite)");
INSERT INTO apartment_class (name, description) VALUES ("ДеЛюкс", "Номера повышенной комфортности (De Luxe)");
INSERT INTO apartment_class (name, description) VALUES ("Дуплекс", "Двухуровневые номера – дуплексы (Duplex)");

INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (1, "Фирменный люкс.", 29000, 4, 2, 0, 5, "Фирменный люкс.", 4, 8);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (2, "Суперлюкс 2.", 21000, 3, 1, 0, 4, "Суперлюкс 2.", 3, 7);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (3, "Суперлюкс 1.", 18000, 3, 1, 0, 3, "Суперлюкс 1.", 3, 7);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (4, "Двухместный люкс с 1 кроватью", 8600, 2, 1, 0, 3, "Двухместный люкс с 1 кроватью, балконом, кондиционером и мини-баром.", 1, 6);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (5, "Семейный номер", 11800, 4, 3, 0, 3, "Семейный номер с видом на парк.Семейный номер с балконом, кондиционером и электрическим чайником.", 4, 4);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (6, "Семейный номер", 13000, 4, 3, 0, 3, "Семейный номер с видом на море.Семейный номер с балконом, кондиционером и электрическим чайником.", 4, 4);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (7, "Номер Делюк с 1 кроватью", 4500, 2, 2, 0, 1, "Номер Делюкс с 1 кроватью и балконом, вид на море. Номер с французскими окнами. В числе удобств — кондиционер и телевизор со спутниковыми каналами. Из окон открывается панорамный вид на море.", 1, 5);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (8, "Номер Делюк с 2 кроватями", 5400, 2, 3, 0, 1, "Номер Делюкс с 2 кроватями и балконом, вид на море. Номер с французскими окнами. В числе удобств — кондиционер и телевизор со спутниковыми каналами. Из окон открывается панорамный вид на море.", 2, 7);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (9, "Полулюкс с видом на море", 14000, 4, 3, 0, 4, "Полулюкс с видом на море.В этом номере с окнами от пола до потолка в вашем распоряжении кондиционер, спутниковое телевидение и балкон с панорамным видом на море, а также ванная комната с ванной и феном.", 5, 3);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (10, "Представительский люкс", 17000, 3, 2, 0, 5, "Представительский люкс.", 3, 6);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (11, "Двухместный номер 'Премиум' с видом на парк", 6600, 2, 1, 0, 2, "Двухместный номер 'Премиум' с видом на парк.Двухместный номер с 1 кроватью, балконом, кондиционером и электрическим чайником.", 1, 5);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (12, "Номер 'Премиум' с 2 кроватями и видом на море", 7200, 3, 2, 0, 3, "Номер 'Премиум' с 2 кроватями и видом на море. Номер с балконом, кондиционером и электрическим чайником.", 2, 7);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (13, "Двухуровневый люкс", 9900, 4, 2, 0, 4, "Двухуровневый люкс. Номер с балконом, кондиционером и электрическим чайником.", 4, 8);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (14, "Классический номер с 2 кроватями", 2000, 2, 1, 0, 2, "Классический номер с 2 кроватями", 2, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (15, "Классический номер с 3 кроватями", 2500, 3, 2, 0, 3, "Классический номер с 3 кроватями", 3, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (16, "Классический двухместный номер с 1 кроватью", 1500, 2, 1, 0, 1, "Классический двухместный номер с 1 кроватью", 1, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (17, "Двухместный номер с 2 кроватями и балконом", 2000, 2, 2, 0, 2, "Двухместный номер с 2 кроватями и балконом. Номер с кондиционером, окнами от пола до потолка, спутниковым телевидением и балконом. Номер также располагает собственной ванной комнатой с феном.", 2, 4);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (18, "Улучшенный двухместный номер с 1 кроватью", 2700, 2, 1, 0, 1, "Улучшенный двухместный номер с 1 кроватью, балконом и видом на город.", 2, 2);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (19, "Номер с видом на море", 11200, 2, 1, 0, 2, "Номер с видом на море. Номер с 1 кроватью, балконом, кондиционером и электрическим чайником.", 1, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (20, "Номер с видом на море", 11200, 3, 1, 0, 2, "Номер с видом на море. Номер с 2 кроватями, балконом, кондиционером и электрическим чайником.", 2, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (21, "Двухместный номер с 1 кроватью и балконом", 3400, 2, 1, 0, 1, "Двухместный номер с 1 кроватью и балконом, вид на море. В этом номере с окнами от пола до потолка в вашем распоряжении кондиционер, спутниковое телевидение и балкон с панорамным видом на море.", 1, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (22, "Классический двухместный номер с 2 кроватями", 2000, 3, 1, 0, 2, "Классический двухместный номер с 2 кроватями", 2, 1);
INSERT INTO apartment (number, name, price, max_count_adult, max_count_child, status, count_of_room, description, number_of_bed, apartment_class_id) 
VALUES (23, "Номер с 2 кроватями и балконом", 3900, 3, 2, 0, 2, "Номер с 2 кроватями и балконом, вид на море. В этом номере с окнами от пола до потолка в вашем распоряжении кондиционер, спутниковое телевидение и балкон с панорамным видом на море.", 2, 1);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-5.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-6.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (1, "01/01-7.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (2, "02/02-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (2, "02/02-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (2, "02/02-3.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (3, "03/03-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (3, "03/03-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (3, "03/03-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (3, "03/03-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (3, "03/03-5.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (3, "03/03-6.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (4, "04/04-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (4, "04/04-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (4, "04/04-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (4, "04/04-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (5, "05/05-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (5, "05/05-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (5, "05/05-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (5, "05/05-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (5, "05/05-5.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (5, "05/05-6.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (6, "06/06-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (6, "06/06-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (6, "06/06-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (6, "06/06-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (7, "07/07-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (7, "07/07-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (7, "07/07-3.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (8, "08/08-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (8, "08/08-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (8, "08/08-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (8, "08/08-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (9, "09/09-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (9, "09/09-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (9, "09/09-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (9, "09/09-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (10, "010/010-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (10, "010/010-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (10, "010/010-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (10, "010/010-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (10, "010/010-5.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (10, "010/010-6.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (11, "011/011-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (11, "011/011-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (11, "011/011-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (11, "011/011-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (11, "011/011-5.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (12, "012/012-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (12, "012/012-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (12, "012/012-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (12, "012/012-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-5.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-6.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-7.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (13, "013/013-8.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (14, "014/014-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (14, "014/014-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (14, "014/014-3.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (15, "015/015-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (15, "015/015-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (15, "015/015-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (15, "015/015-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (15, "015/015-5.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (15, "015/015-6.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (16, "016/016-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (16, "016/016-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (16, "016/016-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (16, "016/016-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (16, "016/016-5.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (17, "017/017-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (17, "017/017-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (17, "017/017-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (17, "017/017-4.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (17, "017/017-5.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (18, "018/018-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (18, "018/018-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (18, "018/018-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (18, "018/018-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (19, "019/019-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (19, "019/019-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (19, "019/019-3.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (19, "019/019-4.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (20, "020/020-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (20, "020/020-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (20, "020/020-3.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (21, "021/021-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (21, "021/021-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (21, "021/021-3.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (22, "022/022-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (22, "022/022-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (22, "022/022-3.jpg", 2);

INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (23, "023/023-1.jpg", 1);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (23, "023/023-2.jpg", 2);
INSERT INTO apartment_image(apartment_id, image_url, image_type) VALUES (23, "023/023-3.jpg", 2);

-- END OF SCRIPT